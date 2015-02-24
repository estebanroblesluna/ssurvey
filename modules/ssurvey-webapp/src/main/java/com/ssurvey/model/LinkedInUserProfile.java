package com.ssurvey.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class LinkedInUserProfile {

  
  private static final float INITIAL_CONFIDENCE = -1.0f;
  
  private String id;
  private List<LinkedInPosition> positions;
  private Set<LinkedInUserProfile> recommenders;
  private Set<LinkedInUserProfile> connections;
  private float confidence;
  
  public LinkedInUserProfile(){
    this.positions = new ArrayList<LinkedInPosition>();
    this.recommenders = new HashSet<LinkedInUserProfile>();
    this.connections = new HashSet<LinkedInUserProfile>();
    this.confidence = INITIAL_CONFIDENCE;
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
    return this.connections;
  }

  public void setConnections(Set<LinkedInUserProfile> connections) {
    this.connections = connections;
  }
  
  public void addConnection(LinkedInUserProfile connection){
    if(this.connections.contains(connection)){
      this.connections.remove(connection);
    }
    this.connections.add(connection);
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


  public float getConfidence() {
    return confidence;
  }

  public void setConfidence(float confidence) {
    this.confidence = confidence;
  }
  
}
