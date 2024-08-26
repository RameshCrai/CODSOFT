package serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.Database;
import entity.Account;
import entity.User;
import service.ServiceMaster;

public class ServiceMasterImpl implements ServiceMaster {

	@Override
	public boolean saveUser(User user) throws ClassNotFoundException {
		String sql = "insert into user(email,full_name, phone)values(?,?,?)";
		try {

			PreparedStatement pstm = Database.getConnection().prepareStatement(sql);
			pstm.setString(1, user.getEmail());
			pstm.setString(3, user.getFullName());
			pstm.setString(2, user.getPhone());

			pstm.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return false;
	}

	@Override
	public User getAuthByEmail(String email) throws ClassNotFoundException {
		String sql = "SELECT * FROM user WHERE email = ?";
		try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, email);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) { 
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					user.setFullName(rs.getString("full_name"));
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveAmount(Account acount) throws ClassNotFoundException {
		String sql = "insert into account (amount,userId)values(?,?)";
		try {
			PreparedStatement pstm = Database.getConnection().prepareStatement(sql);
			pstm.setDouble(1, acount.getAmount());
			pstm.setInt(1, acount.getUserId());

			pstm.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean getAccount(int id) throws ClassNotFoundException {
		String sql = "select *from account where user_id = " + id;
		try {
			Statement stm = Database.getConnection().prepareStatement(sql);
			stm.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
