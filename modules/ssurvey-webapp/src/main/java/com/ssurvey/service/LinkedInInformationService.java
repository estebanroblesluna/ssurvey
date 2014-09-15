package com.ssurvey.service;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.Company;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Position;
import org.springframework.social.linkedin.api.Recommendation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.LinkedInCompany;
import com.ssurvey.model.LinkedInPosition;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.repositories.GenericRepository;
import com.ssurvey.util.LinkedInAPIHelper;

@Service
public class LinkedInInformationService {

  private ConnectionRepository connectionRepository;
  private GenericRepository repository;

  public LinkedInInformationService(ConnectionRepository connectionRepository, GenericRepository repository) {
    this.connectionRepository = connectionRepository;
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

  private LinkedInCompany updateCompany(Company company) {
    LinkedInCompany ret = this.getLinkedInCompany(company.getId());
    if (ret == null) {
      ret = new LinkedInCompany();
      ret.setId(company.getId());
    }
    ret.setName(company.getName());
    this.repository.save(ret);
    return ret;
  }

  private LinkedInPosition updatePosition(Position position) {
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

  private LinkedInUserProfile updateProfile(String linkedInProfileId, int depth) {
    LinkedIn linkedIn = this.connectionRepository.getPrimaryConnection(LinkedIn.class).getApi();
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
        LinkedInUserProfile recommender = this.updateProfile(recommendation.getRecommender().getId(), 0);
        if (recommender != null) {
          ret.addRecommender(recommender);
        }
      }
    }

    // Connections
    if (depth > 0 && linkedIn.connectionOperations().getConnections() != null) {
      for (LinkedInProfile connection : linkedIn.connectionOperations().getConnections()) {
        LinkedInUserProfile connectionProfile = this.updateProfile(connection.getId(), depth - 1);
        if (connectionProfile != null) {
          ret.addConnection(connectionProfile);
        }
      }
    }
    this.repository.save(ret);
    return ret;
  }

  @Transactional
  public LinkedInUserProfile getRespondentInformation() {
    LinkedIn linkedIn = this.connectionRepository.getPrimaryConnection(LinkedIn.class).getApi();
    LinkedInUserProfile linkedInUserProfile = this.updateProfile(linkedIn.profileOperations().getProfileId(), 1);
    return linkedInUserProfile;
  }

}
