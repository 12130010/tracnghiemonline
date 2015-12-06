package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

@Entity
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
	@Id
	@GeneratedValue
	private long id;
	private String tenAcc;
	@Type(type = "timestamp")
	private Date ngaySinh;
	private int MSSV;
	private String lop;
	private String username;
	private String password;
	@Transient
	private List<XepHangMonHoc> dsXepHang = new ArrayList<>();;
	private long khoaID;
	private long nganhID;
	@Transient
	private Khoa khoa;
	@Transient
	private Nganh nganh;

	public Account() {
	}

	public Account(String tenAcc, Date ngaySinh, int mSSV, String lop, String username, String password, long khoaID,
			long nganhID) {
		super();
		this.tenAcc = tenAcc;
		this.ngaySinh = ngaySinh;
		MSSV = mSSV;
		this.lop = lop;
		this.username = username;
		this.password = password;
		this.khoaID = khoaID;
		this.nganhID = nganhID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTenAcc() {
		return tenAcc;
	}

	public void setTenAcc(String tenAcc) {
		this.tenAcc = tenAcc;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public int getMSSV() {
		return MSSV;
	}

	public void setMSSV(int mSSV) {
		MSSV = mSSV;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public List<XepHangMonHoc> getDsXepHang() {
		return dsXepHang;
	}

	public void setDsXepHang(List<XepHangMonHoc> dsXepHang) {
		this.dsXepHang = dsXepHang;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getKhoaID() {
		return khoaID;
	}

	public void setKhoaID(long khoaID) {
		this.khoaID = khoaID;
	}

	public long getNganhID() {
		return nganhID;
	}

	public void setNganhID(long nganhID) {
		this.nganhID = nganhID;
	}

	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}

	public Nganh getNganh() {
		return nganh;
	}

	public void setNganh(Nganh nganh) {
		this.nganh = nganh;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", tenAcc=" + tenAcc + ", ngaySinh=" + ngaySinh + ", MSSV=" + MSSV + ", lop=" + lop
				+ ", username=" + username + ", password=" + password + ", dsXepHang=" + dsXepHang + ", khoaID="
				+ khoaID + ", nganhID=" + nganhID + ", khoa=" + khoa + ", nganh=" + nganh + "]";
	}

}
