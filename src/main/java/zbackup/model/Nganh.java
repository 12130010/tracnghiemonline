package zbackup.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Nganh {
	@Id
	@GeneratedValue
	private long id;
	private String maHoc;
	private String tenNganh;
	@OneToMany
	@JoinColumn(name="nganh_id")
	private List<MonHoc> dsMonHoc;

	public Nganh() {
	}

	public Nganh(long id, String maHoc, String tenNganh, List<MonHoc> dsMonHoc) {
		this.id = id;
		this.maHoc = maHoc;
		this.tenNganh = tenNganh;
		this.dsMonHoc = dsMonHoc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMaHoc() {
		return maHoc;
	}

	public void setMaHoc(String maHoc) {
		this.maHoc = maHoc;
	}

	public String getTenNganh() {
		return tenNganh;
	}

	public void setTenNganh(String tenNganh) {
		this.tenNganh = tenNganh;
	}

	public List<MonHoc> getDsMonHoc() {
		return dsMonHoc;
	}

	public void setDsMonHoc(List<MonHoc> dsMonHoc) {
		this.dsMonHoc = dsMonHoc;
	}

	@Override
	public String toString() {
		return "Nganh [id=" + id + ", maHoc=" + maHoc + ", tenNganh=" + tenNganh + ", dsMonHoc=" + dsMonHoc + "]";
	}

}
