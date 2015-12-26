package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.AccountDao;
import dao.AccountDaoImpl;
import dao.CauHoiDao;
import dao.CauHoiDaoImpl;
import dao.KhoaDao;
import dao.KhoaDaoImpl;
import dao.MessageDao;
import dao.MessageDaoImpl;
import dao.MonHocDao;
import dao.MonHocDaoImpl;
import dao.NganhDao;
import dao.NganhDaoImpl;
import dao.XepHangMonHocDao;
import dao.XepHangMonHocDaoImpl;
import model.Account;
import model.CauHoi;
import model.Khoa;
import model.MonHoc;
import model.Nganh;

public class SpringHibernateMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("hibernate4AnnotatedSessionFactory");
		// Session session = sessionFactory.openSession();
		// session.beginTransaction();
		// MonHoc monHoc = new MonHoc("LTCB", 50, 30, "214202");
		// MonHoc monHoc1 = new MonHoc("LTNC", 50, 30, "214202");
		// session.save(monHoc);

		// Nganh nganh = new Nganh("CNTT", "Công nghệ thông tin");
		// nganh.addMonHoc(monHoc);
		// nganh.addMonHoc(monHoc1);
		// Khoa khoa = new Khoa("CNTT", "Khoa Công nghệ thông tin");
		// khoa.addNganh(nganh);
		// Account account = (Account) session.get(Account.class, 2l);
		// System.out.println(account);
		// account.addKhoa(khoa);
		// System.out.println(khoa);
		// long khoaid = (long) session.save(khoa);
		// session.flush();
		// nganh = (Nganh) session.get(Nganh.class, Long.valueOf(k));
		// khoa = (Khoa) session.get(Khoa.class, khoaid);
		// System.out.println(khoaid);
		// Account account = new Account("Hoang Nhuoc Quy", new Date(),
		// 12130010, "DH12DT", "hoangnhuocquy", "123123",
		// khoaid, khoa.getDsNganh().get(0).getId());
		// session.save(account);
		// session.flush();
		// khoa.add(nganh);
		// session.saveOrUpdate(khoa);
		// nganh.addMonHoc(monHoc);
		// session.save(nganh);
		// System.out.println(session.get(Nganh.class, Long.valueOf(5l)));
		// Account ac = (Account) session.get(Account.class, 3l);
		// System.out.println(ac);
		// session.getTransaction().commit();
		// session.close();
		// AccountDao accountDao = new AccountDaoImpl(sessionFactory);
		// try {
		// System.out.println(accountDao.login("username1", "password"));
		//// System.out.println(accountDao
		//// .register(new Account("NhuocQuy", new Date(), 12130329, "DH12DT",
		// "username1", "password", 7, 5)));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// CauHoiDao cauHoiDao = new CauHoiDaoImpl(sessionFactory);
		// System.out.println(cauHoiDao.listCauHoi(2, 9l, 1));
		// KhoaDao khoaDao = new KhoaDaoImpl(sessionFactory);
		// List<Khoa> listKhoa = khoaDao.getAll();
		// System.out.println(listKhoa);
		// XepHangMonHocDao xepHangMonHocDao = new
		// XepHangMonHocDaoImpl(sessionFactory);
		// xepHangMonHocDao.submitDiem(9l, 3, "Hoang Nhuoc Quy", 1, 9);
		
		// XepHangMonHocDao xepHangMonHocDao = new
		// XepHangMonHocDaoImpl(sessionFactory);
		// try {
		// System.out.println(xepHangMonHocDao.getXepHangMonHoc(9l, 1));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// System.out.println(xepHangMonHocDao.listXepHangMonHoc(3l));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
//		MessageDao messageDao = new MessageDaoImpl(sessionFactory);
//		try {
//			System.out.println(messageDao.getNewMessage());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		CauHoiDaoImpl cauHoiDao = new CauHoiDaoImpl();
		cauHoiDao.setSessionFactory(sessionFactory);
		ArrayList<String> dsHinh = new ArrayList<>();
		dsHinh.add("hinh1.png");
		dsHinh.add("hinh2.png");
		cauHoiDao.save(new CauHoi("aaaaaaaaa",dsHinh,"bbbbbbbb",1,true),9);
		context.close();
	}
}
