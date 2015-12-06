package zbackup.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class CauHoi {
	@Id
	@GeneratedValue
	private long id;
	private String noiDung;
	@ElementCollection
	@CollectionTable(name="hinh", joinColumns=@JoinColumn(name="cauhoi_id"))
	private List<String> dsHinh = new ArrayList<>();
	private double diem;
	private String giaiThich;
	@OneToMany
	@JoinColumn(name="cauhoi_id")
	private List<DapAn> dsDapAn;
	public CauHoi() {
	}
	public CauHoi(int id, String noiDung, List<String> dsHinh, double diem,
			String giaiThich, List<DapAn> dsDapAn) {
		this.id = id;
		this.noiDung = noiDung;
		this.dsHinh = dsHinh;
		this.diem = diem;
		this.giaiThich = giaiThich;
		this.dsDapAn = dsDapAn;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public List<String> getDsHinh() {
		return dsHinh;
	}
	public void setDsHinh(List<String> dsHinh) {
		this.dsHinh = dsHinh;
	}
	public double getDiem() {
		return diem;
	}
	public void setDiem(double diem) {
		this.diem = diem;
	}
	public String getGiaiThich() {
		return giaiThich;
	}
	public void setGiaiThich(String giaiThich) {
		this.giaiThich = giaiThich;
	}
	public List<DapAn> getDsDapAn() {
		return dsDapAn;
	}
	public void setDsDapAn(List<DapAn> dsDapAn) {
		this.dsDapAn = dsDapAn;
	}
	@Override
	public String toString() {
		return "CauHoi [id=" + id + ", noiDung=" + noiDung + ", dsHinh="
				+ dsHinh + ", diem=" + diem + ", giaiThich=" + giaiThich
				+ ", dsDapAn=" + dsDapAn + "]";
	}
	
	//--------------------------------//
	
	public double getDiemThi(){
		double slgDung = 0, slgChon=0;
		for (DapAn dapAn : dsDapAn) {
			slgDung = slgDung + (dapAn.laDADung() ? 1 : 0); 
			slgChon = slgChon + (dapAn.isSelected() ? 1 : 0);
		}
		slgChon = this.diem * (slgChon/slgDung);
		return slgChon;
	}
}
