package entity;

public class User {
	private int userId;
	private String email;
	private String fullName;
	private String phone;
	public User() {
		
	}
	public User(int userId, String email, String fullName, String phone) {
		super();
		this.userId = userId;
		this.email = email;
		this.fullName = fullName;
		this.phone = phone;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
