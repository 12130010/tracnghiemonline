package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AccountDao;
import dao.CauHoiDao;
import dao.KhoaDao;
import dao.MessageDao;
import dao.MonHocDao;
import dao.NganhDao;
import dao.XepHangMonHocDao;
import model.Account;
import model.CauHoi;
import model.Khoa;
import model.MonHoc;
import model.Nganh;
import model.XepHangMonHoc;
import model.dto.MyStatus;

@Service
public class ThiThuService {
	@Autowired
	AccountDao accountDao;
	@Autowired
	KhoaDao khoaDao;
	@Autowired
	NganhDao nganhDao;
	@Autowired
	MonHocDao monHocDao;
	@Autowired
	XepHangMonHocDao xepHangMonHocDao;
	@Autowired
	MessageDao messageDao;
	@Autowired
	CauHoiDao cauHoiDao;
	
	public Account register(Account account) throws Exception {
		return accountDao.register(account);
	}

	public Account login(String username, String password) throws Exception {
		return accountDao.login(username, password);
	}

	public Account login(long idAccount) throws Exception {
		return accountDao.find(idAccount);
	}

	public List<Khoa> syncDataKhoa() {
		return khoaDao.getAll();
	}

	public MonHoc getDeThiThu(long idMonHoc, int doKho) throws Exception {
		return monHocDao.getThiThu(idMonHoc, doKho);
	}
	public MonHoc getMonhoc(long idMonHoc){
		return monHocDao.find(idMonHoc);
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

	public String getNewMessage() throws Exception {
		return messageDao.getNewMessage();
	}

	public List<String> getMessage(int n) throws Exception {
		return messageDao.getNewMessage(n);
	}

	public void changPassword(Account account) throws Exception {
		accountDao.changePassword(account);
	}

	public Account forgetPassword(long idAccount) throws Exception {
		return accountDao.forgetPassword(idAccount);
	}
	/*
	 * quan li admin
	 */

	public void saveOrUpdate(Khoa khoa) {
		khoaDao.saveOrUpdate(khoa);
	}
	public void deleteKhoa(Khoa khoa){
		khoaDao.remove(khoa);
	}

	public void addNganh(Nganh nganh) {
		nganhDao.save(nganh);
	}
	public void updateNganh(Nganh nganh){
		nganhDao.saveOrUpdate(nganh);
	}
	public void deleteNganh(Nganh nganh){
		nganhDao.remove(nganh);
	}
	public void saveOrUpdateMonHoc(MonHoc monHoc, long idNganh){
		if(idNganh > 0)
			monHocDao.save(monHoc, idNganh);
		else
			monHocDao.saveOrUpdate(monHoc);
	}
	public void deleteMonHoc(MonHoc monHoc){
		monHocDao.remove(monHoc);
	}
	public CauHoi getCauHoi(long idCauHoi){
		return cauHoiDao.find(idCauHoi);
	}
}
