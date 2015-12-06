package controller;

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
	public @ResponseBody Account register(@RequestBody Account account) {
		try {
			return thiThuService.register(account);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account login(@RequestParam(defaultValue = "") String username,
			@RequestParam(defaultValue = "") String password) {
		try {
			return thiThuService.login(username, password);
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
}
