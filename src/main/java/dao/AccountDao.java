package dao;

import model.Account;

public interface AccountDao extends GenericDao<Account, Long>{
	public Account login(String username, String password) throws Exception;
	/**
	 * 
	 * @param account
	 * @return Account if success, null if exits username.
	 * @throws Exception
	 */
	public Account register(Account account) throws Exception;
}
