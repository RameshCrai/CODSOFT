package config;

import entity.User;

public class AppConfig {
	private static AppConfig appInstance;
	private User user;

	public static synchronized AppConfig getInstanceOfApp() {
		if (appInstance == null) {
			appInstance = new AppConfig();
		}
		return appInstance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
