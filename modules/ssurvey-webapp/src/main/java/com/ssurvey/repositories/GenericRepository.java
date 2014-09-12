package com.ssurvey.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Repository;

@Repository
public class GenericRepository {

  private SessionFactory sessionFactory;

  protected GenericRepository() {
  }

  public GenericRepository(SessionFactory sessionFactory) {

    this.setSessionFactory(sessionFactory);
  }

  public void save(Object object) {
    this.getSessionFactory().getCurrentSession().save(object);
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> get(Class< ? > theClass, String orderBy) {
    return this.getSessionFactory().getCurrentSession().createCriteria(theClass).addOrder(Order.asc(orderBy)).list();
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> get(Class< ? > theClass) {
    return this.getSessionFactory().getCurrentSession().createCriteria(theClass).list();
  }

  public void delete(Class< ? > theClass, Serializable id) {
    String query = "delete from :class where id = :id".replace(":class", theClass.getCanonicalName());
    Query q = this.getSessionFactory().getCurrentSession().createQuery(query);
    q.setParameter("id", id);
    q.executeUpdate();
  }

  @SuppressWarnings("unchecked")
  public <T> T get(Class< ? > theClass, Serializable id) {
    return (T) this.getSessionFactory().getCurrentSession().get(theClass, id);
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> get(Class<T> theClass, String assocProperty, Serializable anID) {
    return (List<T>) this.getSessionFactory().getCurrentSession().createCriteria(theClass).createAlias(assocProperty, "a").add(Restrictions.eq("a.id", anID))
            .list();
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> findAllBy(Class<T> theClass, Map<String, Object> restrictions) {
    Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(theClass);
    for (Entry<String, Object> entry : restrictions.entrySet()) {
      criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
    }
    return criteria.list();
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> findAllBy(Class<T> theClass, String property, Object value) {
    return this.getSessionFactory().getCurrentSession().createCriteria(theClass).add(Restrictions.eq(property, value)).list();
  }

  @SuppressWarnings("unchecked")
  public <T> T findBy(Class<T> theClass, String property, Object value) {
    return (T) this.getSessionFactory().getCurrentSession().createCriteria(theClass).add(Restrictions.eq(property, value)).uniqueResult();
  }

  @SuppressWarnings("unchecked")
  public <T> T findByAnd(Class<T> theClass, String property, Object value, String property2, Object value2) {
    return (T) this.getSessionFactory().getCurrentSession().createCriteria(theClass).add(Restrictions.eq(property, value))
            .add(Restrictions.eq(property2, value2)).uniqueResult();
  }

  @SuppressWarnings("unchecked")
  public <T> T findBy(Class<T> theClass, Map<String, Object> restrictions) {
    Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(theClass);
    for (Entry<String, Object> entry : restrictions.entrySet()) {
      criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
    }
    return (T) criteria.uniqueResult();
  }

  public void update(Object o) {
    this.getSessionFactory().getCurrentSession().update(o);
  }

  protected SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  protected void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

}