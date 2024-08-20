package Scientific;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

public class CalculatorGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Display;
	private JButton one;
	private JButton two;
	private JButton three;
	private JButton Minus;
	private JButton four;
	private JButton five;
	private JButton six;
	private JButton divid;
	private JButton seven;
	private JButton eight;
	private JButton nine;
	private JButton multiply;
	private JButton zero;
	private JButton dot;
	private JButton pi;
	private JButton equal;
	private double enterNumber1;
	private double enterNumber2;
	private String operator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorGui frame = new CalculatorGui();
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
	public CalculatorGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 285, 378);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(206, 214, 205));
		contentPane.setBorder(new CompoundBorder());

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Display = new JTextField();
		Display.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		Display.setBounds(10, 0, 251, 51);
		contentPane.add(Display);
		Display.setColumns(10);

		one = new JButton("1");
		one.addActionListener(new ActionMaster());
		one.setBounds(19, 115, 50, 45);
		contentPane.add(one);

		two = new JButton("2");
		two.addActionListener(new ActionMaster());
		two.setBounds(80, 115, 50, 45);
		contentPane.add(two);

		three = new JButton("3");
		three.addActionListener(new ActionMaster());
		three.setBounds(140, 117, 50, 45);
		contentPane.add(three);

		Minus = new JButton("-");
		Minus.addActionListener(new ActionMaster());
		Minus.setBounds(200, 117, 50, 45);
		contentPane.add(Minus);

		four = new JButton("4");
		four.addActionListener(new ActionMaster());
		four.setBounds(19, 171, 50, 45);
		contentPane.add(four);

		five = new JButton("5");
		five.addActionListener(new ActionMaster());
		five.setBounds(80, 171, 50, 45);
		contentPane.add(five);

		six = new JButton("6");
		six.addActionListener(new ActionMaster());
		six.setBounds(140, 173, 50, 45);
		contentPane.add(six);

		divid = new JButton("/");
		divid.addActionListener(new ActionMaster());
		divid.setBounds(200, 173, 50, 45);
		contentPane.add(divid);

		seven = new JButton("7");
		seven.addActionListener(new ActionMaster());
		seven.setBounds(19, 227, 50, 45);
		contentPane.add(seven);

		eight = new JButton("8");
		eight.addActionListener(new ActionMaster());
		eight.setBounds(80, 227, 50, 45);
		contentPane.add(eight);

		nine = new JButton("9");
		nine.addActionListener(new ActionMaster());
		nine.setBounds(140, 229, 50, 45);
		contentPane.add(nine);

		multiply = new JButton("*");
		multiply.addActionListener(new ActionMaster());
		multiply.setBounds(200, 229, 50, 45);
		contentPane.add(multiply);

		zero = new JButton("0");
		zero.addActionListener(new ActionMaster());
		zero.setBounds(19, 283, 50, 45);
		contentPane.add(zero);

		dot = new JButton(".");
		dot.addActionListener(new ActionMaster());
		dot.setBounds(80, 283, 50, 45);
		contentPane.add(dot);

		pi = new JButton("PI");
		pi.addActionListener(new ActionMaster());
		pi.setBounds(140, 285, 50, 45);
		contentPane.add(pi);

		equal = new JButton("=");
		equal.addActionListener(new ActionMaster());
		equal.setBounds(200, 285, 50, 45);
		contentPane.add(equal);

		JButton AC = new JButton("ac");
		AC.addActionListener(new ActionMaster());
		AC.setBounds(20, 57, 50, 45);
		contentPane.add(AC);

		JButton CE = new JButton("ce");
		CE.addActionListener(new ActionMaster());
		CE.setBounds(81, 57, 50, 45);
		contentPane.add(CE);

		JButton percentage = new JButton("%");
		percentage.addActionListener(new ActionMaster());
		percentage.setBounds(141, 59, 50, 45);
		contentPane.add(percentage);

		JButton Plus = new JButton("+");
		Plus.addActionListener(new ActionMaster());
		Plus.setBounds(201, 59, 50, 45);
		contentPane.add(Plus);

	}

//	all enter handler
	private class ActionMaster implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String enterCommand = e.getActionCommand();
			if (enterCommand.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Enter Requirements ???");
			} else if (enterCommand.matches("[0-9]")) {
				Display.setText(Display.getText() + enterCommand);
			} else if (enterCommand.matches("[+\\-*/%]")) {
				enterNumber1 = Double.parseDouble(Display.getText());
				operator = enterCommand;
				Display.setText("");
			} else if (enterCommand.equals("=")) {
				enterNumber2 = Double.parseDouble(Display.getText());
				Double result = calculateMath(operator, enterNumber1, enterNumber2);
				Display.setText(String.valueOf(result));

			}
		}
	}

//	calculator master
	public double calculateMath(String operator, double num1, double num2) {

		switch (operator) {
		case "+":
			return num1 + num2;
		case "-":
			return num1 - num2;
		case "*":
			return num1 * num2;
		case "/":
			return num1 / num2;
		case "%":
			return (num1 * num2) / 100;
		case "pi":
			return 3.14;
		default:
			return 0;
		}

	}
}
