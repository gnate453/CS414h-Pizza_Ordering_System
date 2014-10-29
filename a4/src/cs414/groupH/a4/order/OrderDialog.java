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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import cs414.groupH.a4.address.Address;
import cs414.groupH.a4.customer.Customer;
import cs414.groupH.a4.manager.SystemManager;
import cs414.groupH.a4.menu.Menu;

public class OrderDialog extends JDialog implements MouseListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton Accept;
	JButton Cancel;
	JTable menu;
	JTable order;
	ArrayList<String> selectedItems = new ArrayList<String>();
	Customer cust;

	public OrderDialog(Customer c){

		cust = c;
		this.setModal(true);
		this.setSize(new Dimension(500, 255));
		Accept = new JButton("Accept");
		Cancel = new JButton("Cancel");

		this.setLayout(new GridLayout(2,2));
		this.setPreferredSize(new Dimension(50, 100) );
		Accept.addMouseListener(this);
		Cancel.addMouseListener(this);
		
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
        String columnNames[] = {"Item","Price"};
        menu = new JTable(dataValues, columnNames);
        order = new JTable(new DefaultTableModel(new Object[]{"Item", "Price"}, 1));
        JScrollPane pane = new JScrollPane(menu);
        JScrollPane orderPane = new JScrollPane(order);
        
        //SelectionListener for the Menu (for adding items to order)
        menu.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
            	if(!event.getValueIsAdjusting()){
            		selectedItems.add(menu.getValueAt(menu.getSelectedRow(), 0).toString().replace("Special: ", ""));
            		DefaultTableModel model = (DefaultTableModel) order.getModel();
            		String[] newRow = new String[2];
            		newRow[0] = menu.getValueAt(menu.getSelectedRow(), 0).toString().replace("Special: ", "");
            		newRow[1] = menu.getValueAt(menu.getSelectedRow(), 1).toString();
            		model.addRow(newRow);
            	}
            }
        });
        
        //SelectionListener for the menu(for removing items from order)
        order.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
            	if(!event.getValueIsAdjusting()){
            		selectedItems.remove(menu.getValueAt(menu.getSelectedRow(), 0).toString().replace("Special: ", ""));
            		DefaultTableModel model = (DefaultTableModel) order.getModel();
            		model.removeRow(menu.getSelectedRow());
            	}
            }
        });

        this.add(pane);
        this.add(orderPane);
		this.add(Accept);
		this.add(Cancel);
		this.addMouseListener(this);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == Accept)
		{
			//SystemManager.createOrder(c, selectedItems);
			this.setVisible(false);
		}
		else if (e.getSource()== Cancel)
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
