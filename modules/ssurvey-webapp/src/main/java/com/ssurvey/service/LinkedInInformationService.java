package com.ssurvey.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.linkedin.api.Company;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Position;
import org.springframework.social.linkedin.api.Recommendation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.Account;
import com.ssurvey.model.GetConnectionTicket;
import com.ssurvey.model.GetRecommenderTicket;
import com.ssurvey.model.LinkedInCompany;
import com.ssurvey.model.LinkedInPosition;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.model.UpdateAllTicket;
import com.ssurvey.repositories.GenericRepository;
import com.ssurvey.util.LinkedInAPIHelper;

@Service
public class LinkedInInformationService {

//  Constants for confidence index
//  private static final float MIN_CONNECTIONS = 15f;
//  private static final float MAX_CONNECTIONS = 100f;
//  private static final float FIX_CONNECTIONS = 20f;
//  private static final float MAX_RECOMMENDERS = 100f;
//  private static final float FIX_RECOMMENDERS = 50f;
//  private static final float DEFAULT_CONFIDENCE = 0.5f;
  
  private GenericRepository repository;
  private UsersConnectionRepository usersConnectionRepository;
  private TicketService ticketService;
  private AccountService accountService;

  public LinkedInInformationService(GenericRepository repository, TicketService ticketService, UsersConnectionRepository usersConnectionRepository, AccountService accountService) {
    this.ticketService = ticketService;
    this.usersConnectionRepository = usersConnectionRepository;
    this.repository = repository;
    this.accountService = accountService;
  }

  public LinkedInInformationService() {
  }

  @Transactional
  public LinkedInUserProfile getLinkedInUserProfile(String linkedInId) {
    return (LinkedInUserProfile) this.repository.get(LinkedInUserProfile.class, linkedInId);
  }

  @Transactional
  public LinkedInPosition getLinkedInPosition(String positionId) {
    return (LinkedInPosition) this.repository.get(LinkedInPosition.class, positionId);
  }

  @Transactional
  public LinkedInCompany getLinkedInCompany(int companyId) {
    return (LinkedInCompany) this.repository.get(LinkedInCompany.class, companyId);
  }

  @Transactional
  public LinkedInCompany updateCompany(Company company) {
    LinkedInCompany ret = this.getLinkedInCompany(company.getId());
    if (ret == null) {
      ret = new LinkedInCompany();
      ret.setId(company.getId());
    }
    ret.setName(company.getName());
    this.repository.save(ret);
    return ret;
  }

  @Transactional
  public LinkedInPosition updatePosition(Position position) {
    LinkedInPosition ret = this.getLinkedInPosition(position.getId());
    if (ret == null) {
      ret = new LinkedInPosition();
      ret.setId(position.getId());
    }
    ret.setStartDate(LinkedInAPIHelper.convertDate(position.getStartDate()));
    ret.setEndDate(LinkedInAPIHelper.convertDate(position.getEndDate()));
    ret.setCompany(this.updateCompany(position.getCompany()));
    this.repository.save(ret);
    return ret;
  }

  @Transactional
  public LinkedInUserProfile updateProfile(Long connectionOwnerId, String linkedInProfileId, int depth) {
    LinkedIn linkedIn = this.usersConnectionRepository.createConnectionRepository(connectionOwnerId.toString()).getPrimaryConnection(LinkedIn.class).getApi();

    if (linkedInProfileId == null) {
      linkedInProfileId = linkedIn.profileOperations().getProfileId();
    }
    //linkedIn.profileOperations().getUserProfile().getProfilePictureUrl()
    LinkedInProfileFull profile;
    try {
      profile = linkedIn.profileOperations().getProfileFullById(linkedInProfileId);
    } catch (Exception e) {
      return null;
    }

    if (profile.getConnectionAuthorization() == null) {
      return null;
    }
    LinkedInUserProfile ret = this.getLinkedInUserProfile(profile.getId());
    if (ret == null) {
      ret = new LinkedInUserProfile();
      ret.setId(profile.getId());
    }

    // Positions
    if (profile.getPositions() != null) {
      for (Position position : profile.getPositions()) {
        ret.addPosition(this.updatePosition(position));
      }
    }

    // Recomenders
    if (depth == 1 && profile.getRecommendationsReceived() != null) {
      for (Recommendation recommendation : profile.getRecommendationsReceived()) {
        this.ticketService.saveTicket(new GetRecommenderTicket(connectionOwnerId, recommendation.getRecommender().getId(), linkedInProfileId));
      }
    }

    // Connections
    if (depth > 0 && linkedIn.connectionOperations().getConnections() != null) {
      for (LinkedInProfile connection : linkedIn.connectionOperations().getConnections()) {
        this.ticketService.saveTicket(new GetConnectionTicket(connectionOwnerId, connection.getId(), linkedInProfileId, depth - 1));
      }
    }
    this.repository.save(ret);
    return ret;
  }

  @Transactional
  public void addConnection(LinkedInUserProfile p1, LinkedInUserProfile p2) {
    if (p1 == null || p2 == null) {
      return;
    }
    p1 = this.getLinkedInUserProfile(p1.getId());
    p2 = this.getLinkedInUserProfile(p2.getId());
    p1.addConnection(p2);
    p2.addConnection(p1);
    this.repository.save(p1);
    this.repository.save(p2);
  }

  @Transactional
  public void addRecommendation(LinkedInUserProfile recommender, LinkedInUserProfile recommendee) {
    if (recommendee == null || recommender == null) {
      return;
    }
    recommendee = this.getLinkedInUserProfile(recommendee.getId());
    recommender = this.getLinkedInUserProfile(recommender.getId());
    recommender.addConnection(recommendee);
    recommendee.addRecommender(recommender);
    this.repository.save(recommendee);
    this.repository.save(recommender);
  }
  /*
  @Transactional
  public void updateProfileConfidence(LinkedInUserProfile profile) {
    profile = this.getLinkedInUserProfile(profile.getId());
    float confidence = DEFAULT_CONFIDENCE, aux;
    int connections = profile.getConnections().size();
    if (connections >= MIN_CONNECTIONS) {
      aux = Math.max(connections, MAX_CONNECTIONS) * (FIX_CONNECTIONS / MAX_CONNECTIONS);
      confidence += (aux == 0) ? 0 : (1 / aux);
    } else {
      aux = (MIN_CONNECTIONS - connections) * (FIX_CONNECTIONS / MIN_CONNECTIONS);
      confidence -= (aux == 0) ? 0 : (1 / aux);
    }

    // System.out.println("[DEBUG] confidence after connections: " +
    // confidence);
    aux = Math.max(profile.getRecommenders().size(), MAX_RECOMMENDERS) * (FIX_RECOMMENDERS / MAX_RECOMMENDERS);
    confidence += (aux == 0) ? 0 : (1 / aux);

    // System.out.println("[DEBUG] confidence after recommendations: " +
    // confidence);
    profile.setConfidence(confidence);
    this.repository.save(profile);
  }
  */
  @Transactional
  public LinkedInUserProfile getLinkedInProfileForAccount(Long accountId) {
    LinkedIn api = this.usersConnectionRepository.createConnectionRepository(accountId.toString()).getPrimaryConnection(LinkedIn.class).getApi();
    return this.getLinkedInUserProfile(api.profileOperations().getProfileId());
  }
  
  @Transactional
  public Account getAccountForLinkedInProfile(String profileId) {
    Set<String> profileSet = new HashSet<String>();
    profileSet.add(profileId);
    Set<String> accountIdSet = this.usersConnectionRepository.findUserIdsConnectedTo("linkedin", profileSet);
    return this.accountService.getAccountById(Long.parseLong(accountIdSet.iterator().next()));
  }

  @Transactional
  public void updateAllData() {
    this.repository.save(new UpdateAllTicket());
  }

  @Transactional
  public void saveLinkedInUserProfile(LinkedInUserProfile profile) {
    this.repository.save(profile);
  }
  
  @Transactional
  public int getLinkedInUserConnectionsSize (String linkedInId) {
    return ((LinkedInUserProfile) this.repository.get(LinkedInUserProfile.class, linkedInId)).getConnections().size();
  }
}
