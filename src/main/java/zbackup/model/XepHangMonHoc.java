package zbackup.model;

import java.util.List;

public class XepHangMonHoc {
private long id;
private String tenMonHoc;
private int xepHang;
private List<DauBang> dsDauBang;
public XepHangMonHoc() {
}
public XepHangMonHoc(long id, String tenMonHoc, int xepHang,
		List<DauBang> dsDauBang) {
	this.id = id;
	this.tenMonHoc = tenMonHoc;
	this.xepHang = xepHang;
	this.dsDauBang = dsDauBang;
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
public int getXepHang() {
	return xepHang;
}
public void setXepHang(int xepHang) {
	this.xepHang = xepHang;
}
public List<DauBang> getDsDauBang() {
	return dsDauBang;
}
public void setDsDauBang(List<DauBang> dsDauBang) {
	this.dsDauBang = dsDauBang;
}
@Override
public String toString() {
	return "XepHangMonHoc [id=" + id + ", tenMonHoc=" + tenMonHoc
			+ ", xepHang=" + xepHang + ", dsDauBang=" + dsDauBang + "]";
}

}
