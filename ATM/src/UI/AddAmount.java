package UI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import config.AppConfig;
import entity.Account;
import entity.User;
import service.ServiceMaster;
import serviceImpl.ServiceMasterImpl;

public class AddAmount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAmount frame = new AddAmount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddAmount() {
		setTitle("Deposit Amount");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Access the AppConfig singleton instance
		AppConfig appConfig = AppConfig.getInstanceOfApp();
		// Get the User object from AppConfig
		User user = appConfig.getUser();

		JLabel lblNewLabel = new JLabel("Amount : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 103, 30);
		contentPane.add(lblNewLabel);

		amount = new JTextField();
		amount.setBounds(144, 19, 197, 30);
		contentPane.add(amount);
		amount.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (amount.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please Enter Amount");
					return;
				}

				ServiceMaster serviceInfo = new ServiceMasterImpl();
				try {
					Account account = serviceInfo.getAccount(user.getUserId());

					if (account != null) {
						double prevAmount = account.getAmount();
						double enterAmount = Double.parseDouble(amount.getText().trim());
						double totalAmount = prevAmount + enterAmount;

						Account acc = new Account();
						acc.setAmount(totalAmount);
						acc.setUserId(user.getUserId());

						// Update account amount
						if (serviceInfo.updateAmount(acc)) {
							JOptionPane.showMessageDialog(btnSubmit, "Save Amount Successfully");
							Withdraw with = new Withdraw();
							with.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(btnSubmit, "Failed to Update Amount", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						Account acc = new Account();
						acc.setAmount(Double.parseDouble(amount.getText().trim()));
						acc.setUserId(user.getUserId());

						if (serviceInfo.saveAmount(acc)) {
							JOptionPane.showMessageDialog(btnSubmit, "Save Amount Successfully");
							Withdraw with = new Withdraw();
							with.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(btnSubmit, "Failed to Save Amount", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(btnSubmit, "Invalid Amount Format", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(btnSubmit, "Failed to Save Amount", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSubmit.setBounds(189, 89, 111, 30);
		contentPane.add(btnSubmit);
	}
}
