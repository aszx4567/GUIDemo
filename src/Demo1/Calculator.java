package Demo1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class Calculator extends JFrame {
	static double number, answer;
	static int calculation;
	private JPanel contentPane;
	private JTextField textC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
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
	public Calculator() {
		setTitle("\uACC4\uC0B0\uAE30");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\PSI\\GUIDemo\\image\\1.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textC = new JTextField();
		textC.setBounds(30, 20, 200, 30);
		contentPane.add(textC);
		textC.setColumns(10);
		
	
		
		JLabel label = new JLabel("");
		label.setForeground(new Color(204, 255, 204));
		label.setBounds(171, 0, 50, 15);
		contentPane.add(label);
		
		
		
		JButton btnNewButton = new JButton("DEL");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("±¼¸²", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int length = textC.getText().length();
				int number = length - 1;
				if(length > 0){
					StringBuilder back = new StringBuilder(textC.getText());
					back.deleteCharAt(number);
					textC.setText(back.toString());
				}
			}
		});
		btnNewButton.setBounds(60, 72, 60, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("/");
		btnNewButton_1.setFont(new Font("±¼¸²", Font.BOLD, 25));
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str = textC.getText();
				number = Double.parseDouble(textC.getText());
				textC.setText("");
				label.setText(str + "/");
				calculation = 4;
			}
		});
		btnNewButton_1.setBounds(129, 72, 50, 40);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("7");
		btnNewButton_2.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ "7");
			}
		});
		btnNewButton_2.setBounds(8, 122,50, 40);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("4");
		btnNewButton_3.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ "4");
			}
		});
		btnNewButton_3.setBounds(8, 172, 50, 40);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("1");
		btnNewButton_4.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ "1");
			}
		});
		btnNewButton_4.setBounds(8, 222, 50, 40);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("0");
		btnNewButton_5.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ "0");
			}
		});
		btnNewButton_5.setBounds(8, 272, 100, 40);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton(".");
		btnNewButton_6.setFont(new Font("±¼¸²", Font.BOLD, 25));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ ".");
			}
		});
		btnNewButton_6.setBounds(129, 270, 50, 40);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("8");
		btnNewButton_7.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ "8");
			}
		});
		btnNewButton_7.setBounds(70, 122, 50, 40);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("5");
		btnNewButton_8.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ "5");
			}
		});
		btnNewButton_8.setBounds(70, 172, 50, 40);
		contentPane.add(btnNewButton_8);
		
		JButton button = new JButton("2");
		button.setFont(new Font("±¼¸²", Font.BOLD, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ "2");
			}
		});
		button.setBounds(70, 222, 50, 40);
		contentPane.add(button);
		
		JButton btnNewButton_9 = new JButton("9");
		btnNewButton_9.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ "9");
			}
		});
		btnNewButton_9.setBounds(129, 122, 50, 40);
		contentPane.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("6");
		btnNewButton_10.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ "6");
			}
		});
		btnNewButton_10.setBounds(129, 172, 50, 40);
		contentPane.add(btnNewButton_10);
		
		JButton button_1 = new JButton("3");
		button_1.setFont(new Font("±¼¸²", Font.BOLD, 20));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textC.setText(textC.getText()+ "3");
			}
		});
		button_1.setBounds(129, 222,50, 40);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("=");
		button_2.setBackground(Color.ORANGE);
		button_2.setFont(new Font("±¼¸²", Font.BOLD, 25));
		button_2.setForeground(new Color(0, 0, 0));
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			switch(calculation){
				case 1 :
					answer = number + Double.parseDouble(textC.getText());
					if(Double.toString(answer).endsWith(".0")){
						textC.setText(Double.toString(answer).replace(".0",""));
					}else{
						textC.setText(Double.toString(answer));
					}label.setText("");
					break;
				case 2 :
					answer = number - Double.parseDouble(textC.getText());
					if(Double.toString(answer).endsWith(".0")){
						textC.setText(Double.toString(answer).replace(".0",""));
					}else{
						textC.setText(Double.toString(answer));
					}label.setText("");
					break;
				case 3 :
					answer = number * Double.parseDouble(textC.getText());
					if(Double.toString(answer).endsWith(".0")){
						textC.setText(Double.toString(answer).replace(".0",""));
					}else{
						textC.setText(Double.toString(answer));
					}label.setText("");
					break;
				case 4 :
					answer = number / Double.parseDouble(textC.getText());
					if(Double.toString(answer).endsWith(".0")){
						textC.setText(Double.toString(answer).replace(".0",""));
					}else{
						textC.setText(Double.toString(answer));
					}label.setText("");
					break;
				}
			}
		});
		button_2.setBounds(191, 222, 50, 90);
		contentPane.add(button_2);
		
		JButton btnNewButton_11 = new JButton("-");
		btnNewButton_11.setBackground(Color.ORANGE);
		btnNewButton_11.setFont(new Font("±¼¸²", Font.BOLD, 25));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str = textC.getText();
				number = Double.parseDouble(textC.getText());
				textC.setText("");
				label.setText(str + "-");
				calculation = 2;
			}
		});
		btnNewButton_11.setBounds(191, 172, 50,40);
		contentPane.add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("+");
		btnNewButton_12.setBackground(Color.ORANGE);
		btnNewButton_12.setFont(new Font("±¼¸²", Font.BOLD, 25));
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str = textC.getText();
				number = Double.parseDouble(textC.getText());
				textC.setText("");
				label.setText(str + "+");
				calculation = 1;
			}
		});
		btnNewButton_12.setBounds(191, 122, 50, 40);
		contentPane.add(btnNewButton_12);
		
		JButton btnX = new JButton("x");
		btnX.setBackground(Color.ORANGE);
		btnX.setFont(new Font("±¼¸²", Font.BOLD, 25));
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str = textC.getText();
				number = Double.parseDouble(textC.getText());
				textC.setText("");
				label.setText(str + "*");
				calculation = 3;
			}
		});
		btnX.setBounds(191, 72, 50, 40);
		contentPane.add(btnX);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("on\r\n");
		rdbtnNewRadioButton.setForeground(new Color(255, 255, 204));
		rdbtnNewRadioButton.setBackground(new Color(0, 51, 102));
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setEnabled(true);
				btnX .setEnabled(true);
				btnNewButton.setEnabled(true);
				btnNewButton_1.setEnabled(true);
				btnNewButton_2.setEnabled(true);
				btnNewButton_3.setEnabled(true);
				btnNewButton_4.setEnabled(true);
				btnNewButton_5.setEnabled(true);
				btnNewButton_6.setEnabled(true);
				btnNewButton_7.setEnabled(true);
				btnNewButton_8.setEnabled(true);
				btnNewButton_9.setEnabled(true);
				btnNewButton_10.setEnabled(true);
				btnNewButton_11.setEnabled(true);
				btnNewButton_12.setEnabled(true);
				button.setEnabled(true);
				button_2.setEnabled(true);
			}
		});
		rdbtnNewRadioButton.setBounds(8, 52, 44, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("off\r\n");
		rdbtnNewRadioButton_1.setForeground(new Color(255, 255, 204));
		rdbtnNewRadioButton_1.setBackground(new Color(0, 51, 102));
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setEnabled(false);
				btnX .setEnabled(false);
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnNewButton_2.setEnabled(false);
				btnNewButton_3.setEnabled(false);
				btnNewButton_4.setEnabled(false);
				btnNewButton_5.setEnabled(false);
				btnNewButton_6.setEnabled(false);
				btnNewButton_7.setEnabled(false);
				btnNewButton_8.setEnabled(false);
				btnNewButton_9.setEnabled(false);
				btnNewButton_10.setEnabled(false);
				btnNewButton_11.setEnabled(false);
				btnNewButton_12.setEnabled(false);
				button.setEnabled(false);
				button_2.setEnabled(false);
			}
		});
		rdbtnNewRadioButton_1.setBounds(8, 77, 44, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(rdbtnNewRadioButton);
		grp.add(rdbtnNewRadioButton_1);
		
	
	}

}
