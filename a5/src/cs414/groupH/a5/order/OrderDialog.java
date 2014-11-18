package cs414.groupH.a5.order;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cs414.groupH.a5.customer.Customer;
import cs414.groupH.a5.manager.SystemManager;
import cs414.groupH.a5.menu.Menu;
import cs414.groupH.a5.payment.Payment;
import cs414.groupH.a5.payment.PaymentMethodDialog;

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
	DecimalFormat df = new DecimalFormat("#,##0.00");

	public OrderDialog(Customer c){
		cust = c;
		total = 0;
		this.setModal(true);
		this.setSize(new Dimension(800, 500));
		Accept = new JButton("Finish");
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
        	dataValues[i][1] = df.format(Menu.getMenuItems().get(i).getPrice()).replaceAll( "^-(?=0(.0*)?$)", "");
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
			if (selectedItems.size() == 0) {
				if (this.getContentPane().getComponentCount() < 9) {
					this.setLayout(new GridLayout(5,2));
					this.add(new JLabel("ERROR: You have selected no items."));
					this.revalidate();
				}
			}
			else {
				double pay = 0;
				boolean cancel = false;
				int i = 0;
				while(total > pay){
					new PaymentMethodDialog(payments, (total - pay));
					if(payments.size()>0){
						pay = pay + payments.get(i).getAmount();
						i++;
					}else{
						cancel = true;
						break;
					}
				}
				if(!cancel){
					SystemManager.createOrder(cust, selectedItems,payments);
					this.dispose();
				}
			}
		}
		
		if (e.getSource() == remove)
		{
			int[] rows = order.getSelectedRows();			
    		DefaultTableModel model = (DefaultTableModel) order.getModel();
    		
    		for(int i=0; i<rows.length; i++){ 
	    		total = total - Double.parseDouble(order.getValueAt(rows[i]-i, 1).toString());	
	    		selectedItems.remove(order.getValueAt(rows[i]-i, 0).toString().replace("Special: ", ""));
	    		model.removeRow(rows[i]-i);
    		}
    		
    		total_txt.setText(df.format(total).replaceAll( "^-(?=0(.0*)?$)", ""));
    		menu.clearSelection();			
		}
		
		if (e.getSource() == add)
		{
			int[] rows = menu.getSelectedRows();			
    		DefaultTableModel model = (DefaultTableModel) order.getModel();
    		
    		for(int i=0; i<rows.length; i++){  			
	    		String[] newRow = new String[2];
	    		newRow[0] = menu.getValueAt(rows[i], 0).toString().replace("Special: ", "");
	    		newRow[1] = df.format(Double.parseDouble(menu.getValueAt(rows[i], 1).toString())).replaceAll( "^-(?=0(.0*)?$)", "");
	    		model.addRow(newRow);
	    		total = total + Double.parseDouble(menu.getValueAt(rows[i], 1).toString());	
	    		selectedItems.add(menu.getValueAt(rows[i], 0).toString().replace("Special: ", ""));
    		}
    		
    		total_txt.setText(df.format(total).replaceAll( "^-(?=0(.0*)?$)", ""));
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
