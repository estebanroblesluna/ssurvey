package com.ssurvey.repositories;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssurvey.model.Question;

@Repository
public class QuestionRepository {

  private SessionFactory sessionFactory;

  public QuestionRepository() {
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void saveQuestion(Question question) {
    this.sessionFactory.getCurrentSession().save(question);
  }
}
