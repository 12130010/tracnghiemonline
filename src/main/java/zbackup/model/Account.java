package zbackup.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
@Entity
public class Account {
	@Id
	@GeneratedValue
	private long id;
	private String tenAcc;
	@Type(type="timestamp")
	private Date ngaySinh;
	private int MSSV;
	private String lop;
	private String username;
	private String password;
	@Transient
	private List<XepHangMonHoc> dsXepHang;
	public Account() {
	}
	public Account(long id, String tenAcc, Date ngaySinh, int mSSV, String lop,
			List<XepHangMonHoc> dsXepHang) {
		this.id = id;
		this.tenAcc = tenAcc;
		this.ngaySinh = ngaySinh;
		this.MSSV = mSSV;
		this.lop = lop;
		this.dsXepHang = dsXepHang;
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
	@Override
	public String toString() {
		return "Account [id=" + id + ", tenAcc=" + tenAcc + ", ngaySinh="
				+ ngaySinh + ", MSSV=" + MSSV + ", lop=" + lop + ", dsXepHang="
				+ dsXepHang + "]";
	}
	
	
}
