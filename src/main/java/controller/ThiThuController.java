package controller;

import java.util.ArrayList;
import java.util.List;

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
import model.XepHangMonHoc;
import model.dto.MyStatus;
import service.ThiThuService;

@Controller
public class ThiThuController {
	@Autowired
	ThiThuService thiThuService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MyStatus register(@RequestBody Account account) {
		MyStatus myStatus = new MyStatus();
		try {
			if( thiThuService.register(account) != null){
				myStatus.setCode(MyStatus.CODE_SUCCESS);
				myStatus.setMessage(MyStatus.MESSAGE_SUCCESS);
			}else{
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
			@RequestParam(defaultValue = "0") int doKho) {
		try {
			return thiThuService.getDeThiThu(idMonHoc, doKho);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/luudiemthithu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MyStatus luuDiemThiThu(@RequestParam long idMonHoc, @RequestParam long idAccount,
			@RequestParam String tenAcc, @RequestParam int doKho, @RequestParam double diem) {
		return thiThuService.luuDiemThiThu(idMonHoc, idAccount, tenAcc, doKho, diem);
	}

	@RequestMapping(value = "getxephangthithu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody XepHangMonHoc getXepHangThiThu(@RequestParam(defaultValue = "-1") long idMonHoc,
			@RequestParam(defaultValue = "-1") long idAccount, @RequestParam(defaultValue = "-1") int doKho) {
		if (idAccount == -1) {
			return thiThuService.getXepHangThiThu(idMonHoc, doKho);
		} else {
			return thiThuService.getXepHangThiThu(idMonHoc, idAccount, doKho);
		}
	}

	@RequestMapping(value = "listxephangthithu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
}
