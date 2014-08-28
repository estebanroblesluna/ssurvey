package com.ssurvey.repositories;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.Question;
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


  public void saveQuestion(Question question) {
    this.sessionFactory.getCurrentSession().save(question);
  }
  
  public List<Survey> getSurveys(){
	  return this.sessionFactory.getCurrentSession().createCriteria(Survey.class).list();
  }
}
