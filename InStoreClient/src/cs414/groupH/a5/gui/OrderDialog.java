package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import cs414.groupH.a5.http.InStoreHttpClient;
import cs414.groupH.a5.http.RequestHandler;
import cs414.groupH.a5.http.XmlHelper;
import cs414.groupH.a5.gui.PaymentMethodDialog;

public class OrderDialog extends JDialog implements MouseListener  {

	private static final long serialVersionUID = 1L;

	private static final int TABLE_COLS = 2;
	
	private static final int ITEM_NAME = 0;
	private static final int ITEM_PRICE = 1;
	private static final int ITEM_SPECIAL = 2;
	
	JTable menu;
	JTable order;
	JButton add;
	JButton remove;
	JButton Accept;
	JButton Cancel;
	JLabel total_lbl;
	JTextField total_txt;
	
	ArrayList<String> payments = new ArrayList<String>();
	ArrayList<String> selectedItems = new ArrayList<String>();
	double total;
	double paid;
	DecimalFormat df = new DecimalFormat("#,##0.00");

	private int itemCount;

	public OrderDialog() {
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
		
		
		
		String[] menuItems = InStoreHttpClient.getMenu().split("&");
		//this.setLayout(new GridLayout(menuItems.length, TABLE_COLS));       
        
		String dataValues[][] = new String[menuItems.length][TABLE_COLS];
        for(int i=0; i<menuItems.length; i++){
        	String[] item = menuItems[i].split(",");
        	if(item[ITEM_SPECIAL].equalsIgnoreCase("True")){
        		dataValues[i][ITEM_NAME] = "Special: " + item[ITEM_NAME];
        	}
        	else{
        		dataValues[i][ITEM_NAME] = item[ITEM_NAME];
        	}
        	dataValues[i][ITEM_PRICE] = item[ITEM_PRICE];
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

	public void addPayment(double p) {
		paid += p;
	}
	
	public double getAmountDue() {
		return total-paid;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == Accept)
		{
			if (itemCount == 0) {
				if (this.getContentPane().getComponentCount() < 9) {
					this.setLayout(new GridLayout(5,2));
					this.add(new JLabel("ERROR: You have selected no items."));
					this.revalidate();
				}
			}
			else {
				boolean cancel = false;
				int i = 0;
				while(getAmountDue() > 0){
					new PaymentMethodDialog(this);
				}
				if(!cancel){
					if (InStoreHttpClient.createOrder(RequestHandler.getFinalXml()))
					{
						this.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Order Error server return invalid",
								"error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		
		if (e.getSource() == remove)
		{
			int[] rows = order.getSelectedRows();			
    		DefaultTableModel model = (DefaultTableModel) order.getModel();
    		
    		for(int i=0; i<rows.length; i++){ 
	    		total = total - Double.parseDouble(order.getValueAt(rows[i]-i, 1).toString());
	    		RequestHandler.removeItem(rows[i]-i);
	    		model.removeRow(rows[i]-i);
	    		itemCount--;
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
	    		RequestHandler.addItemXml(XmlHelper.getItemXml(menu.getValueAt(rows[i], 0).toString().replace("Special: ", ""),
	    					menu.getValueAt(rows[i], 1).toString(), "false"));
				itemCount++;
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
