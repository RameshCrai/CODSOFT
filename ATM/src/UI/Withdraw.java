package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import config.AppConfig;
import entity.Account;
import entity.User;
import serviceImpl.ServiceMasterImpl;

public class Withdraw extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amount;
	private JTextField phone;
	private ServiceMasterImpl acc;
	private AppConfig appConfig;
	private User user;
	private Account account;
	private JButton amountdata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdraw frame = new Withdraw();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ClassNotFoundException
	 */
	public Withdraw() throws ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel footer = new JLabel("POWERED BY : RAMESH RAI");
		footer.setBounds(5, 329, 664, 27);
		footer.setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		footer.setHorizontalAlignment(SwingConstants.CENTER);
		footer.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(footer);

//		initialize the serviceImpl
		acc = new ServiceMasterImpl();

		// Access the AppConfig singleton instance
		appConfig = AppConfig.getInstanceOfApp();
		// Get the User object from AppConfig
		user = appConfig.getUser();

		JButton Addamount = new JButton("Add Amount");
		Addamount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAmount amount = new AddAmount();
				amount.setVisible(true);
				dispose();
			}
		});
		Addamount.setFont(new Font("Tahoma", Font.BOLD, 16));
		Addamount.setBounds(10, 11, 149, 36);
		contentPane.add(Addamount);

		amount = new JTextField();
		amount.setBounds(131, 115, 177, 36);
		contentPane.add(amount);
		amount.setColumns(10);

		JLabel welcome = new JLabel("Withdraw Amount");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Tahoma", Font.BOLD, 18));
		welcome.setBounds(77, 77, 276, 27);
		contentPane.add(welcome);

		JLabel amounttxt = new JLabel("Amount :");
		amounttxt.setFont(new Font("Tahoma", Font.BOLD, 18));
		amounttxt.setBounds(10, 112, 111, 42);
		contentPane.add(amounttxt);

		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPhone.setBounds(10, 165, 111, 42);
		contentPane.add(lblPhone);

		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(131, 171, 177, 36);
		contentPane.add(phone);

		JButton withdraw = new JButton("Withdraw");
		withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double EnterwithdrawAmount = Double.parseDouble(amount.getText().trim());

				String authPhone = phone.getText().trim();
				System.out.println("enter with ==" + EnterwithdrawAmount + "phone = " + authPhone);
				ServiceMasterImpl withdrawA = new ServiceMasterImpl();
				try {
					Account account = withdrawA.getAccount(user.getUserId());
					if (user.getPhone().equals(authPhone)) {
						if (account == null) {
							JOptionPane.showConfirmDialog(withdraw, "Please Deposite Amount ???");
							AddAmount amount = new AddAmount();
							amount.setVisible(true);
							dispose();
						} else if (EnterwithdrawAmount >= account.getAmount()) {
							JOptionPane.showConfirmDialog(withdraw, "You Have Not Sufficient Amount ??");
							return;
						} else if (EnterwithdrawAmount <= account.getAmount()) {
							double finalAmount = account.getAmount() - EnterwithdrawAmount;
							account.setAccountId(account.getAccountId());
							account.setAmount(finalAmount);
							account.setUserId(account.getUserId());
							if (withdrawA.updateAmount(account)) {
								JOptionPane.showConfirmDialog(withdraw,
										"Amount Withdraw Successfuly " + "\n Withdrawn Amount Rs." + EnterwithdrawAmount
												+ "\n Debit " + user.getFullName());
								amount.setText("");
								phone.setText("");
								account = getAmount();
								amountdata.setText("NPR: " + account.getAmount());

								return;
							}

						} else {
							JOptionPane.showConfirmDialog(withdraw, "Please select within your Amount ??");
							return;
						}

					} else {
						System.out.println("Incorect Phone");
						JOptionPane.showConfirmDialog(withdraw, "Incorrect Phone Number ???");
						return;
					}

				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				}
			}
		});
		withdraw.setFont(new Font("Tahoma", Font.BOLD, 16));
		withdraw.setBounds(131, 226, 177, 36);
		contentPane.add(withdraw);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 128, 192));
		separator.setBounds(352, 111, 2, 182);
		contentPane.add(separator);

		JLabel fname = new JLabel("FullName : ");
		fname.setFont(new Font("Tahoma", Font.BOLD, 14));
		fname.setBounds(374, 112, 85, 27);
		contentPane.add(fname);

		JLabel fnamedata = new JLabel("");
		fnamedata.setFont(new Font("Tahoma", Font.BOLD, 14));
		fnamedata.setBounds(487, 112, 149, 27);
		contentPane.add(fnamedata);

		JLabel emaildata = new JLabel("");
		emaildata.setFont(new Font("Tahoma", Font.BOLD, 14));
		emaildata.setBounds(487, 150, 149, 27);
		contentPane.add(emaildata);

		JLabel phonedata = new JLabel("");
		phonedata.setFont(new Font("Tahoma", Font.BOLD, 14));
		phonedata.setBounds(487, 188, 149, 27);
		contentPane.add(phonedata);

		JLabel lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountDetails.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAccountDetails.setBounds(363, 77, 276, 27);
		contentPane.add(lblAccountDetails);

		JLabel email = new JLabel("Email : ");
		email.setFont(new Font("Tahoma", Font.BOLD, 14));
		email.setBounds(374, 150, 85, 27);
		contentPane.add(email);

		JLabel lblPhone_1 = new JLabel("Phone : ");
		lblPhone_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhone_1.setBounds(374, 188, 85, 27);
		contentPane.add(lblPhone_1);

		JLabel amountxt = new JLabel("Amount : ");
		amountxt.setForeground(new Color(0, 0, 0));
		amountxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		amountxt.setBounds(374, 235, 85, 27);
		contentPane.add(amountxt);

		amountdata = new JButton("");
		amountdata.setBounds(487, 228, 111, 36);
		contentPane.add(amountdata);
		if (user != null) {
			System.out.println("User name: " + user.getEmail());
			fnamedata.setText(user.getFullName().toString());
			emaildata.setText(user.getEmail().toString());
			phonedata.setText(user.getPhone().toString());
			amountdata.setText("NPR: ");

			Account account = this.getAmount();
			if (account != null) {
				amountdata.setText("NPR: " + account.getAmount());
			} else {
				amountdata.setText("Add Amount: ");
			}
		} else {
			System.out.println("No user found.");
		}
	}

	public Account getAmount() {
		try {
			if (acc != null) {
				account = acc.getAccount(user.getUserId());
				return account;
			} else {
				JOptionPane.showMessageDialog(this, "ServiceMasterImpl instance is not initialized.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Failed to retrieve account information.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;

	}
}
