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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import config.AppConfig;
import entity.User;
import service.ServiceMaster;
import serviceImpl.ServiceMasterImpl;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel loginTop = new JLabel("LOGIN ATM");
		loginTop.setHorizontalAlignment(SwingConstants.CENTER);
		loginTop.setFont(new Font("Tahoma", Font.BOLD, 18));
		loginTop.setBounds(157, 11, 281, 32);
		contentPane.add(loginTop);

		JLabel email1 = new JLabel("Email :");
		email1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		email1.setBounds(58, 109, 81, 32);
		contentPane.add(email1);

		email = new JTextField();
		email.setBounds(163, 108, 293, 41);
		contentPane.add(email);
		email.setColumns(10);

		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (email.getText().isEmpty()) {
					JOptionPane.showConfirmDialog(null, "Please Fill the table ???");
					return;
				}

				User userEmail = new User();
				userEmail.setEmail(email.getText());

				ServiceMaster master = new ServiceMasterImpl();
				try {
					User userinfo = master.getAuthByEmail(userEmail.getEmail());

					if (userinfo != null) {
						AppConfig.getInstanceOfApp().setUser(userinfo);
						System.out.println("user data = " + userinfo);
						Withdraw withdraw = new Withdraw();
						withdraw.setVisible(true);
						dispose();
					} else {

						JOptionPane.showConfirmDialog(null, "Email is not Exist Please Register  ??");
						return;
					}
				} catch (HeadlessException | ClassNotFoundException e1) {

					e1.printStackTrace();
				}
			}
		});
		login.setFont(new Font("Tahoma", Font.BOLD, 18));
		login.setBounds(240, 201, 126, 41);
		contentPane.add(login);
	}

}
