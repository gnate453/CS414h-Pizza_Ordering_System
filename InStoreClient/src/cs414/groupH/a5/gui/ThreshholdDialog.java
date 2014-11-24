package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cs414.groupH.a5.http.InStoreHttpClient;

public class ThreshholdDialog extends JDialog implements MouseListener {

	private static final long serialVersionUID = 6618108255319022180L;
	
	JButton accept_btn;
	JButton cancel_btn;
	JLabel curThres_lbl;
	JLabel newThres_lbl;
	JLabel curThres_txt;
	JTextField newThres_txt;
	
	
	public ThreshholdDialog() {
		accept_btn = new JButton("change");
		cancel_btn = new JButton("cancel");
		curThres_lbl = new JLabel("Current threshold:");
		newThres_lbl = new JLabel("New threshold:");
		curThres_txt = new JLabel(InStoreHttpClient.getThreshold());
		newThres_txt = new JTextField();
		
		this.setLayout(new GridLayout(3,2));
		this.setSize(new Dimension(800, 500));
		this.setModal(true);
		
		this.add(curThres_lbl);
		this.add(curThres_txt);
		this.add(newThres_lbl);
		this.add(newThres_txt);
		accept_btn.addMouseListener(this);
		this.add(accept_btn);
		cancel_btn.addMouseListener(this);
		this.add(cancel_btn);
		
		this.addMouseListener(this);
		this.setVisible(true);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == accept_btn) {
			InStoreHttpClient.setThreshold(newThres_txt.getText());
			this.dispose();		
		}
		else if (e.getSource()== cancel_btn) {
			this.dispose();
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {

	}
	@Override
	public void mouseExited(MouseEvent arg0) {

	}
	@Override
	public void mousePressed(MouseEvent arg0) {

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}
