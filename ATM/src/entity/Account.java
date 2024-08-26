package entity;

public class Account {
	private int accountId;
	private double amount;
	private int userId;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accountId, double amount, int userId) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.userId = userId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	

}
