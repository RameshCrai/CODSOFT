package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Atm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atm frame = new Atm();
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
	public Atm() {
		setTitle("ATM SYSTEM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel imagelabel = new JLabel("image");
		imagelabel.setIcon(new ImageIcon("D:\\CODSOFT\\atm.jpg"));
		imagelabel.setBounds(281, 2, 705, 543);
		contentPane.add(imagelabel);
		
		JLabel footer = new JLabel("Powered By : RAMESH RAI");
		footer.setEnabled(false);
		footer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		footer.setHorizontalAlignment(SwingConstants.LEFT);
		footer.setBackground(new Color(128, 128, 255));
		footer.setBounds(10, 320, 269, 41);
		contentPane.add(footer);
		
		JButton login = new JButton("Log-in");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login atmService = new Login();
				atmService.setVisible(true);
				dispose();
			}
		});
		login.setForeground(new Color(0, 0, 0));
		login.setFont(new Font("Tahoma", Font.BOLD, 15));
		login.setBounds(20, 79, 240, 41);
		contentPane.add(login);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
				dispose();
			}
		});
		register.setForeground(Color.BLACK);
		register.setFont(new Font("Tahoma", Font.BOLD, 15));
		register.setBounds(20, 131, 240, 41);
		contentPane.add(register);
		
		JLabel atmtitle = new JLabel("ATM BANK");
		atmtitle.setHorizontalAlignment(SwingConstants.CENTER);
		atmtitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		atmtitle.setEnabled(false);
		atmtitle.setBackground(new Color(128, 128, 255));
		atmtitle.setBounds(0, 2, 279, 41);
		contentPane.add(atmtitle);
	}
}
