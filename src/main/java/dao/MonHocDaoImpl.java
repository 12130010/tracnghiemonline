package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.MonHoc;

public class MonHocDaoImpl extends GenericDaoImpl<MonHoc, Long> implements MonHocDao {
	private CauHoiDao cauHoiDao;
	public CauHoiDao getCauHoiDao() {
		return cauHoiDao;
	}

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
			monHoc.setDsCauHoi(cauHoiDao.listCauHoi(monHoc.getSoLgCauHoi(), idMonHoc, doKho));
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

}
