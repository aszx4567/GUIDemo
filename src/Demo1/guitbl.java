package Demo1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.*;

public class guitbl extends JFrame {

	
	private JPanel contentPane;
	private JTextField textname;
	private JTextField textphone;
	private JTextField textid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					guitbl frame = new guitbl();
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
	public guitbl() {
		
		
	 
		setBackground(SystemColor.inactiveCaptionBorder);
		setAutoRequestFocus(false);
		setTitle("GUI Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("GUI Test \uC804\uD654\uBC88\uD638\uBD80 \r\n\r\n");
		lblNewLabel.setFont(new Font("HY궁서", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 10, 221, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(40, 70, 60, 20);
		contentPane.add(lblName);
		
		textname = new JTextField();
		textname.setBackground(new Color(245, 245, 220)); 
		textname.setToolTipText("insert name\r\n");
		textname.setBounds(82, 70, 200, 20);
		contentPane.add(textname);
		textname.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(40, 120, 60, 20);
		contentPane.add(lblPhone);
		
		textphone = new JTextField();
		textphone.setBounds(82, 120, 200, 20);
		contentPane.add(textphone);
		textphone.setColumns(10);
		
		String [] gender = {"Male", "FeMale"};
		JComboBox comboBoxGender = new JComboBox(gender);
		comboBoxGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBoxGender.setBounds(147, 188, 101, 15);
		contentPane.add(comboBoxGender);
		
		JLabel lblNewLabel_1 = new JLabel("gender");
		lblNewLabel_1.setBounds(85, 189, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(40, 230, 242, 105);
		contentPane.add(panel);
				
		JButton btnInsert = new JButton("insert");
		btnInsert.setBackground(SystemColor.menu);
		btnInsert.setToolTipText("이름과 번호, 성별을 데이터베이스에 추가합니다. insert는 id를 넣지 마세요");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?useSSL=false","root","tkddls6222");
					
					java.sql.PreparedStatement Pstatement = conn.prepareStatement("insert into guitbl(name,phone,gender) values(? , ?, ?)");
					Pstatement.setString(1, textname.getText());
					Pstatement.setString(2, textphone.getText());
				    Pstatement.setString(3, comboBoxGender.getSelectedItem().toString());
					Pstatement.executeUpdate();
					System.out.println("Inserted successfully");
				}catch(SQLException e1){
					e1.printStackTrace();
				}
			} 
		});
		panel.add(btnInsert);
		
		JButton btnSearch = new JButton("search");
		btnSearch.setToolTipText("이름을 입력하고 누르면 전화번호를 출력합니다");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?useSSL=false","root","tkddls6222");
					
					java.sql.PreparedStatement Pstatement = conn.prepareStatement("select * from guitbl where name = ?");
					Pstatement.setString(1, textname.getText());
					ResultSet rs = Pstatement.executeQuery();
					System.out.println("Search successfully");
					
					if(rs.next()){
 						String Id = rs.getString(1);
						String Name = rs.getString(2);
						String Phone = rs.getString(3);
						String Gender = rs.getString(4);
						textid.setText(Id);
						textname.setText(Name);
						textphone.setText(Phone);
						comboBoxGender.setSelectedItem(Gender);
					}		
				    }catch(SQLException e1){
					      e1.printStackTrace();
				    }
		    	}
	     	});
		panel.add(btnSearch);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.setToolTipText("먼저 수정할 이름을 검색후 나온 id를 그대로 두고 이름,번호를 바꾸시면 됩니다");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?useSSL=false","root","tkddls6222");
					
					java.sql.PreparedStatement Pstatement = conn.prepareStatement("update guitbl set name = ?, phone = ?, gender = ? where id = ? ");
					Pstatement.setString(1, textname.getText()); //1번째 ? 를 textname으로 지정합니다.
					Pstatement.setString(2, textphone.getText());
					Pstatement.setString(3, textid.getText());
					Pstatement.setString(4, comboBoxGender.getSelectedItem().toString());
					Pstatement.executeUpdate();
					System.out.println("Update successfully");
				}catch(SQLException e1){
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.setToolTipText("id에 해당하는 데이터를 삭제합니다.   검색후 그냥 눌르면 됩니다.");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?useSSL=false","root","tkddls6222");
					
					java.sql.PreparedStatement Pstatement = conn.prepareStatement("delete from guitbl where id = ? ");
					Pstatement.setString(1, textid.getText());
					Pstatement.executeUpdate();
					System.out.println("Delete successfully");
				}catch(SQLException e1){
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnDelete);
		
		JButton btnClear = new JButton(" clear ");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textid.setText("");
				textphone.setText("");
				textname.setText("");
			}
		});
		panel.add(btnClear);
		
		JButton btnExit = new JButton("  exit  ");
		btnExit.setIcon(new ImageIcon("C:\\PSI\\ReferBooks\\image\\exit.png"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//To Do
				System.exit(0);
			}
		});
		panel.add(btnExit);
		
		textid = new JTextField();
		textid.setBounds(82, 158, 60, 21);
		contentPane.add(textid);
		textid.setColumns(10);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(40, 161, 57, 15);
		contentPane.add(lblId);
		
		JLabel lblInsertId = new JLabel("\uCC98\uC74C\uC2E4\uD589\uC2DC \uD234\uD301\uC744 \uAF2D \uBD10\uC8FC\uC138\uC694\r\n");
		lblInsertId.setBounds(96, 345, 185, 15);
		contentPane.add(lblInsertId);
		
		
	}
}
