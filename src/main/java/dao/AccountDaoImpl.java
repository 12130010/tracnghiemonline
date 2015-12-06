package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Account;
import model.Khoa;
import model.Nganh;

@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account, Long> implements AccountDao {
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Account login(String username, String password) throws Exception {
		Account account = null;
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery(SQL.CHECK_USER_PASS);
			query.setParameter("user", username);
			query.setParameter("pass", password);
			account = (Account) query.uniqueResult();
			if (account != null)
				getKhoaAndNganh(account, session);
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
		return account;
	}

	@Override
	public Account register(Account account) throws Exception {
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery(SQL.CHECK_USER_PASS);
			query.setParameter("user", account.getUsername());
			query.setParameter("pass", account.getPassword());
			if (query.uniqueResult() != null) {
				account = null;
			} else {
				session.save(account);
				getKhoaAndNganh(account, session);
				session.flush();
				session.getTransaction().commit();
			}
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
		return account;
	}

	/**
	 * get Khoa and Nganh then set to Account
	 * 
	 * @param account
	 * @param session
	 * @throws Exception
	 */
	private void getKhoaAndNganh(Account account, Session session) throws Exception {
		try {
			Khoa khoa = null;
			Nganh nganh = null;
			// select Khoa
			Connection connection = ((SessionImpl) session).connection();
			PreparedStatement pre = connection.prepareStatement(SQL.SELECT_ONLY_INFO_KHOA);
			pre.setLong(1, account.getKhoaID());
			ResultSet res = pre.executeQuery();
			khoa = new Khoa();
			if (res.next()) {
				khoa.setId(res.getLong("id"));
				khoa.setMaKhoa(res.getString("maKhoa"));
				khoa.setTenKhoa(res.getString("tenKhoa"));
			}
			res.close();
			account.setKhoa(khoa);
			// select Nganh
			pre = connection.prepareStatement(SQL.SELECT_ONLY_INFO_NGANH);
			pre.setLong(1, account.getNganhID());
			res = pre.executeQuery();
			nganh = new Nganh();
			if (res.next()) {
				nganh.setId(res.getLong("id"));
				nganh.setMaNganh(res.getString("maNganh"));
				nganh.setTenNganh(res.getString("tenNganh"));
			}
			res.close();
			account.setNganh(nganh);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
		}
	}
}
