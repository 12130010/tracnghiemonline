package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Nganh {
	@Id
	@GeneratedValue
	private long id;
	private String maNganh;
	private String tenNganh;
//	@JoinColumn(name="nganh_id")
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<MonHoc> dsMonHoc = new ArrayList<>();

	public Nganh() {
	}

	public Nganh(String maNganh, String tenNganh) {
		super();
		this.maNganh = maNganh;
		this.tenNganh = tenNganh;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getMaNganh() {
		return maNganh;
	}

	public void setMaNganh(String maNganh) {
		this.maNganh = maNganh;
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
	public void addMonHoc(MonHoc monHoc){
		dsMonHoc.add(monHoc);
	}
	@Override
	public boolean equals(Object obj) {
		if( obj == null || !(obj instanceof Nganh))
			return false;
		Nganh that = (Nganh) obj;
		return this.id == that.id;
	}
	@Override
	public String toString() {
		return "Nganh [id=" + id + ", maHoc=" + maNganh + ", tenNganh=" + tenNganh + ", dsMonHoc=" + dsMonHoc + "]";
	}

}
