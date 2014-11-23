package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewOrderDetails extends JDialog implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	JLabel Cust;
	JLabel Addr;
	JLabel Phone;
	JTable OrderItems;
	JLabel payed;
	JButton back_btn;
	
	public ViewOrderDetails(String custName, String[] addr, String[] items) {		
		
		Cust = new JLabel("Customer: " + custName);	
		if (addr.length == 5) {
			if(addr[0].isEmpty()){
				Addr = new JLabel("Address: ");
			}else{
				Addr = new JLabel("Address: " +addr[0]+" "+addr[1]+", "+addr[2]+" "+addr[3]);
			}
			
			Phone = new JLabel("Phone: " + addr[4]);
		}
		else {
			Addr = new JLabel("Address: ");
			Phone = new JLabel("Phone: ");
		}
		
		back_btn = new JButton("Back");
        this.setSize(new Dimension(800, 400));
		this.setLayout(new GridLayout(3, 2));       
        
		String dataValues[][] = new String[items.length][1];
        for(int i=0; i<items.length; i++){
        	dataValues[i][0] = items[i];
        }        
        
        String columnNames[] = {"Item"};
        OrderItems = new JTable(dataValues, columnNames);
        JScrollPane pane = new JScrollPane(OrderItems);
        back_btn.addMouseListener(this);
        this.add(Cust);
        this.add(Addr);
        this.add(Phone);       
        this.add(pane);
        this.add(back_btn); 
        
        this.addMouseListener(this);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == back_btn) {
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
