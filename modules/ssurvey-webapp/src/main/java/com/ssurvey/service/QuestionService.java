package com.ssurvey.service;

import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.Question;
import com.ssurvey.repositories.QuestionRepository;

public class QuestionService {

  private QuestionRepository questionRepository;

  public QuestionService() {
  }

  public QuestionRepository getQuestionRepository() {
    return questionRepository;
  }

  public void setQuestionRepository(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  @Transactional
  public void saveQuestion(Question question) {
    this.questionRepository.saveQuestion(question);
  }

  public Question getQuestion(Long questionId) {
    return this.questionRepository.get(Question.class, questionId);
  }

}
