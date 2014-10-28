package cs414.groupH.a4.customer;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cs414.groupH.a4.address.Address;

public class CustomerDialog extends JDialog implements MouseListener  {
	JButton Accept;
	JButton Cancel;
	JLabel name;
	JTextField nameTXT;
	Customer DialogFor;
	Address a;
	CustomerDialog(Customer c){
		a = new Address();
		DialogFor = c;
		this.setSize(new Dimension(500, 255));
		Accept = new JButton("Accept");
		Cancel = new JButton("Cancel");
		name = new JLabel("Customer Name:");
		nameTXT = new JTextField();
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
		if (e.getSource() == Accept)
		{
			DialogFor.addAddress(a);
			DialogFor.setFields(nameTXT.getText());
			this.setVisible(false);
		}
		else if (e.getSource()== Cancel)
		{
			this.setVisible(false);
			this.dispose();
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
