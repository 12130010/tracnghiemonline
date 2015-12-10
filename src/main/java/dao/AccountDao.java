package dao;

import model.Account;

public interface AccountDao extends GenericDao<Account, Long> {
	public Account login(String username, String password) throws Exception;

	/**
	 * 
	 * @param account
	 * @return Account if success, null if exits username.
	 * @throws Exception
	 */
	public Account register(Account account) throws Exception;

	public void changePassword(Account account) throws Exception;

	public Account forgetPassword(long idAccount) throws Exception;
}
