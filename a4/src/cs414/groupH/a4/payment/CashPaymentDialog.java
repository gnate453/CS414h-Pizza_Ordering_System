package cs414.groupH.a4.payment;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CashPaymentDialog extends JDialog implements MouseListener{
	JButton Accept;
	JButton Cancel;
	JLabel AmountLBL;
	JTextField AmountTXT;
	CashPayment DialogFor;
	public CashPaymentDialog(CashPayment p){
		DialogFor = p;
		this.setSize(new Dimension(255, 255));
		Accept = new JButton("Accept");
		Cancel = new JButton("Cancel");
		AmountLBL = new JLabel("Amount:");
		AmountTXT = new JTextField();
		this.setLayout(new GridLayout(2,2));
		this.setPreferredSize(new Dimension(50, 100) );
		Accept.addMouseListener(this);
		Cancel.addMouseListener(this);
		this.add(AmountLBL);
		this.add(AmountTXT);
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
			DialogFor.setFields(Double.parseDouble(AmountTXT.getText()));
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
