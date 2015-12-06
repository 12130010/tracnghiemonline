package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AccountDao;
import dao.KhoaDao;
import dao.MonHocDao;
import dao.XepHangMonHocDao;
import model.Account;
import model.Khoa;
import model.MonHoc;
import model.XepHangMonHoc;
import model.dto.MyStatus;

@Service
public class ThiThuService {
	@Autowired
	AccountDao accountDao;
	@Autowired
	KhoaDao khoaDao;
	@Autowired
	MonHocDao monHocDao;
	@Autowired
	XepHangMonHocDao xepHangMonHocDao;

	public Account register(Account account) throws Exception {
		return accountDao.register(account);
	}

	public Account login(String username, String password) throws Exception {
		return accountDao.login(username, password);
	}

	public List<Khoa> syncDataKhoa() {
		return khoaDao.getAll();
	}

	public MonHoc getDeThiThu(long idMonHoc, int doKho) throws Exception {
		return monHocDao.getThiThu(idMonHoc, doKho);
	}

	public MyStatus luuDiemThiThu(long idMonHoc, long idAccount, String tenAcc, int doKho, double diem) {
		MyStatus myStatus = new MyStatus();
		try {
			xepHangMonHocDao.submitDiemThiThu(idMonHoc, idAccount, tenAcc, doKho, diem);
			myStatus.setCode(MyStatus.CODE_SUCCESS);
			myStatus.setMessage(MyStatus.MESSAGE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			myStatus.setCode(MyStatus.CODE_FAIL);
			myStatus.setMessage(MyStatus.MESSAGE_FAIL);
		}
		return myStatus;
	}

	public XepHangMonHoc getXepHangThiThu(long idMonHoc, long idAccount, int doKho) {
		try {
			return xepHangMonHocDao.getXepHangMonHoc(idMonHoc, idAccount, doKho);
		} catch (Exception e) {
			e.printStackTrace();
			return new XepHangMonHoc();
		}
	}


	public XepHangMonHoc getXepHangThiThu(long idMonHoc, int doKho) {
		try {
			return xepHangMonHocDao.getXepHangMonHoc(idMonHoc, doKho);
		} catch (Exception e) {
			e.printStackTrace();
			return new XepHangMonHoc();
		}
	}
	public List<XepHangMonHoc> getXepHangThiThu(long idAccount) {
		try {
			return xepHangMonHocDao.listXepHangMonHoc(idAccount);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
