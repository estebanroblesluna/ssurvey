package com.ssurvey.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Position;
import org.springframework.social.linkedin.api.Recommendation;

public class LinkedInUserProfile {

  private String id;
  private List<LinkedInPosition> positions;
  private Set<LinkedInUserProfile> recommenders;
  private Set<LinkedInUserProfile> connections;
  
  public LinkedInUserProfile(){
    
  }

  public LinkedInUserProfile(LinkedInProfileFull profile) {
    this.id = profile.getId();
    this.positions = new ArrayList<LinkedInPosition>();
    for(Position position : profile.getPositions()){
      this.addPosition(new LinkedInPosition(position));
    }
    this.recommenders = new HashSet<LinkedInUserProfile>();
    this.connections = new HashSet<LinkedInUserProfile>();
  }

  public List<LinkedInPosition> getPositions() {
    return positions;
  }

  public void setPositions(List<LinkedInPosition> positions) {
    this.positions = positions;
  }

  public void addPosition(LinkedInPosition position) {
    this.positions.add(position);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Set<LinkedInUserProfile> getRecommenders() {
    return recommenders;
  }

  public void setRecommenders(Set<LinkedInUserProfile> recommenders) {
    this.recommenders = recommenders;
  }
  
  public void addRecommender(LinkedInUserProfile recomender){
    if(this.recommenders.contains(recomender)){
      this.recommenders.remove(recomender);
    }
    this.recommenders.add(recomender);
  }

  public Set<LinkedInUserProfile> getConnections() {
    return connections;
  }

  public void setConnections(Set<LinkedInUserProfile> connections) {
    this.connections = connections;
  }
  
  public void addConnection(LinkedInUserProfile connection){
    if(this.connections.contains(connection)){
      this.connections.remove(connection);
    }
    this.connections.add(connection);
    if(connection.getConnections().contains(this)){
      connection.getConnections().remove(this);
    }
    connection.getConnections().add(this);
  }
  
  @Override
  public boolean equals(Object obj){
    if (!(obj instanceof LinkedInUserProfile))
      return false;
    if (obj == this)
      return true;
    LinkedInUserProfile rhs = (LinkedInUserProfile) obj;
    return new EqualsBuilder().
        // if deriving: appendSuper(super.equals(obj)).
        append(this.id, rhs.id).
        isEquals();
  }
  
  @Override
  public int hashCode(){
    return new HashCodeBuilder().append(this.id).build();
  }
  
}
