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
		String sql = "insert into user(full_name,email, phone)values(?,?,?)";
		try {

			PreparedStatement pstm = Database.getConnection().prepareStatement(sql);
			pstm.setString(1, user.getFullName());
			pstm.setString(2, user.getEmail());
			pstm.setString(3, user.getPhone());

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
		String sql = "insert into account (amount,user_id)values(?,?)";
		try {
			PreparedStatement pstm = Database.getConnection().prepareStatement(sql);
			pstm.setDouble(1, acount.getAmount());
			pstm.setInt(2, acount.getUserId());

			pstm.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Account getAccount(int id) throws ClassNotFoundException {
		String sql = "SELECT * FROM account WHERE user_id = ?";
		try (Connection conn = Database.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {

			// Set the parameter for the prepared statement
			pstm.setInt(1, id);

			try (ResultSet rs = pstm.executeQuery()) {
				if (rs.next()) {
					Account acc = new Account();
					acc.setAccountId(rs.getInt("account_id"));
					acc.setAmount(rs.getDouble("amount"));
					acc.setUserId(rs.getInt("user_id"));
					return acc;
				}
			}
		} catch (SQLException e) {
			// Log the exception or handle it appropriately
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateAmount(Account account) throws ClassNotFoundException {
	    String sql = "UPDATE account SET amount = ? WHERE user_id = ?";
	    try (Connection con = Database.getConnection(); 
	         PreparedStatement pstm = con.prepareStatement(sql)) {

	        pstm.setDouble(1, account.getAmount()); 
	        pstm.setInt(2, account.getUserId());   

	        int rowsAffected = pstm.executeUpdate();

	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


}
