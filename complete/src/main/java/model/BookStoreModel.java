package model;

import entities.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BookStoreModel extends AbstractHibernateDao<Bookstore> {

	

	public Bookstore find(int ID) {
		Session session = HibernateUltis.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return (Bookstore) session.createQuery(" from Bookstore b WHERE b.id = :id")
				.setParameter("id", ID).getSingleResult();
	}

}
