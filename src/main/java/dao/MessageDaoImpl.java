package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDaoImpl implements MessageDao {
	@Autowired
	SessionFactory sessionFactory;

	public MessageDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	public MessageDaoImpl() {
	}
	@Override
	public void saveMessage(String mes) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery(SQL.SAVE_MESSAGE);
			sqlQuery.setParameter("message", mes);
			sqlQuery.executeUpdate();
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
	public List<String> getNewMessage(int n) throws Exception {
		List<String> res = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Connection con = ((SessionImpl) session).connection();
			PreparedStatement pre = con.prepareStatement(SQL.GET_MESSAGE);
			pre.setInt(1, n);
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				res.add(result.getString("message"));
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
		return res;
	}

	@Override
	public String getNewMessage() throws Exception {
		return getNewMessage(1).get(0);
	}

}
