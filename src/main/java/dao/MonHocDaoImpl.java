package dao;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MonHoc;
@Repository
public class MonHocDaoImpl extends GenericDaoImpl<MonHoc, Long> implements MonHocDao {
	private CauHoiDao cauHoiDao;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	public void setCauHoiDao(CauHoiDao cauHoiDao) {
		this.cauHoiDao = cauHoiDao;
	}

	@Override
	public MonHoc getThiThu(long idMonHoc, int doKho) throws Exception {
		MonHoc monHoc = null;
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			monHoc = (MonHoc) session.get(MonHoc.class, idMonHoc);
			if (monHoc != null){
				monHoc.setDsCauHoi(cauHoiDao.listCauHoi(monHoc.getSoLgCauHoi(), idMonHoc, doKho));
				monHoc.setDoKho(doKho);
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
		return monHoc;
	}

	@Override
	public Long save(MonHoc monHoc, long idNganh) throws HibernateException {
		Session session = openSession();
		Transaction transaction = null;
		Long idMH = 0l;
		try {
			transaction = session.beginTransaction();
			idMH = (Long) session.save(monHoc);
			SQLQuery sqlQuery = session.createSQLQuery(SQL.INSERT_MONHOC_REF_NGANH);
			sqlQuery.setParameter("Nganh_id", idNganh);
			sqlQuery.setParameter("dsMonHoc_id", idMH);
			sqlQuery.executeUpdate();
			monHoc.setId(idMH);
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
		return idMH;
	}

}
