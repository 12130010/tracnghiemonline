package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Account;
import model.CauHoi;
import model.Khoa;
import model.MonHoc;
import model.Nganh;
import model.XepHangMonHoc;
import model.dto.MyStatus;
import service.ThiThuService;

@Controller
public class ThiThuController {
	private static final int BUFFER_SIZE = 4096;
	public static final String LIST_KHOA = "listKhoa";
	public static final String SELECT_KHOA = "selectKhoa";
	public static final String SELECT_KHOA_FOR_MONHOC = "selectKhoaForMonHoc";
	public static final String SELECT_NGANH = "selectNganh";
	public static final String SELECT_NGANH_FOR_CAUHOI = "selectNganhForCauHoi";
	public static final String SELECT_MONHOC_FOR_CAUHOI = "selectMonHocForCauHoi";
	public static final String ADD_CAUHOI_KHOA = "addCauHoiKhoa";
	public static final String ADD_CAUHOI_KHOA_INDEX = "addCauHoiKhoaIndex";
	public static final String ADD_CAUHOI_NGANH = "addCauHoiNganh";
	public static final String ADD_CAUHOI_NGANH_INDEX = "addCauHoiNganhIndex";
	public static final String ADD_CAUHOI_MONHOC = "addCauHoiMonHoc";
	public static final String ADD_CAUHOI_MONHOC_INDEX = "addCauHoiMonHocIndex";
	public static final String ADD_CAUHOI_CAUHOI = "addCauHoiCauHoi";

	@Autowired
	public String IMAGE_DIR;
	@Autowired
	ThiThuService thiThuService;

	@RequestMapping(value = "/")
	public String home(HttpSession session) {
		List<Khoa> listKhoa = thiThuService.syncDataKhoa();
		session.setAttribute(LIST_KHOA, listKhoa);
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MyStatus register(@RequestBody Account account) {
		MyStatus myStatus = new MyStatus();
		try {
			if (thiThuService.register(account) != null) {
				myStatus.setCode(MyStatus.CODE_SUCCESS);
				myStatus.setMessage(MyStatus.MESSAGE_SUCCESS);
			} else {
				myStatus.setCode(MyStatus.CODE_FAIL);
				myStatus.setMessage(MyStatus.MESSAGE_FAIL);
				myStatus.setObj("Username đã tồn tại!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return myStatus;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account login(@RequestParam(defaultValue = "") String username,
			@RequestParam(defaultValue = "") String password, @RequestParam(defaultValue = "-1") long idAccount) {
		try {
			if (idAccount == -1)
				return thiThuService.login(username, password);
			return thiThuService.login(idAccount);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/syncdatakhoa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Khoa> syncDataKhoa() {
		return thiThuService.syncDataKhoa();
	}

	@RequestMapping(value = "/thithu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MonHoc thiThu(@RequestParam(defaultValue = "0") long idMonHoc,
			@RequestParam(defaultValue = "1") int doKho) {
		try {
			return thiThuService.getDeThiThu(idMonHoc, doKho);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/luudiemthithu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MyStatus luuDiemThiThu(@RequestParam long idMonHoc,
			@RequestParam(defaultValue = "0") long idAccount, @RequestParam String tenAcc, @RequestParam int doKho,
			@RequestParam double diem) {
		return thiThuService.luuDiemThiThu(idMonHoc, idAccount, tenAcc, doKho, diem);
	}

	@RequestMapping(value = "/getxephangthithu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody XepHangMonHoc getXepHangThiThu(@RequestParam(defaultValue = "-1") long idMonHoc,
			@RequestParam(defaultValue = "-1") long idAccount, @RequestParam(defaultValue = "-1") int doKho) {
		if (idAccount == -1) {
			return thiThuService.getXepHangThiThu(idMonHoc, doKho);
		} else {
			return thiThuService.getXepHangThiThu(idMonHoc, idAccount, doKho);
		}
	}

	@RequestMapping(value = "/listxephangthithu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<XepHangMonHoc> listxephangthithu(@RequestParam(defaultValue = "-1") long idAccount) {
		return thiThuService.getXepHangThiThu(idAccount);
	}

	@RequestMapping(value = "/getnewmessage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MyStatus getNewMessage(@RequestParam(defaultValue = "-1") int n) {
		MyStatus myStatus = new MyStatus();
		try {
			myStatus.setCode(MyStatus.CODE_SUCCESS);
			myStatus.setMessage(MyStatus.MESSAGE_SUCCESS);
			if (n == -1)
				myStatus.setObj(thiThuService.getNewMessage());
			else
				myStatus.setObj(thiThuService.getMessage(n));
		} catch (Exception e) {
			myStatus.setCode(MyStatus.CODE_FAIL);
			myStatus.setMessage(MyStatus.MESSAGE_FAIL);
			if (n == -1)
				myStatus.setObj("");
			else
				myStatus.setObj(new ArrayList<>());
		}
		return myStatus;
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MyStatus changPassword(@RequestBody Account account) {
		MyStatus myStatus = new MyStatus();
		try {
			myStatus.setCode(MyStatus.CODE_SUCCESS);
			myStatus.setMessage(MyStatus.MESSAGE_SUCCESS);
			thiThuService.changPassword(account);
		} catch (Exception e) {
			myStatus.setCode(MyStatus.CODE_FAIL);
			myStatus.setMessage(MyStatus.MESSAGE_FAIL);
		}
		return myStatus;
	}

	@RequestMapping(value = "/forgetpassword", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody MyStatus forgetPassword(long idAccount) {
		MyStatus myStatus = new MyStatus();
		try {
			myStatus.setCode(MyStatus.CODE_SUCCESS);
			myStatus.setMessage(MyStatus.MESSAGE_SUCCESS);
			Account account = thiThuService.forgetPassword(idAccount);
			// TODO send password to mail
			System.out.println(account);
		} catch (Exception e) {
			myStatus.setCode(MyStatus.CODE_FAIL);
			myStatus.setMessage(MyStatus.MESSAGE_FAIL);
		}
		return myStatus;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String fileName) throws IOException {

		String fullPath = IMAGE_DIR + fileName;
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = request.getSession().getServletContext().getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response can download file
		// String headerKey = "Content-Disposition";
		// String headerValue = String.format("attachment; filename=\"%s\"",
		// downloadFile.getName());
		// response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
	}

	/*
	 * admin quan li
	 */
	@RequestMapping("qlnganhtablenganh")
	public String qlNganhSelectNganh(@RequestParam int indexkhoa, HttpSession session, Model model) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		model.addAttribute(SELECT_KHOA, listKhoa.get(indexkhoa));
		return "qlnganh-tablenganh";
	}

	@RequestMapping("qlmonhocselectnganh")
	public String qlMonHocSelectNganh(@RequestParam int indexkhoa, HttpSession session, Model model) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		model.addAttribute(SELECT_KHOA_FOR_MONHOC, listKhoa.get(indexkhoa));
		return "qlmonhoc-selectnganh";
	}

	@RequestMapping("qlmonhoctablemonhoc")
	public String qlMonHocTableMonHoc(@RequestParam int indexnganh, @RequestParam int indexkhoa, HttpSession session,
			Model model) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		Khoa khoa = listKhoa.get(indexkhoa);
		model.addAttribute(SELECT_NGANH, khoa.getDsNganh().get(indexnganh));
		return "qlmonhoc-tablemonhoc";
	}

	@RequestMapping("qlcauhoiselectmonhoc")
	public String qlCauHoiSelectMonHoc(int indexkhoa, int indexnganh, HttpSession session, Model model) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		Khoa khoa = listKhoa.get(indexkhoa);
		Nganh nganh = khoa.getDsNganh().get(indexnganh);
		model.addAttribute(SELECT_NGANH_FOR_CAUHOI, nganh);
		return "qlcauhoi-selectmonhoc";
	}

	@RequestMapping(value = "addkhoa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody String addKhoa(Khoa khoa) {
		System.out.println(khoa);
		try {
			thiThuService.saveOrUpdate(khoa);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@RequestMapping(value = "addnganh", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody String addNganh(Nganh nganh, int indexKhoa, HttpSession session) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		Khoa khoa = listKhoa.get(indexKhoa);
		try {
			// thiThuService.addNganh(nganh);
			if (nganh.getId() == 0) {
				khoa.addNganh(nganh);
				thiThuService.saveOrUpdate(khoa);
			} else {
				thiThuService.updateNganh(nganh);
				khoa.getDsNganh().remove(nganh);
				khoa.getDsNganh().add(nganh);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@RequestMapping(value = "deletekhoa")
	public @ResponseBody String deleteKhoa(int indexKhoa, HttpSession session) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		Khoa khoa = listKhoa.get(indexKhoa);
		if (!khoa.getDsNganh().isEmpty()) {
			return "Khoa " + khoa.getTenKhoa() + " còn chứa danh sách ngành nên không thể xóa!!!";
		} else {
			try {
				thiThuService.deleteKhoa(khoa);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}
	}

	@RequestMapping(value = "deletenganh")
	public @ResponseBody String deleteNganh(int indexKhoa, int indexNganh, HttpSession session) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		Khoa khoa = listKhoa.get(indexKhoa);
		Nganh nganh = khoa.getDsNganh().get(indexNganh);
		if (!nganh.getDsMonHoc().isEmpty()) {
			return "Ngành" + nganh.getTenNganh() + " còn chứa danh sách môn học nên không thể xóa!!!";
		} else {
			try {
				thiThuService.deleteNganh(nganh);
				khoa.getDsNganh().remove(nganh);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}
	}

	@RequestMapping(value = "addmonhoc", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody String addMonHoc(MonHoc monHoc, int indexKhoa, int indexNganh, HttpSession session) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		Khoa khoa = listKhoa.get(indexKhoa);
		Nganh nganh = khoa.getDsNganh().get(indexNganh);
		System.out.println(monHoc);
		try {
			if (monHoc.getId() == 0) {
				thiThuService.saveOrUpdateMonHoc(monHoc, nganh.getId());
				nganh.addMonHoc(monHoc);
			} else {
				thiThuService.saveOrUpdateMonHoc(monHoc, -1);
				nganh.getDsMonHoc().remove(monHoc);
				nganh.addMonHoc(monHoc);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@RequestMapping(value = "deletemonhoc")
	public @ResponseBody String deleteMonHoc(int indexKhoa, int indexNganh, int indexMonHoc, HttpSession session) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		Khoa khoa = listKhoa.get(indexKhoa);
		Nganh nganh = khoa.getDsNganh().get(indexNganh);
		MonHoc monHoc = nganh.getDsMonHoc().get(indexMonHoc);
		try {
			nganh.getDsMonHoc().remove(monHoc);
			thiThuService.updateNganh(nganh);
			;
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			nganh.addMonHoc(monHoc);
			return "fail";
		}
	}

	

	@RequestMapping("qlcauhoitablecauhoi")
	public String qlCauHoiTableCauHoi(int indexkhoa, int indexnganh, int indexmonhoc, HttpSession session,
			Model model) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		Khoa khoa = listKhoa.get(indexkhoa);
		Nganh nganh = khoa.getDsNganh().get(indexnganh);
		MonHoc monHoc = nganh.getDsMonHoc().get(indexmonhoc);
		monHoc = thiThuService.getMonhoc(monHoc.getId());
		model.addAttribute(SELECT_MONHOC_FOR_CAUHOI, monHoc);
		return "qlcauhoi-tablecauhoi";
	}
	@RequestMapping("qlcauhoiaddcauhoi")
	public String qlCauHoiAddCauHoi(int indexkhoa, int indexnganh, int indexmonhoc,
			@RequestParam(defaultValue = "-1") long idCauHoi, HttpSession session, Model model) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		Khoa khoa = listKhoa.get(indexkhoa);
		Nganh nganh = khoa.getDsNganh().get(indexnganh);
		MonHoc monHoc = nganh.getDsMonHoc().get(indexmonhoc);
		if (idCauHoi >= 0) {
			CauHoi cauHoi = thiThuService.getCauHoi(idCauHoi);
			model.addAttribute(ADD_CAUHOI_CAUHOI, cauHoi);
		}
		model.addAttribute(ADD_CAUHOI_KHOA, khoa);
		model.addAttribute(ADD_CAUHOI_KHOA_INDEX, indexkhoa);
		model.addAttribute(ADD_CAUHOI_NGANH, nganh);
		model.addAttribute(ADD_CAUHOI_NGANH_INDEX, indexnganh);
		model.addAttribute(ADD_CAUHOI_MONHOC, monHoc);
		model.addAttribute(ADD_CAUHOI_MONHOC_INDEX, indexmonhoc);
		return "qlcauhoi-addcauhoi";
	}
	@RequestMapping("addcauhoi")
	public String addCauHoi(int indexkhoa, int indexnganh, int indexmonhoc,
			CauHoi cauHoi, HttpSession session, Model model){
		cauHoi.setNoiDung(new String(cauHoi.getNoiDung().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8")));
		cauHoi.setGiaiThich(new String(cauHoi.getGiaiThich().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8")));
		System.out.println(cauHoi);
		return "redirect:/";
	}
}
