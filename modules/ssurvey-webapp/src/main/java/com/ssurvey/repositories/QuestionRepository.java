package com.ssurvey.repositories;

import org.hibernate.SessionFactory;
import org.jsoup.helper.Validate;
import org.springframework.stereotype.Repository;

import com.ssurvey.model.Question;

@Repository
public class QuestionRepository extends GenericRepository {

  public QuestionRepository() {
  }

  public QuestionRepository(SessionFactory sessionFactory) {
    Validate.notNull(sessionFactory);
    this.setSessionFactory(sessionFactory);
  }

  public void saveQuestion(Question question) {
    this.save(question);
  }
}
