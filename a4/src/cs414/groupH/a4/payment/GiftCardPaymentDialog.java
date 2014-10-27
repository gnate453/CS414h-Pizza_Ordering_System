package cs414.groupH.a4.payment;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GiftCardPaymentDialog extends JDialog implements MouseListener  {
	JButton Accept;
	JButton Cancel;
	JLabel Balance;
	JTextField BalanceTXT;
	JLabel cardNumber;
	JTextField cardNumberTXT;
	GiftCardPayment DialogFor;
	public GiftCardPaymentDialog(GiftCardPayment p){
		DialogFor = p;
		this.setSize(new Dimension(500, 500));
		Accept = new JButton("Accept");
		Cancel = new JButton("Cancel");
		cardNumber = new JLabel("Card Number:");
		cardNumberTXT = new JTextField();
		Balance = new JLabel("Balance:");
		BalanceTXT = new JTextField();
		this.setLayout(new GridLayout(3,2));
		this.setPreferredSize(new Dimension(50, 100) );
		Accept.addMouseListener(this);
		Cancel.addMouseListener(this);
		this.add(cardNumber);
		this.add(cardNumberTXT);
		this.add(Balance);
		this.add(BalanceTXT);
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
			DialogFor.setFields(Integer.parseInt(BalanceTXT.getText()),Integer.parseInt(cardNumberTXT.getText()));
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
