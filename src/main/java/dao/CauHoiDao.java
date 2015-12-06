package dao;

import java.util.List;

import org.hibernate.HibernateException;

import model.CauHoi;

public interface CauHoiDao extends GenericDao<CauHoi, Long> {
	public List<Long> listIdCauHoi(long idMonHoc) throws HibernateException;
	public List<Long> listIdCauHoi(long idMonHoc, int doKho) throws HibernateException, Exception;
	public List<CauHoi> listCauHoi(long... idCauhoi) throws HibernateException;
	public List<CauHoi> listCauHoi(int sum,long idMonHoc, int doKho) throws HibernateException, Exception;
}
