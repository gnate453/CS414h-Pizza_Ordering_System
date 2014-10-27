package cs414.groupH.a4.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cs414.groupH.a4.manager.EmployeeManager;

public class LoginDialog extends JDialog implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6618108255319022180L;
	
	JButton accept_btn;
	JButton cancel_btn;
	JLabel empId_lbl;
	JLabel pwd_lbl;
	JTextField empId_txt;
	JTextField pwd_txt;
	
	
	public LoginDialog() {
		accept_btn = new JButton("accept");
		cancel_btn = new JButton("cancel");
		empId_lbl = new JLabel("Employee ID:");
		pwd_lbl = new JLabel("Password:");
		empId_txt = new JTextField();
		pwd_txt = new JTextField();
		
		this.setLayout(new GridLayout(3,2));
		this.setPreferredSize(new Dimension(50, 100) );
		
		this.add(empId_lbl);
		this.add(empId_txt);
		this.add(pwd_lbl);
		this.add(pwd_txt);
		this.add(accept_btn);
		this.add(cancel_btn);
		
		this.addMouseListener(this);
		this.setVisible(true);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == accept_btn)
		{
			this.setVisible(false);
			EmployeeManager.verifyCreds(empId_txt.getText(), pwd_txt.getText());
		}
		else if (e.getSource()== cancel_btn)
		{
			this.setVisible(false);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
