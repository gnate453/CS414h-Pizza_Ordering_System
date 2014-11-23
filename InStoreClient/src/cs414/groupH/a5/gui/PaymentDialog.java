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

public class PaymentDialog extends JDialog implements MouseListener{
	
	private static final long serialVersionUID = 5317400034609838961L;
	JButton Accept;
	JButton Cancel;
	JLabel AmountLBL;
	JTextField AmountTXT;
	OrderDialog parentOrder;
	
	public PaymentDialog(OrderDialog order) {
		parentOrder = order;
		this.setSize(new Dimension(600, 255));
		Accept = new JButton("Accept");
		Cancel = new JButton("Cancel");
		AmountLBL = new JLabel("Amount:");
		AmountTXT = new JTextField();
		
		this.setModal(true);
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
	
	public static boolean isNumeric(String str) {  
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
			if (AmountTXT.getText().isEmpty() || !isNumeric(AmountTXT.getText())) {
				if (this.getContentPane().getComponentCount() < 5) {
					this.setLayout(new GridLayout(3,2));
					this.add(new JLabel("ERROR: Enter a valid amount."));
					this.revalidate();
				}
			}
			else if (Double.parseDouble(AmountTXT.getText()) <= 0.0) {
				if (this.getContentPane().getComponentCount() < 5) {
					this.add(new JLabel("ERROR: Enter a valid amount."));
					this.revalidate();
				}
			}
			else {
				String paymentXml = XmlHelper.getCashPaymentXml(AmountTXT.getText());
				RequestHandler.addPaymentXml(paymentXml);
				double amtPaid = Double.parseDouble(AmountTXT.getText());
				if(amtPaid > parentOrder.getAmountDue()) {
					new CashBack((amtPaid-parentOrder.getAmountDue()));
				}
				parentOrder.addPayment(amtPaid);
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
