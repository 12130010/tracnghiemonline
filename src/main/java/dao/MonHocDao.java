package dao;

import model.MonHoc;

public interface MonHocDao extends GenericDao<MonHoc, Long>{
	public MonHoc getThiThu(long idMonHoc, int doKho) throws Exception;
}
