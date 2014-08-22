package com.ssurvey.repositories;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.Survey;

@Repository
public class SurveyRepository {
	
	private SessionFactory sessionFactory;
	
	public SurveyRepository(){
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public Survey saveSurvey(Survey survey){
		return (Survey) this.sessionFactory.getCurrentSession().save(survey);
	}
}
