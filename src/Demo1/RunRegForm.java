package Demo1;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class RunRegForm extends RegistrationForm {
	String gender = "";
	ResultSet rst, rstLast;

	int serialNo;
	String SHOW = "Show";
	RegistrationForm formObject;
	
	// 생성자로써... 폼 화면상의 모든 이벤트를 처리한다.
	RunRegForm(){
		// name 필드 체크
		nameField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				if(nameField.getText().length()>=20)
					e.consume();
			}// end of keyTyped()
		});// end of addKeyListner()
		
		// gender 필드  체크
		male.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gender = "Male";
			}
		});
		female.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gender = "Female";
			}
		});
		
		// address 필드 체크
		addressField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				if(addressField.getText().length()>=20)
					e.consume();
			}// end of keyTyped()
		});// end of addKeyListner()
		
		// contact 필드 체크
		contactField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char c = e.getKeyChar();
				if(!((c >= '0') && (c <= '9') || 
					 (c == KeyEvent.VK_BACK_SPACE) ||
					 (c == KeyEvent.VK_DELETE) )){
					e.consume();
				} // end of if()
				
				if(contactField.getText().length() >= 10)
					e.consume();
			}// end of keyTyped()
		});// end of addKeyListner()
		
		// Exit 버튼 처리
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					con.close();
					System.exit(0);
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});
		
		// Register 버튼 처리
		registerButton.addActionListener(new AbstractAction(SHOW){
			public void actionPerformed(ActionEvent ae){
				try{
				  if(ae.getSource() == registerButton ) {
					if(nameField.getText().equals(""))
						JOptionPane.showMessageDialog(nameField, "Please fill name");
					else if(addressField.getText().equals(""))
						JOptionPane.showMessageDialog(addressField, "Please fill address");
					else if(contactField.getText().equals(""))
						JOptionPane.showMessageDialog(contactField, "Please fill contact");
					else if(gender.equals(""))
						JOptionPane.showMessageDialog(genderField, "Please select gender");
					else {
						preStatement.setString(1, nameField.getText());
						preStatement.setString(2, gender);
						preStatement.setString(3, addressField.getText());
						preStatement.setString(4, contactField.getText());
						// 
						int i = preStatement.executeUpdate();
						if(i==1){
							JOptionPane.showMessageDialog(panel, "Successfully Registered");
						}
						
						// showing last record
						rstLast = stmt.executeQuery("select * from regForm");
						rstLast.last();
						String string = serialNo++ 
								+ "," 
								+ String.valueOf(rstLast.getInt(1)) 
								+ "," 
								+ rstLast.getString(2) 
								+ ","
								+ rstLast.getString(3)
								+ ","
								+ rstLast.getString(4)
								+ ","
								+ rstLast.getString(5);
						Object[] row = null;
						row = string.split(",");
						model.addRow(row);
						panel.revalidate();
						
						blankFields();
					}
				  }// 등록 번튼일 때... 
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			  }
			});
		
		// delete 버튼 처리
		deleteButton.addActionListener(new AbstractAction(SHOW){
			public void actionPerformed(ActionEvent e){
				try{
					int r = table.getSelectedRow();
					if(r >= 0){
						if(JOptionPane.showConfirmDialog(panel, 
								"Are you sure want to delete this RECORD?",
								"WARNING",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							// 삭제를 원한다면... 
							String id = (String)table.getModel().getValueAt(r, 1);
							int i = stmt.executeUpdate("delete from regForm where id="+id);
							if(i==1){
								model.removeRow(r);
								
								blankFields();
								registerButton.setEnabled(true);
								deleteButton.setEnabled(false);
								updateButton.setEnabled(false);
								resetButton.setEnabled(false);
							}
						}
					}// end of if(r>=0)
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});
		// update 버튼 처리
		updateButton.addActionListener(new AbstractAction(SHOW){
			public void actionPerformed(ActionEvent e){
				if(nameField.getText().equals(""))
					JOptionPane.showMessageDialog(nameField, "Please fill name");
				else if(addressField.getText().equals(""))
					JOptionPane.showMessageDialog(addressField, "Please fill address");
				else if(contactField.getText().equals(""))
					JOptionPane.showMessageDialog(contactField, "Please fill contact");
				else if(gender.equals(""))
					JOptionPane.showMessageDialog(genderField, "Please select gender");
				else {
					int r = table.getSelectedRow();
					try{
						if(r>=0){
							if(male.isSelected())
								gender = male.getText();
							else 
								gender = female.getText();
							String id = (String)table.getModel().getValueAt(r, 1);
							updatePreStmt = con.prepareStatement(
									"update regForm set name=?, gender=?, address=?,contact=? "
									+ "where id="+id);
							updatePreStmt.setString(1, nameField.getText());
							updatePreStmt.setString(2, gender);
							updatePreStmt.setString(3, addressField.getText());
							updatePreStmt.setString(4, contactField.getText());
							// 
							int i = updatePreStmt.executeUpdate();
							if(i==1){
								table.setValueAt(nameField.getText(), r, 2);
								table.setValueAt(gender, r, 3);
								table.setValueAt(addressField.getText(), r, 4);
								table.setValueAt(contactField.getText(), r, 5);
							}
							else 
								JOptionPane.showMessageDialog(panel, "Update Failed");
							}
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}
				}
			}// end actionPerformed()
		});
		
		// reset 버튼 처리
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				blankFields();
				registerButton.setEnabled(true);
				updateButton.setEnabled(false);
				deleteButton.setEnabled(false);
				resetButton.setEnabled(false);
			}
		});
		// refresh 버튼 처리
		refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				refreshTable();
			}
		});
		//  table 상의 레코드에 마우스를 클릭할 때...
		table.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				int r = table.getSelectedRow();
				if(r >=0 ){
					deleteButton.setEnabled(true);
					updateButton.setEnabled(true);
					resetButton.setEnabled(true);
					registerButton.setEnabled(false);
					
					idField.setText(""+table.getModel().getValueAt(r, 1));
					nameField.setText(""+table.getModel().getValueAt(r, 2));
					if(table.getModel().getValueAt(r, 3).equals("Male"))
						male.setSelected(true);
					else female.setSelected(true);
					addressField.setText(""+table.getModel().getValueAt(r, 4));
					contactField.setText(""+table.getModel().getValueAt(r, 5));
					
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
		
		
		addRows();   // 첫 화면에서 전체 테이블 보여주기
		
	}// Constructor 
	
	private void addRows(){
		try{
			Object[] row = null;
			serialNo = 1;
			rst = stmt.executeQuery("select * from regForm");
			while(rst.next()){
				String string = serialNo++ 
						+ "," 
						+ String.valueOf(rst.getInt(1)) 
						+ "," 
						+ rst.getString(2) 
						+ ","
						+ rst.getString(3)
						+ ","
						+ rst.getString(4)
						+ ","
						+ rst.getString(5);
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
		idField.setText("");
		nameField.setText("");
		gender="";
		bg.clearSelection();
		addressField.setText("");
		contactField.setText("");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RunRegForm();
	}// end of main

}// end of class RunRegForm