package Demo2;

import java.awt.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class AirExTest extends JFrame {
	JTable table;
	JPanel panel2;
	DefaultTableModel model;
	JScrollPane scrollpane;
	JButton selectb, insertb, deleteb, exitButton, updateb, resetb;
	JTextField id, nameField, country, year;
	JTextArea impor; 
	ButtonGroup bg;
	JButton refresh ;
	Connection con;
	Statement stmt;
	PreparedStatement preStatement, updatePreStmt, insertPreStmt, pretStatement;
	JPanel contentPane , panel;
	ResultSet rst, rstLast;
	int serialNo;
	String k, a;
	JTextArea textArea;
	
	

	
	
	

	
	public AirExTest() {
		
		super("Simple Registration");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 450);
		getContentPane().setLayout(null);
		
	    
		
		connect();
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		id = new JTextField();
		id.setBounds(78, 37, 116, 21);
		contentPane.add(id);
		id.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(78, 77, 116, 21);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(12, 40, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setBounds(12, 80, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("COUNTRY");
		lblNewLabel_2.setBounds(12, 121, 69, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton exitButton = new JButton("Exit\r\n");
		
		exitButton.setBounds(67, 360, 97, 23);
		contentPane.add(exitButton);
		
		JButton updateb = new JButton("Update\r\n");
		
		updateb.setBounds(12, 304, 97, 23);
		contentPane.add(updateb);
		
		JButton resetb = new JButton("Reset");
		
		resetb.setBounds(121, 304, 97, 23);
		contentPane.add(resetb);
		
		JButton insertb = new JButton("Insert\r\n");
		
		
		insertb.setBounds(12, 249, 97, 23);
		contentPane.add(insertb);
		
		JButton deleteb = new JButton("Delete");
		
		deleteb.setBounds(121, 249, 97, 23);
		contentPane.add(deleteb);
		
		JButton selectb = new JButton("Select");
		selectb.setBounds(67, 196, 97, 23);
		contentPane.add(selectb);
		
		country = new JTextField();
		country.setBounds(78, 118, 116, 21);
		contentPane.add(country);
		country.setColumns(10);
		
		panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setLayout(null);
		panel2.setBounds(230, 128, 422, 262);
		panel2.setBorder(BorderFactory.createDashedBorder(Color.blue));
		getContentPane().add(panel2);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 422, 262);
		panel2.add(textArea);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		panel.setBounds(230, 10, 422, 108);
		contentPane.add(panel);
		
		model = new DefaultTableModel();
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		model.addColumn("S.No");
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("year");
		model.addColumn("Country");
		
		id.setEnabled(false);
		
		scrollpane = new JScrollPane(table, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panel.add(scrollpane);
		
		
		year = new JTextField();
		year.setBounds(78, 152, 116, 21);
		contentPane.add(year);
		year.setColumns(10);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(12, 155, 57, 15);
		contentPane.add(lblYear);
		
		refresh = new JButton("Refresh Table");
		refresh.setBounds(306, 397, 270, 15);
		getContentPane().add(refresh);
		
		JButton textinsert = new JButton("textinsert");
		textinsert.setBounds(196, 393, 97, 23);
		contentPane.add(textinsert);
		
	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2,
				dim.height/2 - this.getSize().height/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		nameField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				if(nameField.getText().length()>=30)
					e.consume();
			}// end of keyTyped()
		});// end of addKeyListner()
		
		year.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				if(year.getText().length()>=5)
					e.consume();
			}// end of keyTyped()
		});// end of addKeyListner()
		
		country.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				if(country.getText().length()>=20)
					e.consume();
			}// end of keyTyped()
		});// end of addKeyListner()
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					con.close();
					System.exit(0);
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});
		// refresh 버튼 처리
				refresh.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						refreshTable();
					}
				});
		
		insertb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					preStatement = con.prepareStatement(
							"insert into pritbl(name,year,country) "
							+ "values (?,?,?)");
										
				     	 if(e.getSource() == insertb ) {
						  
							
						if(nameField.getText().equals(""))
							JOptionPane.showMessageDialog(nameField, "Please fill name");
						else if(year.getText().equals(""))
							JOptionPane.showMessageDialog(year, "Please fill address");
						else if(country.getText().equals(""))
							JOptionPane.showMessageDialog(country, "Please fill contact");
						else {
							preStatement.setString(1, nameField.getText());
							preStatement.setString(2, year.getText());
							preStatement.setString(3, country.getText());	
							
							
							//					
							 
							int i = preStatement.executeUpdate();
							
							if(i==1){
								JOptionPane.showMessageDialog(panel, "Successfully Registered");
							}
							
							// showing last record
							rstLast = stmt.executeQuery("select * from pritbl");
							rstLast.last();
							String string = serialNo++ 
									+ "," 
									+ String.valueOf(rstLast.getInt(1)) 
									+ "," 
									+ rstLast.getString(2) 
									+ ","
									+ rstLast.getString(3)
									+ ","
									+ rstLast.getString(4);
							Object[] row = null;
							row = string.split(",");
							model.addRow(row);
							panel.revalidate();
							blankFields();
							
						}
					  }// 등록 버튼일 때... 
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}
			
				/*pretStatement = con.prepareStatement("select LAST_INSERT_ID()");
				ResultSet rs2 = pretStatement .executeQuery();
				if(rs2.next()){				
					k = rs2.getString(1);
					System.out.println(k);
					//a.setText(k);
					}	else{
						
				}*/
				
				
				
			}
		});
		
		textinsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try{
					int r = table.getSelectedRow();
					String id3 = (String)table.getModel().getValueAt(r, 1);
					System.out.println("[dbg] before insertion into another table");
					pretStatement = con.prepareStatement("insert into texttbl(id,text) values("+id3+",?)");
					pretStatement.setString(1, textArea.getText());
				
					int i = pretStatement.executeUpdate();
					if(i==1){
						JOptionPane.showMessageDialog(panel, "Successfully Registered into text table");
					}
					
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}
				
			}
		});
		
		deleteb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int r = table.getSelectedRow();
					String id2 = id.getText();
					
					
					if(r >= 0 ){
						if(JOptionPane.showConfirmDialog(panel, 
								"Are you sure want to delete this RECORD?",
								"WARNING",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							// 삭제를 원한다면... 
							
							int i = stmt.executeUpdate("delete from pritbl where id ="+id2);
							
							     
							if(i==1){
								model.removeRow(r);
								
								blankFields();
								insertb.setEnabled(true);
								deleteb.setEnabled(false);
								updateb.setEnabled(false);
								resetb.setEnabled(false);
							}
						}
					}// end of if(r>=0)
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});
		updateb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().equals(""))
					JOptionPane.showMessageDialog(nameField, "Please fill name");
				else if(year.getText().equals(""))
					JOptionPane.showMessageDialog(year, "Please fill address");
				else if(country.getText().equals(""))
					JOptionPane.showMessageDialog(country, "Please fill contact");
				else {
				int r = table.getSelectedRow();
					try{
						if(r>=0){
							
							String id = (String)table.getModel().getValueAt(r, 1);
							updatePreStmt = con.prepareStatement(
				       					"update pritbl  "
									+ "set name=?, year=?,country=?"
									+ "where id="+id);
							
							updatePreStmt.setString(1, nameField.getText());
							updatePreStmt.setString(2, year.getText());
							updatePreStmt.setString(3, country.getText());		
							
							updatePreStmt = con.prepareStatement(
			       					"update texttbl set  text = ? where id ="+id);
							updatePreStmt.setString(1, textArea.getText());
							
							int i = updatePreStmt.executeUpdate();
							if(i==1){
								table.setValueAt(nameField.getText(), r, 2);
								table.setValueAt(year.getText(), r, 3);
								table.setValueAt(country.getText(), r, 4);
							}
							else 
								JOptionPane.showMessageDialog(panel, "Update Failed");
							}
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}
				}
			}
		});
		resetb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blankFields();
				insertb.setEnabled(true);
				updateb.setEnabled(false);
				deleteb.setEnabled(false);
				resetb.setEnabled(false);
			}
		});
		
		//  table 상의 레코드에 마우스를 클릭할 때...
			table.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e){
					int r = table.getSelectedRow();
					
					
					
					if(r >=0 ){
						deleteb.setEnabled(true);
						updateb.setEnabled(true);
						resetb.setEnabled(true);
						insertb.setEnabled(false);
						
						id.setText(""+table.getModel().getValueAt(r,1));
						nameField.setText(""+table.getModel().getValueAt(r, 2));
						year.setText(""+table.getModel().getValueAt(r, 3));
						country.setText(""+table.getModel().getValueAt(r, 4));
						
						try{
							
							preStatement = con.prepareStatement("select * from texttbl where id ="+table.getModel().getValueAt(r,1));
							System.out.println(preStatement);
							ResultSet rs = preStatement .executeQuery();
							if(rs.next()){				
		 					String text2 = rs.getString(2);
							textArea.setText(text2);
							}	
						
						    }catch(SQLException e1){
							      e1.printStackTrace();
						    }
						
					}//end of if(r>=0)
				
						
						
					  
				}// end of mouseClicked()
				@Override
				public void mouseReleased(MouseEvent e){
					
				}
				@Override
				public void mousePressed(MouseEvent e){
					
				}
				@Override
				public void mouseExited(MouseEvent e){
					
				}
				@Override
				public void mouseEntered(MouseEvent e){
					
				}
			});// end of addMouseListener()
			

			selectb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
					preStatement = con.prepareStatement("select * from pritbl where name = ' ?' ");
					//rstLast = stmt.executeQuery("select * from pritbl where name = ' ?' ");
					preStatement.setString(1, nameField.getText());
					
					rstLast.last();
					String string = serialNo++ 
							+ "," 
							+ String.valueOf(rstLast.getInt(1)) 
							+ "," 
							+ rstLast.getString(2) 
							+ ","
							+ rstLast.getString(3)
							+ ","
							+ rstLast.getString(4);
					Object[] row = null;
					row = string.split(",");
					model.addRow(row);
					panel.revalidate();
					blankFields();
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}
				}
			});
			
			addRows(); 

		
	}
	
	private void addRows(){
		try{
			Object[] row = null;
			serialNo = 1;
			rst = stmt.executeQuery("select * from pritbl");
			while(rst.next()){
				String string = serialNo++ 
						+ "," 
						+ String.valueOf(rst.getInt(1)) 
						+ "," 
						+ rst.getString(2) 
						+ ","
						+ rst.getString(3)
						+ ","
						+ rst.getString(4);
				row = string.split(",");
				model.addRow(row);
			}
			panel.revalidate();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	private void refreshTable(){
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		dm.getDataVector().removeAllElements();
		System.out.println("Refresh Table");
		
		addRows();
	}
	
	private void blankFields(){
		id.setText("");
		nameField.setText("");
		year.setText("");
		country.setText("");
		textArea.setText("");
	}

	
	public void connect(){
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdba?useSSL=false", 
					"root", "tkddls6222");
			
			stmt = con.createStatement();
		
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new AirExTest();
	}
}
