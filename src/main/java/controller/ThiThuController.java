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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Account;
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

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

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
	public String qlNganhSelectNganh(@RequestParam int indexkhoa, HttpSession session) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		session.setAttribute(SELECT_KHOA, listKhoa.get(indexkhoa));
		return "qlnganh-tablenganh";
	}

	@RequestMapping("qlmonhocselectnganh")
	public String qlMonHocSelectNganh(@RequestParam int indexkhoa, HttpSession session) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		session.setAttribute(SELECT_KHOA_FOR_MONHOC, listKhoa.get(indexkhoa));
		return "qlmonhoc-selectnganh";
	}

	@RequestMapping("qlmonhoctablemonhoc")
	public String qlMonHocTableMonHoc(@RequestParam int indexnganh, @RequestParam int indexkhoa, HttpSession session) {
		List<Khoa> listKhoa = (List<Khoa>) session.getAttribute(LIST_KHOA);
		Khoa khoa = listKhoa.get(indexkhoa);
		session.setAttribute(SELECT_NGANH, khoa.getDsNganh().get(indexnganh));
		return "qlmonhoc-tablemonhoc";
	}

	@RequestMapping(value = "addkhoa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody String addKhoa(Khoa khoa) {
		System.out.println(khoa);
		try {
			thiThuService.addKhoa(khoa);
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
		khoa.addNganh(nganh);
		try {
//			thiThuService.addNganh(nganh);
			thiThuService.updateKhoa(khoa);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
}
