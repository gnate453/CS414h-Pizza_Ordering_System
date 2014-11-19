package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cs414.groupH.a5.http.RequestHandler;
import cs414.groupH.a5.http.XmlHelper;

public class CustomerDialog extends JDialog implements MouseListener  {
	
	private static final long serialVersionUID = 6922442609951102439L;
	JButton Accept;
	JButton Cancel;
	JLabel name;
	JTextField nameTXT;
	
	public CustomerDialog() {
		new AddressDialog();
		this.setSize(new Dimension(500, 255));
		Accept = new JButton("Accept");
		Cancel = new JButton("Cancel");
		name = new JLabel("Customer Name:");
		nameTXT = new JTextField();
		
		this.setModal(true);
		this.setLayout(new GridLayout(2,2));
		this.setPreferredSize(new Dimension(50, 100) );
		
		Accept.addMouseListener(this);
		Cancel.addMouseListener(this);
		this.add(name);
		this.add(nameTXT);
		this.add(Accept);
		this.add(Cancel);
		this.addMouseListener(this);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == Accept) {
			String custXml = XmlHelper.getCustomerXml(nameTXT.getText());
			RequestHandler.setCustomerXml(custXml);
			this.setVisible(false);
		}
		else if (e.getSource()== Cancel) {
			this.setVisible(false);
			this.dispose();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}