package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Account;
import model.CauHoi;

@Repository
public class CauHoiDaoImpl extends GenericDaoImpl<CauHoi, Long> implements CauHoiDao {
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public CauHoi find(Long idCauHoi) {
		Session session = openSession();
		Transaction transaction = null;
		CauHoi cauHoi = null;
		try {
			transaction = session.beginTransaction();
			cauHoi = (CauHoi) session.get(CauHoi.class, idCauHoi);
			cauHoi.fetchAll();
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
		return cauHoi;
	}
	@Override
	public List<Long> listIdCauHoi(long idMonHoc) {
		Session session = openSession();
		Transaction transaction = null;
		List<Long> list = new ArrayList<>();
		try {
			transaction = session.beginTransaction();
			// TODO just get list id cau hoi. Chua can lam.
			SQLQuery sqlQuery = session.createSQLQuery(SQL.LIST_CAUHOI_OF_MONHOC_SQL);
			sqlQuery.setParameter("idMonHoc", idMonHoc);
			sqlQuery.addEntity(CauHoi.class);
			List<CauHoi> listCH = sqlQuery.list();
			// System.out.println(listCH);
			for (CauHoi cauHoi : listCH) {
				list.add(cauHoi.getId());
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
	@Override
	public List<CauHoi> listIdCauHois(long idMonHoc) {
		Session session = openSession();
		Transaction transaction = null;
		List<CauHoi> listCH = new ArrayList<>();
		try {
			transaction = session.beginTransaction();
			// TODO just get list id cau hoi. Chua can lam.
			SQLQuery sqlQuery = session.createSQLQuery(SQL.LIST_CAUHOI_OF_MONHOC_SQL);
			sqlQuery.setParameter("idMonHoc", idMonHoc);
			sqlQuery.addEntity(CauHoi.class);
			listCH = sqlQuery.list();
			for (CauHoi cauHoi : listCH) {
				cauHoi.fetchAll();
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
		return listCH;
	}

	@Override
	public List<Long> listIdCauHoi(long idMonHoc, int doKho) throws Exception {
		Session session = openSession();
		Transaction transaction = null;
		List<Long> list = new ArrayList<>();
		try {
			transaction = session.beginTransaction();
			Connection connection = ((SessionImpl) session).connection();
			PreparedStatement pre = connection.prepareStatement(SQL.LIST_CAUHOI_OF_MONHOC_WITH_DOKHO_SQL);
			pre.setLong(1, idMonHoc);
			pre.setInt(2, doKho);
			ResultSet res = pre.executeQuery();
			while (res.next())
				list.add(res.getLong("id"));
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

	@Override
	public List<CauHoi> listCauHoi(long... idCauhoi) throws HibernateException {
		Session session = openSession();
		Transaction transaction = null;
		List<CauHoi> listCauHoi = new ArrayList<>();
		try {
			transaction = session.beginTransaction();
			for (int i = 0; i < idCauhoi.length; i++) {
				listCauHoi.add((CauHoi) session.get(CauHoi.class, idCauhoi[i]));
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
		return listCauHoi;
	}

	@Override
	public List<CauHoi> listCauHoi(int sum, long idMonHoc, int doKho) throws Exception {
		List<CauHoi> listCauHoi = new ArrayList<>();
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Long> listID = listIdCauHoi(idMonHoc, doKho);
			if (!listID.isEmpty()) {
				Set<Integer> set = new HashSet<>();
				int j = 0;
				int len = listID.size();
				Random r = new Random();
				CauHoi cauHoiTmp = null;
				for (int i = 0; i < sum; i++) {
					do {
						j = r.nextInt(len);
					} while (!set.add(j));
					cauHoiTmp = (CauHoi) session.get(CauHoi.class, listID.get(j));
					cauHoiTmp.fetchAll();
					listCauHoi.add(cauHoiTmp);
				}
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
		return listCauHoi;
	}

}
