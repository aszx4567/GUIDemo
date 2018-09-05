package Demo1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class RegistrationForm extends JFrame {
	
	Connection con;
	Statement stmt;
	PreparedStatement preStatement, updatePreStmt;
	
	JLabel title, idLabel, nameLabel, genderLabel, addressLabel, 
	       contactLabel;
	JTextField idField, nameField, genderField, 
	           addressField, contactField;
	JButton registerButton, exitButton, updateButton, 
	        deleteButton, resetButton, refresh;
	JRadioButton male, female;
	ButtonGroup bg;
	JPanel panel;
	JTable table;
	DefaultTableModel model;
	JScrollPane scrollpane;
	
	public RegistrationForm(){
		super("Simple Registration");
		
		setSize(770, 420);
		setLayout(null);
		
		connect();
		
		// defining labels
		title = new JLabel("Simpole Registration Demo");
		idLabel = new JLabel("ID");
		nameLabel = new JLabel("Name");
		genderLabel = new JLabel("Gender");
		addressLabel = new JLabel("Address");
		contactLabel = new JLabel("Contact");
		// sizing & positioning labels
		title.setBounds(60, 7, 200, 30);
		idLabel.setBounds(30, 50, 60, 30);
		nameLabel.setBounds(30, 85, 60, 30);
		genderLabel.setBounds(30, 120, 60, 30);
		addressLabel.setBounds(30, 155, 60, 30);
		contactLabel.setBounds(30, 190, 60, 30);
		
		// defining text fields; 실제 자료를 입력하는 창
		// idField, nameField, genderField, 
        // addressField, contactField;
		idField = new JTextField();
		nameField = new JTextField();
		genderField = new JTextField();
		addressField = new JTextField();
		contactField = new JTextField();
		
		idField.setBounds(95,  50, 130, 30);
		idField.setEnabled(false);
		nameField.setBounds(95,  85, 130, 30);
		// defining gender buttons
		male = new JRadioButton("Male");
		male.setBounds(95, 120, 60, 30);
		female = new JRadioButton("Female");
		female.setBounds(155, 120, 70, 30);
		// 라디오 버튼으로 만든 두 개 버튼을 하나로 그룹화... 둘 중 하나만 선택하도록.
		bg = new ButtonGroup();
		bg.add(male);
		bg.add(female);
		
		addressField.setBounds(95, 155, 130, 30);
		contactField.setBounds(95, 190, 130, 30);
		
		// Buttons
		// registerButton, exitButton, updateButton, 
        // deleteButton, resetButton, refresh;
		exitButton = new JButton("Exit");
		registerButton = new JButton("Register");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		resetButton = new JButton("Reset");
		
		exitButton.setBounds(25, 250, 80, 25);
		registerButton.setBounds(110, 250, 100, 25);
		deleteButton.setBounds(25, 285, 80, 25);
		deleteButton.setEnabled(false);
		updateButton.setBounds(110, 285, 100, 25);
		updateButton.setEnabled(false);
		resetButton.setBounds(60, 320, 100, 25);
		resetButton.setEnabled(false);
		
		// fixing all Label, TextField, RadioButton
		add(title);
		add(idLabel);
		add(nameLabel);
		add(genderLabel);
		add(addressLabel);
		add(contactLabel);
		add(idField);
		add(nameField);
		add(male);
		add(female);
		add(addressField);
		add(contactField);
		add(exitButton);
		add(registerButton);
		add(deleteButton);
		add(updateButton);
		add(resetButton);
		
		
		// defining panel
		panel = new JPanel();
		panel.setLayout(new GridLayout());
		panel.setBounds(250, 20, 480, 330);
		panel.setBorder(BorderFactory.createDashedBorder(Color.blue));
		add(panel);
		
		// refresh button
		refresh = new JButton("Refresh Table");
		refresh.setBounds(350, 350, 270, 15);
		add(refresh);

		// defining model for table
		model = new DefaultTableModel();
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		model.addColumn("S.No");
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Gender");
		model.addColumn("Address");
		model.addColumn("Contact");
		
		scrollpane = new JScrollPane(table, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panel.add(scrollpane);
		
		// 창을 화면 가운데에 출력하기
		// 위치를 잡아주는 메소드
		// setLocation()
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2,
				dim.height/2 - this.getSize().height/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
	}// constructor;
	
	// Connection with Database
	public void connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdb?useSSL=false", 
					"root", "tkddls6222");
			
			stmt = con.createStatement();
			preStatement = con.prepareStatement(
			"insert into regForm(name, gender, address, contact) "
			+ "values (?,?,?,?)");
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}