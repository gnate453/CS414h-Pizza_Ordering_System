package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cs414.groupH.a5.http.InStoreHttpClient;

public class ViewOrders extends JDialog implements MouseListener {

	private static final long serialVersionUID = 1L;

	JButton back_btn;
	JButton details_btn;
	JTable table;
	JButton markComplete;
	
	public ViewOrders() {		
		back_btn = new JButton("Back");	
		details_btn = new JButton("Details");
		
		markComplete = new JButton("Mark Complete");
		markComplete.addMouseListener(this);
		
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(2, 2));       
        
		String[] orders = InStoreHttpClient.getOrders().split(",");
		String dataValues[][] = new String[(orders.length/2)][2];
		int j = 0;
        for(int i=0; i<orders.length; i++){
        	dataValues[j][0] = orders[i];
        	dataValues[j][1] = orders[i+1];
        	i++;
        	j++;
        }        
        
        String columnNames[] = {"Order ID","Completed?"};
        table = new JTable(dataValues, columnNames);
        JScrollPane pane = new JScrollPane(table);
        back_btn.addMouseListener(this);
        details_btn.addMouseListener(this);
        this.add(back_btn);
        this.add(pane);
        this.add(details_btn);
        this.add(markComplete);
        
        this.addMouseListener(this);
		this.setVisible(true);
	}

    @Override
    public void mouseClicked(MouseEvent me) {
    	if (me.getSource() == markComplete) {
    		if (table.getSelectedRow() != -1) {
	    		//SystemManager.markOrderComplete(table.getValueAt(table.getSelectedRow(), 0).toString());
	    		this.dispose();
    		}
    	}else if(me.getSource() == details_btn){  
    		if (table.getSelectedRow() != -1) {
    			//Order o = SystemManager.findOrder(table.getValueAt(table.getSelectedRow(), 0).toString());
    			String custName = InStoreHttpClient.getOrderCust(table.getValueAt(table.getSelectedRow(), 0).toString());
    			String[] addr = InStoreHttpClient.getOrderAddr(table.getValueAt(table.getSelectedRow(), 0).toString());
    			String[] items = InStoreHttpClient.getOrderItems(table.getValueAt(table.getSelectedRow(), 0).toString());
    			
    			new ViewOrderDetails(custName, addr, items);
    			this.dispose();
    		}
    	}
		if (me.getSource() == back_btn) {
			this.dispose();
		}
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}