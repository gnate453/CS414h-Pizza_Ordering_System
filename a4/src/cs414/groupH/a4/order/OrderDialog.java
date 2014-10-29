package cs414.groupH.a4.order;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cs414.groupH.a4.customer.Customer;
import cs414.groupH.a4.manager.SystemManager;
import cs414.groupH.a4.menu.Menu;
import cs414.groupH.a4.payment.Payment;
import cs414.groupH.a4.payment.PaymentMethodDialog;

public class OrderDialog extends JDialog implements MouseListener  {

	private static final long serialVersionUID = 1L;
	
	JTable menu;
	JTable order;
	JButton add;
	JButton remove;
	JButton Accept;
	JButton Cancel;
	JLabel total_lbl;
	JTextField total_txt;
	
	ArrayList<Payment> payments = new ArrayList<Payment>();
	ArrayList<String> selectedItems = new ArrayList<String>();
	Customer cust;	
	double total;

	public OrderDialog(Customer c){
		cust = c;
		total = 0;
		
		this.setModal(true);
		this.setSize(new Dimension(800, 500));
		Accept = new JButton("Accept");
		Cancel = new JButton("Cancel");
		add = new JButton("Add selected items to order");
		remove = new JButton("Remove selected items from order");
		total_lbl = new JLabel("Total: ");
		total_txt = new JTextField("0.00");

		this.setLayout(new GridLayout(4,2));
		this.setPreferredSize(new Dimension(50, 100) );
		Accept.addMouseListener(this);
		Cancel.addMouseListener(this);
		add.addMouseListener(this);
		remove.addMouseListener(this);
		
		String dataValues[][] = new String[Menu.getMenuItems().size()][2];
        for(int i=0; i<Menu.getMenuItems().size(); i++){
        	if(Menu.getMenuItems().get(i).isDailySpecial()){
        		dataValues[i][0] = "Special: " + Menu.getMenuItems().get(i).getName();
        	}
        	else{
        		dataValues[i][0] = Menu.getMenuItems().get(i).getName();
        	}
        	dataValues[i][1] = String.valueOf(Menu.getMenuItems().get(i).getPrice());
        }        
        String columnNames[] = {"Menu Items","Price"};
        menu = new JTable(dataValues, columnNames);
        order = new JTable(new DefaultTableModel(new Object[]{"Order Items", "Price"}, 0));
        JScrollPane pane = new JScrollPane(menu);
        JScrollPane orderPane = new JScrollPane(order);

        this.add(pane);
        this.add(orderPane);
        this.add(add);
        this.add(remove);
		this.add(Accept);
		this.add(Cancel);
		this.add(total_lbl);
		this.add(total_txt);
		this.addMouseListener(this);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == Accept)
		{
			System.out.println("Items: "+selectedItems.size());
			if (selectedItems.size() == 0) {
				if (this.getContentPane().getComponentCount() < 9) {
					this.setLayout(new GridLayout(5,2));
					this.add(new JLabel("ERROR: You have selected no items."));
					this.revalidate();
				}
			}
			else {
				double pay = 0;
				int i = 0;
				while(total > pay){
					new PaymentMethodDialog(payments, (total - pay));
					pay = pay + payments.get(i).getAmount();
					i++;
				}
				SystemManager.createOrder(cust, selectedItems,payments);
				this.dispose();
			}
		}
		
		if (e.getSource() == remove)
		{
			int[] rows = order.getSelectedRows();			
    		DefaultTableModel model = (DefaultTableModel) order.getModel();
    		
    		for(int i=0; i<rows.length; i++){  			

	    		model.removeRow(rows[i]-i);
	    		total = total - Double.parseDouble(menu.getValueAt(rows[i], 1).toString());	
	    		selectedItems.remove(menu.getValueAt(rows[i], 0).toString().replace("Special: ", ""));
    		}
    		
    		total_txt.setText(String.valueOf(total));
    		menu.clearSelection();			
		}
		
		if (e.getSource() == add)
		{
			int[] rows = menu.getSelectedRows();			
    		DefaultTableModel model = (DefaultTableModel) order.getModel();
    		
    		for(int i=0; i<rows.length; i++){  			
	    		String[] newRow = new String[2];
	    		newRow[0] = menu.getValueAt(rows[i], 0).toString().replace("Special: ", "");
	    		newRow[1] = menu.getValueAt(rows[i], 1).toString();
	    		model.addRow(newRow);
	    		total = total + Double.parseDouble(menu.getValueAt(rows[i], 1).toString());	
	    		selectedItems.add(menu.getValueAt(rows[i], 0).toString().replace("Special: ", ""));
    		}
    		
    		total_txt.setText(String.valueOf(total));
    		menu.clearSelection();
		}
		
		if (e.getSource()== Cancel)
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
