package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginDialog extends JDialog implements MouseListener {

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
		this.setSize(new Dimension(800, 500));
		this.setModal(true);
		
		this.add(empId_lbl);
		this.add(empId_txt);
		this.add(pwd_lbl);
		this.add(pwd_txt);
		accept_btn.addMouseListener(this);
		this.add(accept_btn);
		cancel_btn.addMouseListener(this);
		this.add(cancel_btn);
		
		this.addMouseListener(this);
		this.setVisible(true);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == accept_btn) {
			boolean valid = EmployeeManager.verifyCreds(empId_txt.getText(), pwd_txt.getText());
			if (valid) {
				InStoreEmployeeApp.loginEmployee(EmployeeManager.findEmployee(empId_txt.getText()));
				this.dispose();
			}
			else {
				if (this.getContentPane().getComponentCount() < 7) {
					this.setLayout(new GridLayout(4,2));
					this.add(new JLabel("Invalid ID or password. Please try again."));
					this.revalidate();
				}
			}
		}
		else if (e.getSource()== cancel_btn) {
			this.dispose();
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {

	}
	@Override
	public void mouseExited(MouseEvent arg0) {

	}
	@Override
	public void mousePressed(MouseEvent arg0) {

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}
