package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class PaymentMethodDialog extends JDialog implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	JButton Credit;
	JButton GiftCard;
	JButton Cancel;
	JButton Cash;
	JButton Debit;
	JLabel owed;
	JLabel owed2;
	double d = 0;
	
	public PaymentMethodDialog(double amount) {
		DecimalFormat df = new DecimalFormat("###0.00");
		d = amount;
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
		if (e.getSource() == Credit) {
			new CreditPaymentDialog();
			this.setVisible(false);
			this.dispose();
		}
		else if (e.getSource()== Debit) {
			new DebitPaymentDialog();
			this.setVisible(false);
			this.dispose();
		}
		else if (e.getSource()== Cash) {
			new PaymentDialog(d);
			this.setVisible(false);
			/*if(c.getAmount() > d) {
				new CashBack((c.getAmount()-d));
			}*/
			this.dispose();
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
