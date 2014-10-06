package com.ssurvey.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.jsoup.helper.Validate;
import org.springframework.stereotype.Repository;

import com.ssurvey.model.Survey;

@Repository
public class SurveyRepository extends GenericRepository {

  public SurveyRepository() {
  }

  public SurveyRepository(SessionFactory sessionFactory) {
    Validate.notNull(sessionFactory);
    this.setSessionFactory(sessionFactory);
  }

  public void saveSurvey(Survey survey) {
    this.save(survey);
  }

  public List<Survey> getSurveys() {
    return this.list(Survey.class);
  }

  public Survey getSurveyById(long id) {
    return this.get(Survey.class, id);
  }

  @SuppressWarnings("unchecked")
  public Survey getSurveyByPermalink(long permalink) {
    ArrayList<Survey> surveys = (ArrayList<Survey>) this.getSessionFactory().getCurrentSession().createCriteria(Survey.class)
            .add(Restrictions.eq("permalink", permalink)).list();
    if (!surveys.isEmpty()) {
      return surveys.get(0);
    } else {
      return null;
    }
  }
}
