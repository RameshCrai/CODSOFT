package service;

import entity.Account;
import entity.User;

public interface ServiceMaster {

	public boolean saveUser(User user) throws ClassNotFoundException;

	public User getAuthByEmail(String email) throws ClassNotFoundException;

	public boolean saveAmount(Account acount) throws ClassNotFoundException;

	public boolean getAccount(int id) throws ClassNotFoundException;

}
