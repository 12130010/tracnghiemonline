package dao;

import org.hibernate.HibernateException;

import model.MonHoc;

public interface MonHocDao extends GenericDao<MonHoc, Long>{
	public MonHoc getThiThu(long idMonHoc, int doKho) throws Exception;
	public Long save(MonHoc monHoc, long idNganh) throws HibernateException;
}
