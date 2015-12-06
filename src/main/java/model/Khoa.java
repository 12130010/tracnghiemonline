package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Khoa {
	@Id
	@GeneratedValue
	private long id;
	private String maKhoa;
	private String tenKhoa;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "khoa_id")
	private List<Nganh> dsNganh = new ArrayList<>();;

	public Khoa() {
	}

	public Khoa(String maKhoa, String tenKhoa) {
		super();
		this.maKhoa = maKhoa;
		this.tenKhoa = tenKhoa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}

	public List<Nganh> getDsNganh() {
		return dsNganh;
	}

	public void setDsNganh(List<Nganh> dsNganh) {
		this.dsNganh = dsNganh;
	}

	public void addNganh(Nganh nganh) {
		dsNganh.add(nganh);
	}

	public void fetchAllNganh() {
		dsNganh.size();
	}

	@Override
	public String toString() {
		return "Khoa [id=" + id + ", maKhoa=" + maKhoa + ", tenKhoa=" + tenKhoa + ", dsNganh=" + dsNganh + "]";
	}

}
