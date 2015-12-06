package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Khoa;
@Repository
public class KhoaDaoImpl extends GenericDaoImpl<Khoa, Long> implements KhoaDao{
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<Khoa> getAll() {
		List<Khoa> list = new ArrayList<>();
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			list = session.createCriteria(Khoa.class).list();
			for (Khoa khoa : list) {
				khoa.fetchAllNganh();
			}
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
