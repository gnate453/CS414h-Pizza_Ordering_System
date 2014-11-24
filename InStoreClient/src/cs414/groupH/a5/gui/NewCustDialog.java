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

public class NewCustDialog extends JDialog implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	JButton register_btn;
	JButton cancel_btn;
	JLabel name_lbl;
	JLabel street_lbl;
	JLabel city_lbl;
	JLabel state_lbl;
	JLabel zip_lbl;
	JLabel phone_lbl;
	JLabel uname_lbl;
	JLabel pwd_lbl;
	JTextField name_txt;
	JTextField street_txt;
	JTextField city_txt;
	JTextField state_txt;
	JTextField zip_txt;
	JTextField phone_txt;
	JTextField uname_txt;
	JPasswordField pwd_txt;
	
	
	public NewCustDialog() {
		super();
		this.setSize(new Dimension(1000, 500));
		
		register_btn = new JButton("Register");
		cancel_btn = new JButton("cancel");
		name_lbl = new JLabel("Name:");
		street_lbl = new JLabel("Street:");
		city_lbl = new JLabel("City:");
		state_lbl = new JLabel("State:");
		zip_lbl = new JLabel("Zip:");
		phone_lbl = new JLabel("Phone:");
		uname_lbl = new JLabel("Username:");
		pwd_lbl = new JLabel("Password:");
		name_txt = new JTextField();
		street_txt = new JTextField();
		city_txt = new JTextField();
		state_txt = new JTextField();
		zip_txt = new JTextField();
		phone_txt = new JTextField();
		uname_txt = new JTextField();
		pwd_txt = new JPasswordField();
		
		this.setModal(true);
		this.setLayout(new GridLayout(7,2));
		this.setPreferredSize(new Dimension(50, 100));
		
		this.add(name_lbl);
		this.add(name_txt);
		this.add(street_lbl);
		this.add(street_txt);
		this.add(city_lbl);
		this.add(city_txt);
		this.add(state_lbl);
		this.add(state_txt);
		this.add(zip_lbl);
		this.add(zip_txt);
		this.add(phone_lbl);
		this.add(phone_txt);
		register_btn.addMouseListener(this);
		cancel_btn.addMouseListener(this);
		this.add(register_btn);
		this.add(cancel_btn);
		
		this.addMouseListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setVisible(true);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == register_btn) {
			
			
			String addressXml = XmlHelper.getAddressXml(street_txt.getText(), city_txt.getText(), state_txt.getText(), zip_txt.getText(), phone_txt.getText());
			RequestHandler.setAddressXml(addressXml);
			this.setVisible(false);
			this.dispose();
		}
		else if (e.getSource()== cancel_btn) {
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
