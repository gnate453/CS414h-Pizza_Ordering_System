package cs414.groupH.a4.payment;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class CreditPaymentDialog extends JDialog implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7381338780788035436L;
	JButton Accept;
	JButton Cancel;
	JLabel cardHolder;
	JTextField cardHolderTXT;
	JLabel cardNumber;
	JTextField cardNumberTXT;
	JLabel cardSecure;
	JTextField cardSecureTXT;
	JLabel cardExp;
	JTextField cardExpTXT;
	JLabel Error;
	CreditPayment DialogForCr;
	public CreditPaymentDialog(CreditPayment p){
		DialogForCr = p;
		this.setSize(new Dimension(750, 500));
		Accept = new JButton("Accept");
		Cancel = new JButton("Cancel");
		cardHolder = new JLabel("Card Holder Name:");
		cardHolderTXT = new JTextField();
		cardNumber = new JLabel("Card Number:");
		cardNumberTXT = new JTextField();
		cardSecure = new JLabel("Card Security Code:");
		cardSecureTXT = new JTextField();
		cardExp = new JLabel("Card Experation Date:");
		cardExpTXT = new JTextField();
		Error = new JLabel("Please Populate All Fields Correctly");
		
		this.setModal(true);
		this.setLayout(new GridLayout(6,2));
		this.setPreferredSize(new Dimension(50, 100) );
		
		Accept.addMouseListener(this);
		Cancel.addMouseListener(this);
		this.add(cardHolder);
		this.add(cardHolderTXT);
		this.add(cardNumber);
		this.add(cardNumberTXT);
		this.add(cardSecure);
		this.add(cardSecureTXT);
		this.add(cardExp);
		this.add(cardExpTXT);
		this.add(Accept);
		this.add(Cancel);
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
		if (e.getSource() == Accept)
		{
			if (cardNumberTXT.getText().isEmpty()|| !isNumeric(cardNumberTXT.getText())) {
				if (this.getContentPane().getComponentCount() < 11) {
					this.add(Error);
					this.revalidate();
				}
			}
			else if (cardSecureTXT.getText().isEmpty() || !isNumeric(cardSecureTXT.getText())) {
				if (this.getContentPane().getComponentCount() < 11) {
					this.add(Error);
					this.revalidate();
				}
			}
			else if (cardHolderTXT.getText().isEmpty()) {
				if (this.getContentPane().getComponentCount() < 11) {
					this.add(Error);
					this.revalidate();
				}
			}
			else if (cardExpTXT.getText().isEmpty() || !isNumeric(cardExpTXT.getText())) {
				if (this.getContentPane().getComponentCount() < 11) {
					this.add(Error);
					this.revalidate();
				}
			}
			else {
				this.setVisible(false);
				if(!cardNumberTXT.getText().equals("")||!cardSecureTXT.getText().equals("")||!cardExpTXT.getText().equals("")){
					DialogForCr.setFields(cardHolderTXT.getText(), cardNumberTXT.getText(), cardSecureTXT.getText(), cardExpTXT.getText());
				}				
				this.dispose();
			}
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
