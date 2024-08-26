package UI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entity.User;
import service.ServiceMaster;
import serviceImpl.ServiceMasterImpl;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fname;
	private JTextField phone;
	private JTextField email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel registertop = new JLabel("Register For ATM");
		registertop.setFont(new Font("Tahoma", Font.BOLD, 30));
		registertop.setBounds(144, 11, 272, 42);
		contentPane.add(registertop);

		JLabel fullname = new JLabel("Full Name :");
		fullname.setFont(new Font("Tahoma", Font.BOLD, 18));
		fullname.setBounds(22, 104, 107, 32);
		contentPane.add(fullname);

		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPhone.setBounds(22, 168, 107, 32);
		contentPane.add(lblPhone);

		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(22, 231, 107, 32);
		contentPane.add(lblEmail);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 64, 537, 2);
		contentPane.add(separator);

		fname = new JTextField();
		fname.setBounds(220, 102, 307, 42);
		contentPane.add(fname);
		fname.setColumns(10);

		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(220, 166, 307, 42);
		contentPane.add(phone);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(220, 229, 307, 42);
		contentPane.add(email);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fname.getText().isEmpty()) {
					JOptionPane.showConfirmDialog(null, "FullName Required ??");
					return;
				}
				if (phone.getText().isEmpty()) {
					JOptionPane.showConfirmDialog(null, "Phone Required ??");
					return;
				}
				if (email.getText().isEmpty()) {
					JOptionPane.showConfirmDialog(null, "Email Required ??");
					return;
				}
				User user = new User();
				user.setFullName(fname.getText());
				user.setEmail(email.getText());
				user.setPhone(phone.getText());

				ServiceMaster saveUserInfo = new ServiceMasterImpl();
				try {
					if (saveUserInfo.saveUser(user)) {
						JOptionPane.showConfirmDialog(null, "User Save Successfuly ??");
						fname.setText("");
						email.setText("");
						phone.setText("");
						Login login = new Login();
						login.setVisible(true);
						dispose();
						return;
					}
				} catch (HeadlessException | ClassNotFoundException e1) {
					e1.printStackTrace();
					JOptionPane.showConfirmDialog(null, "User Save Failed ??");
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(329, 295, 118, 39);
		contentPane.add(btnNewButton);
	}
}
