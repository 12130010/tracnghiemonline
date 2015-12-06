package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;

import model.Account;
import model.DauBang;
import model.XepHangMonHoc;

public class XepHangMonHocDaoImpl implements XepHangMonHocDao {
	private SessionFactory sessionFactory;

	public XepHangMonHocDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void submitDiemThiThu(long idMonHoc, long idAccout, String tenAcc, int doKho, double diem) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String sql = "insert into xephang (idMonHoc,idAccount, tenAcc,doKho,diem) values(:idMonHoc,:idAccount,:tenAcc,:doKho,:diem);";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter("idMonHoc", idMonHoc);
			sqlQuery.setParameter("idAccount", idAccout);
			sqlQuery.setParameter("tenAcc", tenAcc);
			sqlQuery.setParameter("doKho", doKho);
			sqlQuery.setParameter("diem", diem);
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
	public XepHangMonHoc getXepHangMonHoc(long idMonHoc, long idAccount, int doKho) throws Exception {
		XepHangMonHoc xepHangMonHoc = new XepHangMonHoc();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Connection connection = ((SessionImpl) session).connection();
			PreparedStatement pre = connection.prepareStatement(SQL.RANK_THEO_ACCOUNT_AND_MON_SQL);
			pre.setLong(1, idMonHoc);
			pre.setInt(2, doKho);
			pre.setLong(3, idAccount);
			pre.setLong(4, idMonHoc);
			pre.setInt(5, doKho);
			ResultSet res = pre.executeQuery();
			if (res.next()) {
				xepHangMonHoc.setTenMonHoc(res.getString("tenMonHoc"));
				xepHangMonHoc.setXepHang(res.getInt("hang"));
				xepHangMonHoc.setIdMonHoc(res.getLong("idMonHoc"));
				PreparedStatement pre2 = connection.prepareStatement(SQL.LIST_DAUBAN_THEO_MONHOC);
				pre2.setLong(1, idMonHoc);
				pre2.setInt(2, doKho);
				pre2.setLong(3, idMonHoc);
				pre2.setInt(4, doKho);
				ResultSet res2 = pre2.executeQuery();
				while (res2.next()) {
					xepHangMonHoc.addDauBang(
							new DauBang(res2.getString("tenAcc"), res2.getDouble("diem"), res2.getInt("hang")));
				}
				res2.close();
			}
			res.close();
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
		return xepHangMonHoc;
	}
	@Override
	public XepHangMonHoc getXepHangMonHoc(long idMonHoc, int doKho) throws Exception {
		XepHangMonHoc xepHangMonHoc = new XepHangMonHoc();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Connection connection = ((SessionImpl) session).connection();
			PreparedStatement pre = connection.prepareStatement(SQL.GET_TEN_MONHOC);
			pre.setLong(1, idMonHoc);
			ResultSet res = pre.executeQuery();
			if (res.next()) {
				xepHangMonHoc.setTenMonHoc(res.getString("tenMonHoc"));
				xepHangMonHoc.setIdMonHoc(res.getLong("idMonHoc"));
				PreparedStatement pre2 = connection.prepareStatement(SQL.LIST_DAUBAN_THEO_MONHOC);
				pre2.setLong(1, idMonHoc);
				pre2.setInt(2, doKho);
				pre2.setLong(3, idMonHoc);
				pre2.setInt(4, doKho);
				ResultSet res2 = pre2.executeQuery();
				while (res2.next()) {
					xepHangMonHoc.addDauBang(
							new DauBang(res2.getString("tenAcc"), res2.getDouble("diem"), res2.getInt("hang")));
				}
				res2.close();
			}
			res.close();
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
		return xepHangMonHoc;
	}

	@Override
	public List<XepHangMonHoc> listXepHangMonHoc(long idAccount) throws Exception {
		List<XepHangMonHoc> list = new ArrayList<>();
		XepHangMonHoc xepHangMonHoc = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Connection connection = ((SessionImpl) session).connection();
			PreparedStatement pre = connection.prepareStatement(SQL.RANK_THEO_ACCOUNT_SQL);
			pre.setLong(1, idAccount);
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				xepHangMonHoc = new XepHangMonHoc();
				xepHangMonHoc.setTenMonHoc(res.getString("tenMonHoc"));
				xepHangMonHoc.setXepHang(res.getInt("hang"));
				xepHangMonHoc.setIdMonHoc(res.getLong("idMonHoc"));
				/* get list DauBan
				 * PreparedStatement pre2 =
				 * connection.prepareStatement(SQL.LIST_DAUBAN_THEO_MONHOC);
				 * pre2.setLong(1, idMonHoc); pre2.setInt(2, doKho);
				 * pre2.setLong(3, idMonHoc); pre2.setInt(4, doKho); ResultSet
				 * res2 = pre2.executeQuery(); while (res2.next()) {
				 * xepHangMonHoc.addDauBang( new
				 * DauBang(res2.getString("tenAcc"), res2.getDouble("diem"),
				 * res2.getInt("hang"))); } res2.close();
				 */
				list.add(xepHangMonHoc);
			}
			res.close();
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
