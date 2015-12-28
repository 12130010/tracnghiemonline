package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import util.ToString;
@Entity
@Table(name="cauhoi")
public class CauHoi {
	@Transient
	public static final int EASY = 1;
	@Transient
	public static final int MEDIUM = 2;
	@Transient
	public static final int HARD = 3;
	@Id
	@GeneratedValue
	private long id;
	@Type(type="text")
	private String noiDung;
	@ElementCollection()
	@CollectionTable(name="hinh", joinColumns=@JoinColumn(name="cauhoi_id"))
	private List<String> dsHinh = new ArrayList<>();
	@Type(type="text")
	private String giaiThich;
	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn(name="cauhoi_id")
	private List<DapAn> dsDapAn  = new ArrayList<>();
	private int doKho;
	private boolean display;
	public CauHoi() {
	}
	
	

	public CauHoi(String noiDung, List<String> dsHinh, String giaiThich, int doKho, boolean display) {
		super();
		this.noiDung = noiDung;
		this.dsHinh = dsHinh;
		this.giaiThich = giaiThich;
		this.doKho = doKho;
		this.display = display;
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
	
	public int getDoKho() {
		return doKho;
	}
	public void setDoKho(int doKho) {
		this.doKho = doKho;
	}
	
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
	@Override
	public String toString() {
		return "CauHoi [id=" + id + ", noiDung=" + noiDung + ", dsHinh=" + dsHinh + ", giaiThich=" + giaiThich
				+ ", dsDapAn=" + dsDapAn + ", doKho=" + doKho + "]";
	}
	public void fetchAll(){
		dsDapAn.size();
		dsHinh.size();
	}
	//--------------------------------//
	public double getDiemThi(double diem){
		double slgDung = 0, slgChon=0;
		for (DapAn dapAn : dsDapAn) {
			slgDung = slgDung + (dapAn.isLaDADung() ? 1 : 0); 
			slgChon = slgChon + (dapAn.isSelected() ? 1 : 0);
		}
		slgChon = diem * (slgChon/slgDung);
		return slgChon;
	}
	public String convertHinhToTagA(){
		return ToString.toStringImage(dsHinh);
	}
}
