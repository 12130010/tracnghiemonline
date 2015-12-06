package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Account;
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
	public @ResponseBody  Account login(@RequestParam(defaultValue="") String username,@RequestParam(defaultValue="") String password) {
		try {
			return thiThuService.login(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
