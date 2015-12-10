package dao;

import java.util.List;

public interface MessageDao {
	public void saveMessage(String mes) throws Exception;
	public List<String> getNewMessage(int n)throws Exception;
	public String getNewMessage()throws Exception;
}
