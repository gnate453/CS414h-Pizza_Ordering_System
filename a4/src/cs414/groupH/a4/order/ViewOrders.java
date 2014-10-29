package cs414.groupH.a4.order;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs414.groupH.a4.manager.SystemManager;

public class ViewOrders extends JDialog implements MouseListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;

	JButton back_btn;
	JTable table;
	JButton add;
	
	public ViewOrders() {		
		
		List<Order> orders = SystemManager.getOrders();
        
		back_btn = new JButton("Back");	
		
		add = new JButton("Mark Complete");
		add.addMouseListener(this);
		
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(orders.size(), 2));       
        
		String dataValues[][] = new String[orders.size()][2];
        for(int i=0; i<orders.size(); i++){
        	dataValues[i][0] = orders.get(i).getOrderId();
        	dataValues[i][1] = String.valueOf(orders.get(i).isComplete());
        }        
        
        String columnNames[] = {"Order ID","Completed?"};
        table = new JTable(dataValues, columnNames);
        JScrollPane pane = new JScrollPane(table);
        back_btn.addMouseListener(this);
        this.add(back_btn);
        
        this.add(pane);
        this.add(add);
        
        this.addMouseListener(this);
		this.setVisible(true);
	}

    @Override
    public void mouseClicked(MouseEvent me) {
    	if (me.getSource() == add) {
    		SystemManager.markOrderComplete(table.getValueAt(table.getSelectedRow(), 0).toString());
    		this.dispose();
    		new ViewOrders();
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

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
	}
}