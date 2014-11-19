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

public class DebitPaymentDialog extends JDialog implements MouseListener  {
	
	private static final long serialVersionUID = 5581859536284808691L;
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
	JLabel cardPin;
	JTextField cardPinTXT;
	JLabel Error;
	
	DebitPaymentDialog() {
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
		cardPin = new JLabel("Card Pin Number:");
		cardPinTXT = new JTextField();
		Error = new JLabel("Please Populate All Fields Correctly");
		
		this.setLayout(new GridLayout(7,2));
		this.setPreferredSize(new Dimension(50, 100) );
		this.setModal(true);
		
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
		this.add(cardPin);
		this.add(cardPinTXT);
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
		if (e.getSource() == Accept) {
			if (cardNumberTXT.getText().isEmpty()|| !isNumeric(cardNumberTXT.getText())) {
				if (this.getContentPane().getComponentCount() < 13) {
					this.add(Error);
					this.revalidate();
				}
			}
			else if (cardSecureTXT.getText().isEmpty() || !isNumeric(cardSecureTXT.getText())) {
				if (this.getContentPane().getComponentCount() < 13) {
					this.add(Error);
					this.revalidate();
				}
			}
			else if (cardHolderTXT.getText().isEmpty()) {
				if (this.getContentPane().getComponentCount() < 13) {
					this.add(Error);
					this.revalidate();
				}
			}
			else if (cardExpTXT.getText().isEmpty() || !isNumeric(cardExpTXT.getText())) {
				if (this.getContentPane().getComponentCount() < 13) {
					this.add(Error);
					this.revalidate();
				}
			}
			else if (cardPinTXT.getText().isEmpty() || !isNumeric(cardPinTXT.getText())) {
				if (this.getContentPane().getComponentCount() < 13) {
					this.add(Error);
					this.revalidate();
				}
			}
			else {
				this.setVisible(false);
				if(!cardNumberTXT.getText().equals("")||!cardSecureTXT.getText().equals("")||!cardExpTXT.getText().equals("")){
					String paymentXml = XmlHelper.getDeditPaymentXml(cardHolderTXT.getText(), cardNumberTXT.getText(), cardSecureTXT.getText(), cardExpTXT.getText(), cardPinTXT.getText());
					RequestHandler.addPaymentXml(paymentXml);
				}				
				this.dispose();
			}
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
