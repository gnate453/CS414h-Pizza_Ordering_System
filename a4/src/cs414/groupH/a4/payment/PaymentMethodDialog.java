package cs414.groupH.a4.payment;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class PaymentMethodDialog extends JDialog implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton Credit;
	JButton GiftCard;
	JButton Cancel;
	JButton Cash;
	JButton Debit;
	JLabel owed;
	JLabel owed2;
	ArrayList<Payment> pay;
	public PaymentMethodDialog(ArrayList<Payment> p, double amount){
		DecimalFormat df = new DecimalFormat("###0.00");
		pay = p;
		this.setSize(new Dimension(500, 500));
		
		Credit = new JButton("Credit");
		Debit = new JButton("Debit");
		Cash = new JButton("Cash");
		Cancel = new JButton("Cancel");
		owed = new JLabel("Amount Due: " + df.format(amount).replaceAll( "^-(?=0(.0*)?$)", ""));
		
		this.setModal(true);
		this.setLayout(new GridLayout(3,2));
		this.setPreferredSize(new Dimension(50, 100) );
		
		Credit.addMouseListener(this);
		Debit.addMouseListener(this);
		Cash.addMouseListener(this);
		Cancel.addMouseListener(this);
		
		this.add(Credit);
		this.add(Debit);
		this.add(Cash);
		this.add(Cancel);
		this.add(owed);
		this.addMouseListener(this);
		this.setVisible(true);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == Credit)
		{
			CreditPayment c = new CreditPayment();
			pay.add(c);
			this.setVisible(false);
			this.dispose();
		}
		else if (e.getSource()== Debit)
		{
			DebitPayment d = new DebitPayment();
			pay.add(d);
			this.setVisible(false);
			this.dispose();
		}
		else if (e.getSource()== Cash)
		{
			CashPayment c = new CashPayment();
			pay.add(c);
			this.setVisible(false);
			this.dispose();
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
