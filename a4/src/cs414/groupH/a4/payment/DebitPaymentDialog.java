package cs414.groupH.a4.payment;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DebitPaymentDialog extends JDialog implements MouseListener  {
	/**
	 * 
	 */
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
	DebitPayment DialogForDb;
	
	DebitPaymentDialog(DebitPayment p){
		DialogForDb = p;
		this.setSize(new Dimension(500, 500));
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
		
		this.setLayout(new GridLayout(6,2));
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == Accept)
		{
			this.setVisible(false);
			DialogForDb.setFields(cardHolderTXT.getText(), cardNumberTXT.getText(), cardSecureTXT.getText(), cardExpTXT.getText(),cardPinTXT.getText());
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
