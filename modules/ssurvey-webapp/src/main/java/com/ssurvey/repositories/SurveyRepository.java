package com.ssurvey.repositories;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssurvey.model.Survey;

@Repository
public class SurveyRepository {

  private SessionFactory sessionFactory;

  public SurveyRepository() {
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void saveSurvey(Survey survey) {
    this.sessionFactory.getCurrentSession().save(survey);
  }

  @SuppressWarnings("unchecked")
  public List<Survey> getSurveys() {
    return this.sessionFactory.getCurrentSession().createCriteria(Survey.class).list();
  }

  public Survey getSurveyById(long id) {
    return (Survey) this.sessionFactory.getCurrentSession().get(Survey.class, id);
  }
}
