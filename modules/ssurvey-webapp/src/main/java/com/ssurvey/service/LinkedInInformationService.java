package com.ssurvey.service;

import org.springframework.social.connect.ConnectionRepository;
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
import com.ssurvey.model.AnsweredSurvey;
import com.ssurvey.model.GetConnectionTicket;
import com.ssurvey.model.GetRecommenderTicket;
import com.ssurvey.model.GetRespondentInformationTicket;
import com.ssurvey.model.LinkedInCompany;
import com.ssurvey.model.LinkedInPosition;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.repositories.GenericRepository;
import com.ssurvey.util.LinkedInAPIHelper;

@Service
public class LinkedInInformationService {
  private GenericRepository repository;
  private UsersConnectionRepository usersConnectionRepository;
  private TicketService ticketService;

  public LinkedInInformationService(GenericRepository repository, TicketService ticketService, UsersConnectionRepository usersConnectionRepository) {
    this.ticketService = ticketService;
    this.usersConnectionRepository = usersConnectionRepository;
    this.repository = repository;
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
    LinkedIn linkedIn = this.usersConnectionRepository.createConnectionRepository(connectionOwnerId.toString())
            .getPrimaryConnection(LinkedIn.class).getApi();
    
    if(linkedInProfileId == null){
      linkedInProfileId = linkedIn.profileOperations().getProfileId();
    }
    
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
        this.ticketService.saveTicket(new GetRecommenderTicket(connectionOwnerId,recommendation.getRecommender().getId(),linkedInProfileId));
      }
    }

    // Connections
    if (depth > 0 && linkedIn.connectionOperations().getConnections() != null) {
      for (LinkedInProfile connection : linkedIn.connectionOperations().getConnections()) {
        this.ticketService.saveTicket(new GetConnectionTicket(connectionOwnerId,connection.getId(),linkedInProfileId,depth-1));
      }
    }
    this.repository.save(ret);
    return ret;
  }
  
  @Transactional
  public void addConnection(LinkedInUserProfile p1, LinkedInUserProfile p2){
    if(p1 == null || p2 == null){
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
  public void addRecommendation(LinkedInUserProfile recommender, LinkedInUserProfile recommendee){
    if(recommendee == null || recommender == null){
      return;
    }
    recommendee = this.getLinkedInUserProfile(recommendee.getId());
    recommender = this.getLinkedInUserProfile(recommender.getId());
    recommender.addConnection(recommendee);
    recommendee.addRecommender(recommender);
    this.repository.save(recommendee);
    this.repository.save(recommender);
  }
  
  @Transactional
  public void saveLinkedInUserProfile(LinkedInUserProfile profile){
    this.repository.save(profile);
  }
}
