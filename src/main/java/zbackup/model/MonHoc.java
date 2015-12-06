package zbackup.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
@Entity
public class MonHoc {
	@Id
	@GeneratedValue
	private long id;
	private String tenMonHoc;
	private Date thoiGian;
	private int soLgCauHoi;
	private String maMH;
	@OneToMany
	@JoinColumn(name="monhoc_id")
	private List<CauHoi> dsCauHoi;
	public MonHoc() {
	}
	public MonHoc(long id, String tenMonHoc, Date thoiGian, int soLgCauHoi,
			String maMH, List<CauHoi> dsCauHoi) {
		this.id = id;
		this.tenMonHoc = tenMonHoc;
		this.thoiGian = thoiGian;
		this.soLgCauHoi = soLgCauHoi;
		this.maMH = maMH;
		this.dsCauHoi = dsCauHoi;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public Date getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	public int getSoLgCauHoi() {
		return soLgCauHoi;
	}
	public void setSoLgCauHoi(int soLgCauHoi) {
		this.soLgCauHoi = soLgCauHoi;
	}
	public String getMaMH() {
		return maMH;
	}
	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}
	public List<CauHoi> getDsCauHoi() {
		return dsCauHoi;
	}
	public void setDsCauHoi(List<CauHoi> dsCauHoi) {
		this.dsCauHoi = dsCauHoi;
	}
	@Override
	public String toString() {
		return "Monhoc [id=" + id + ", tenMonHoc=" + tenMonHoc + ", thoiGian="
				+ thoiGian + ", soLgCauHoi=" + soLgCauHoi + ", maMH=" + maMH
				+ ", dsCauHoi=" + dsCauHoi + "]";
	}
	
	
	//--------------------------------//
	
	public double getDiemThi(){
		double diemThi = 0;
		for (CauHoi cauHoi : dsCauHoi) {
			diemThi += cauHoi.getDiemThi();
					}
		return diemThi;
	}
}
