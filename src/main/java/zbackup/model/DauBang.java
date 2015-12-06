package zbackup.model;

public class DauBang {

	private long id;
	private String ten;
	private double diem;
	public DauBang() {
	}
	public DauBang(long id, String ten, double diem) {
		this.id = id;
		this.ten = ten;
		this.diem = diem;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public double getDiem() {
		return diem;
	}
	public void setDiem(double diem) {
		this.diem = diem;
	}
	@Override
	public String toString() {
		return "DauBang [id=" + id + ", ten=" + ten + ", diem=" + diem + "]";
	}
	
	
}
