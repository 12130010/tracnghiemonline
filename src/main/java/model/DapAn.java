package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DapAn {
	@Id
	@GeneratedValue
	private long id;
	private int thuTu;
	private String noiDungDA;
	private String hinh;
	private boolean laDADung;
	private boolean isSelected;
	public DapAn() {
	}
	public DapAn(long id, int thuTu, String noiDungDA, String hinh,
			boolean laDADung, boolean isSelected) {
		this.id = id;
		this.thuTu = thuTu;
		this.noiDungDA = noiDungDA;
		this.hinh = hinh;
		this.laDADung = laDADung;
		this.isSelected = isSelected;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getThuTu() {
		return thuTu;
	}
	public void setThuTu(int thuTu) {
		this.thuTu = thuTu;
	}
	public String getNoiDungDA() {
		return noiDungDA;
	}
	public void setNoiDungDA(String noiDungDA) {
		this.noiDungDA = noiDungDA;
	}
	public String getHinh() {
		return hinh;
	}
	public void setHinh(String hinh) {
		this.hinh = hinh;
	}
	public boolean laDADung() {
		return laDADung;
	}
	public void setLaDADung(boolean laDADung) {
		this.laDADung = laDADung;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	@Override
	public String toString() {
		return "DapAn [id=" + id + ", thuTu=" + thuTu + ", noiDungDA="
				+ noiDungDA + ", hinh=" + hinh + ", laDADung=" + laDADung
				+ ", isSelected=" + isSelected + "]";
	}
	
}
