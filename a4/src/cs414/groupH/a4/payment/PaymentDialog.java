package cs414.groupH.a4.payment;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PaymentDialog extends JDialog implements MouseListener{
	JButton Debit;
	JButton Credit;
	JButton Cash;
	JButton Gift;
	Payment DialogFor;
	CashPayment DialogForCa;
	CreditPayment DialogForCr;
	DebitPayment DialogForDb;
	GiftCardPayment DialogForGf;
	public PaymentDialog(Payment p){
		DialogFor = p;
		this.setSize(new Dimension(500, 500));
		Debit = new JButton("Debit");
		Credit = new JButton("Credit");
		Cash = new JButton("Cash");
		Gift = new JButton("Gift Card");
		this.setLayout(new GridLayout(2,2));
		this.setPreferredSize(new Dimension(50, 100) );
		Debit.addMouseListener(this);
		Cash.addMouseListener(this);
		Credit.addMouseListener(this);
		Gift.addMouseListener(this);
		this.add(Debit);
		this.add(Credit);
		this.add(Cash);
		this.add(Gift);
		
		this.addMouseListener(this);
		this.setVisible(true);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == Debit)
		{
			this.setVisible(false);	
			DialogForDb = new DebitPayment();
		}
		else if (e.getSource()== Credit)
		{
			this.setVisible(false);
			DialogForCr = new CreditPayment();
		}
		else if (e.getSource()== Cash)
		{
			this.setVisible(false);
			DialogForCa = new CashPayment();
		}
		else if (e.getSource()== Gift)
		{
			this.setVisible(false);
			DialogForGf = new GiftCardPayment();
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
