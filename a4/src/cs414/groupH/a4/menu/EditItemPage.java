package cs414.groupH.a4.menu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cs414.groupH.a4.manager.SystemManager;

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
		
		this.setLayout(new GridLayout(5,2));
		this.setPreferredSize(new Dimension(50, 100));
		
		this.add(name_lbl);
		this.add(name_txt);
		this.add(price_lbl);
		this.add(price_txt);
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
			if (name_txt.getText().equals("")) {
				this.add(new JLabel("ERROR: Please enter a name."));
				this.revalidate();
				this.repaint();
			}
			if (price_txt.getText().equals("") || !isNumeric(price_txt.getText())) {
				this.add(new JLabel("ERROR: Please enter a valid price."));
				this.revalidate();
				this.repaint();
			}
			else {
				if (e.getSource() == accept_btn) {
					SystemManager.editMenuItem(oldName, name_txt.getText(), Double.parseDouble(price_txt.getText()), special_chkbox.isSelected());
					
					this.dispose();
				}		
				else if (e.getSource()== cancel_btn) {
					this.dispose();
				}
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
