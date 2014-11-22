package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cs414.groupH.a5.http.InStoreHttpClient;

public class EditItemPage extends JDialog implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	JButton accept_btn;
	JButton cancel_btn;
	JLabel name_lbl;
	JLabel price_lbl;
	JLabel special_lbl;
	JTextField name_txt;
	JTextField price_txt;
	JCheckBox special_chkbox;
	boolean isSpecial;
	String oldName;
	
	JLabel errorName;
	JLabel errorPrice;

	public EditItemPage(String name, double price, boolean isSpecial) {
		this.setSize(new Dimension(500, 1000));
		this.isSpecial = isSpecial;
		this.oldName = name;
		
		accept_btn = new JButton("accept");
		cancel_btn = new JButton("cancel");
		name_lbl = new JLabel("Name:");
		price_lbl = new JLabel("Price:");
		special_lbl = new JLabel("Daily Special:");
		name_txt = new JTextField(name);
		price_txt = new JTextField(Double.toString(price));
		special_chkbox = new JCheckBox("Yes");
		special_chkbox.setSelected(isSpecial);
		
		errorName = new JLabel("ERROR: Please enter a name.");
		errorPrice = new JLabel("ERROR: Please enter a valid price.");
		
		this.setLayout(new GridLayout(5,2));
		this.setPreferredSize(new Dimension(50, 100));
		
		this.add(name_lbl);
		this.add(name_txt);
		this.add(price_lbl);
		this.add(price_txt);
		this.add(special_lbl);
		this.add(special_chkbox);
		accept_btn.addMouseListener(this);
		cancel_btn.addMouseListener(this);
		this.add(accept_btn);
		this.add(cancel_btn);
		
		this.addMouseListener(this);
		this.setVisible(true);
	}

	public static boolean isNumeric(String str)  
	{  
		try {  
			Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == accept_btn) {
			if (name_txt.getText().isEmpty()) {
				if (this.getContentPane().getComponentCount() < 10) {
					this.add(errorName);
					this.revalidate();
				}
			}
			else if (price_txt.getText().isEmpty() || !isNumeric(price_txt.getText())) {
				if (this.getContentPane().getComponentCount() < 10) {
					this.add(errorPrice);
					this.revalidate();
				}
			}
			else if (Double.parseDouble(price_txt.getText()) < 0.0) {
				if (this.getContentPane().getComponentCount() < 10) {
					this.add(errorPrice);
					this.revalidate();
				}
			}
			else {
				InStoreHttpClient.editMenuItem(oldName, name_txt.getText(), price_txt.getText(), Boolean.toString(special_chkbox.isSelected()));
					
				this.dispose();
			}
		}
		else if (e.getSource()== cancel_btn) {
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
