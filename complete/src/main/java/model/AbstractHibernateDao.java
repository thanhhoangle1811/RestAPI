package model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import entities.Bookstore;

public abstract class AbstractHibernateDao<T extends Serializable> {
	private Class<T> clazz;
	@Autowired
	SessionFactory sessionFactory;
	
	

	public final void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(long id) {
		T employees = null;
		SessionFactory factory = HibernateUltis.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.getTransaction().begin();

			String sql = "Select e from " + clazz.getName() + " e ";

			// Create Query object.
			Query<T> query = session.createQuery(sql);

			// Execute query.
			employees = query.getSingleResult();

			// Commit data.
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
		}
		return employees;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	public void create(T entity) {
		try{
			getCurrentSession().persist(entity);	
		}catch (Exception e) {
			// TODO: handle exception
			getCurrentSession().getTransaction().rollback();
		}
		
	}

	public void update(T entity) {
		getCurrentSession().merge(entity);
	}

	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public void deleteById(long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}

	protected final Session getCurrentSession() {
		if(sessionFactory == null){
			sessionFactory =new Configuration().configure().buildSessionFactory();
		}
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		return session;
	}
}
