package com.ssurvey.repositories;

import java.util.List;

import org.hibernate.SessionFactory;
import org.jsoup.helper.Validate;

import com.ssurvey.model.AnsweredSurvey;

public class AnswerRepository extends GenericRepository {

  public AnswerRepository() {
  }

  public AnswerRepository(SessionFactory sessionFactory) {
    Validate.notNull(sessionFactory);
    this.setSessionFactory(sessionFactory);
  }

  public void saveAnsweredSurvey(AnsweredSurvey answeredSurvey) {
    this.save(answeredSurvey);
  }

  public List<AnsweredSurvey> getAnsweredSurveys() {
    return this.list(AnsweredSurvey.class);
  }

  public AnsweredSurvey getAnsweredSurveyById(long id) {
    return this.get(AnsweredSurvey.class, id);
  }

  public AnsweredSurvey getAnsweredSurveyBySurveyId(long id) {
    return this.get(AnsweredSurvey.class, "survey", id).get(0);
  }
}
