package zbackup.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Khoa {
	@Id
	@GeneratedValue
	private long id;
	private String maKhoa;
	private String tenKhoa;
	@OneToMany
	@JoinColumn(name="khoa_id")
	private List<Nganh> dsNganh;
	public Khoa() {
	}
	public Khoa(long id, String maKhoa, String tenKhoa, List<Nganh> dsNganh) {
		this.id = id;
		this.maKhoa = maKhoa;
		this.tenKhoa = tenKhoa;
		this.dsNganh = dsNganh;
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

	@Override
	public String toString() {
		return "Khoa [id=" + id + ", maKhoa=" + maKhoa + ", tenKhoa=" + tenKhoa
				+ ", dsNganh=" + dsNganh + "]";
	}
	
	

}
