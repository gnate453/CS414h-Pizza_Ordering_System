package cs414.groupH.a4.address;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddressDialog extends JDialog implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton accept_btn;
	JButton cancel_btn;
	JLabel street_lbl;
	JLabel city_lbl;
	JLabel state_lbl;
	JLabel zip_lbl;
	JLabel phone_lbl;
	JTextField street_txt;
	JTextField city_txt;
	JTextField state_txt;
	JTextField zip_txt;
	JTextField phone_txt;
	Address dialogFor;
	
	
	AddressDialog(Address a) {
		this.setSize(new Dimension(500, 1000));
		dialogFor = a;
		
		accept_btn = new JButton("accept");
		cancel_btn = new JButton("cancel");
		street_lbl = new JLabel("Street:");
		city_lbl = new JLabel("City:");
		state_lbl = new JLabel("State:");
		zip_lbl = new JLabel("Zip:");
		phone_lbl = new JLabel("Phone:");
		street_txt = new JTextField();
		city_txt = new JTextField();
		state_txt = new JTextField();
		zip_txt = new JTextField();
		phone_txt = new JTextField();
		
		this.setLayout(new GridLayout(5,2));
		this.setPreferredSize(new Dimension(50, 100));
		
		this.add(street_lbl);
		this.add(street_txt);
		this.add(city_lbl);
		this.add(city_txt);
		this.add(state_lbl);
		this.add(state_txt);
		this.add(zip_lbl);
		this.add(zip_txt);
		accept_btn.addMouseListener(this);
		cancel_btn.addMouseListener(this);
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
			dialogFor.setFields(street_txt.getText(), city_txt.getText(), state_txt.getText(), zip_txt.getText(), phone_txt.getText());
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
