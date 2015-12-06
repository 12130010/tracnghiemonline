package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Account;

@Repository
public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {
	@Autowired
	private SessionFactory sessionFactory;

	
	protected Class<? extends E> daoType;

	public GenericDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoType = (Class) pt.getActualTypeArguments()[0];
	}

	/**
	 * By defining this class as abstract, we prevent Spring from creating
	 * instance of this class If not defined as abstract,
	 * getClass().getGenericSuperClass() would return Object. There would be
	 * exception because Object class does not hava constructor with parameters.
	 */
//	public GenericDaoImpl() {
//		Type t = getClass().getGenericSuperclass();
//		ParameterizedType pt = (ParameterizedType) t;
//		daoType = (Class) pt.getActualTypeArguments()[0];
//	}

	protected Session openSession() {
		return sessionFactory.openSession();
	}

	@Override
	public K save(E entity)  {
		Session session = openSession();
		Transaction transaction = null;
		K k = null;
		try {
			transaction = session.beginTransaction();
			k = (K) session.save(entity);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return k;
	}

	@Override
	public void saveOrUpdate(E entity)  {
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void update(E entity)  {
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(entity);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void remove(E entity)  {
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(entity);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public E find(K key) {
		E entity = null;
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			entity = (E) session.get(daoType, key);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> getAll() {
		List<E> list = new ArrayList<>();
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			list = openSession().createCriteria(daoType).list();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}
}
/*
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
*/