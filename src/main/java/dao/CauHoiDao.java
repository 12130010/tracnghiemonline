package dao;

import java.util.List;

import org.hibernate.HibernateException;

import model.CauHoi;
import model.MonHoc;

public interface CauHoiDao extends GenericDao<CauHoi, Long> {
	public List<Long> listIdCauHoi(long idMonHoc) throws HibernateException;
	public List<CauHoi> listIdCauHois(long idMonHoc) throws HibernateException;
	public List<Long> listIdCauHoi(long idMonHoc, int doKho) throws HibernateException, Exception;
	public List<CauHoi> listCauHoi(long... idCauhoi) throws HibernateException;
	/**
	 * thi thu.
	 * @param sum
	 * @param idMonHoc
	 * @param doKho
	 * @return
	 * @throws HibernateException
	 * @throws Exception
	 */
	public List<CauHoi> listCauHoi(int sum,long idMonHoc, int doKho) throws HibernateException, Exception;
	long save(CauHoi cauHoi, long idMonHoc);
}
