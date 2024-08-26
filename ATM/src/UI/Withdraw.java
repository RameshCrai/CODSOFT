package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Withdraw extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amount;
	private JTextField phone;

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
	 */
	public Withdraw() {
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
		
		JButton Addamount = new JButton("Add Amount");
		Addamount.setFont(new Font("Tahoma", Font.BOLD, 16));
		Addamount.setBounds(10, 11, 149, 36);
		contentPane.add(Addamount);
		
		JButton Account = new JButton("Account Details");
		Account.setFont(new Font("Tahoma", Font.BOLD, 16));
		Account.setBounds(176, 11, 177, 36);
		contentPane.add(Account);
		
		JButton Addamount_2 = new JButton("Add Amount");
		Addamount_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		Addamount_2.setBounds(363, 11, 149, 36);
		contentPane.add(Addamount_2);
		
		amount = new JTextField();
		amount.setBounds(232, 115, 177, 42);
		contentPane.add(amount);
		amount.setColumns(10);
		
		JLabel welcome = new JLabel("Withdraw Amount");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Tahoma", Font.BOLD, 18));
		welcome.setBounds(163, 67, 276, 27);
		contentPane.add(welcome);
		
		JLabel amounttxt = new JLabel("Amount :");
		amounttxt.setFont(new Font("Tahoma", Font.BOLD, 18));
		amounttxt.setBounds(78, 115, 111, 42);
		contentPane.add(amounttxt);
		
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPhone.setBounds(78, 188, 111, 42);
		contentPane.add(lblPhone);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(232, 188, 177, 42);
		contentPane.add(phone);
		
		JButton withdraw = new JButton("Withdraw");
		withdraw.setFont(new Font("Tahoma", Font.BOLD, 16));
		withdraw.setBounds(232, 253, 177, 36);
		contentPane.add(withdraw);
	}

}
