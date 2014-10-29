package cs414.groupH.a4.order;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cs414.groupH.a4.manager.SystemManager;

public class ViewOrderDetails extends JDialog implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel Cust;
	JLabel Addr;
	JLabel Phone;
	JTable OrderItems;
	JLabel payed;
	JButton back_btn;
	public ViewOrderDetails(Order o) {		
		
		Cust = new JLabel("Customer: " + o.getCustomer().getName());			
		Addr = new JLabel("Address: " + o.getCustomer().getAddress().getStreet()+" "+o.getCustomer().getAddress().getCity()+", "+o.getCustomer().getAddress().getState()+" "+o.getCustomer().getAddress().getZip());
		Phone = new JLabel("Phone: " + o.getCustomer().getAddress().getPhone());
		back_btn = new JButton("Back");
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(o.getItems().size(), 2));       
        
		String dataValues[][] = new String[o.getItems().size()][1];
        for(int i=0; i<o.getItems().size(); i++){
        	dataValues[i][0] = o.getItems().get(i).getName();
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
		// TODO Auto-generated method stub
		
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
