package dao;

import java.util.List;

import model.XepHangMonHoc;

public interface XepHangMonHocDao {
	public void submitDiemThiThu(long idMonHoc, long idAccout,String tenAcc, int doKho, double diem );
	public XepHangMonHoc getXepHangMonHoc(long idMonHoc, long idAccount, int doKho) throws Exception;
	public XepHangMonHoc getXepHangMonHoc(long idMonHoc,  int doKho) throws Exception;
	public List<XepHangMonHoc> listXepHangMonHoc(long idAccount) throws Exception;
}
