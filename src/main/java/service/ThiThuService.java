package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AccountDao;
import model.Account;

@Service
public class ThiThuService {
	@Autowired
	AccountDao accountDao;
	public Account register(Account account) throws Exception{
		return accountDao.register(account);
	}
	public Account  login(String username, String password) throws Exception{
		return accountDao.login(username, password);
	}
}
