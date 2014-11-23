package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cs414.groupH.a5.http.InStoreHttpClient;
import cs414.groupH.a5.http.RequestHandler;
import cs414.groupH.a5.http.XmlHelper;

public class CustLoginDialog extends JDialog implements MouseListener {

	private static final long serialVersionUID = 6618108255319022180L;
	
	JButton guest_btn;
	JButton accept_btn;
	JButton cancel_btn;
	JLabel returning_lbl;
	JLabel uname_lbl;
	JLabel pwd_lbl;
	JTextField empId_txt;
	JTextField pwd_txt;
	
	
	public CustLoginDialog() {
		guest_btn = new JButton("Continue as guest");
		accept_btn = new JButton("Continue as guest");
		cancel_btn = new JButton("Login");
		returning_lbl = new JLabel("Returning Customer:");
		uname_lbl = new JLabel("Username:");
		pwd_lbl = new JLabel("Password:");
		empId_txt = new JTextField();
		pwd_txt = new JPasswordField();
		
		this.setLayout(new GridLayout(4,2));
		this.setSize(new Dimension(800, 500));
		this.setModal(true);
		
		this.add(returning_lbl);
		this.add(new JLabel());
		this.add(uname_lbl);
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
		if (e.getSource() == cancel_btn) {
			if (!empId_txt.getText().isEmpty() &&!pwd_txt.getText().isEmpty()) {
				String name = InStoreHttpClient.loginCust(empId_txt.getText(), pwd_txt.getText());
				if (!name.equalsIgnoreCase("invalid")) {
					String custXml = XmlHelper.getCustomerXml(name);
					RequestHandler.setCustomerXml(custXml);
					new OrderDialog();
					this.dispose();
				}
				else {
					if (this.getContentPane().getComponentCount() < 9) {
						this.setLayout(new GridLayout(5,2));
						this.add(new JLabel("Invalid username or password. Please try again."));
						this.revalidate();
					}
				}
			}
			else {
				if (this.getContentPane().getComponentCount() < 9) {
					this.setLayout(new GridLayout(5,2));
					this.add(new JLabel("Invalid username or password. Please try again."));
					this.revalidate();
				}
			}
		}
		else if (e.getSource()== accept_btn) {
			new OrderDialog();
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
