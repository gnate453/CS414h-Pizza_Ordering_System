package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class CashBack extends JDialog implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	JButton Close;
	JLabel Amount;
	
	public CashBack(double d) {
		this.setSize(new Dimension(255, 255));
		Close = new JButton("Close");
		Amount = new JLabel("Change:" + String.valueOf(d));
		
		this.setModal(true);
		this.setLayout(new GridLayout(2,1));
		this.setPreferredSize(new Dimension(50, 100) );
		
		Close.addMouseListener(this);
		this.add(Amount);
		this.add(Close);
	
		this.addMouseListener(this);
		this.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		 if (e.getSource()== Close){
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
