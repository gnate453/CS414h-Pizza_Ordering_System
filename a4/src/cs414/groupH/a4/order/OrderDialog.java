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

import cs414.groupH.a4.address.Address;
import cs414.groupH.a4.customer.Customer;
import cs414.groupH.a4.menu.Menu;

public class OrderDialog extends JDialog implements MouseListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton Accept;
	JButton Cancel;
	JTable table;
	 ArrayList<String> selectedItems = new ArrayList<String>();

	public OrderDialog(){

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
        table = new JTable(dataValues, columnNames);
        JScrollPane pane = new JScrollPane(table);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });
        
        this.add(pane);
		this.add(Accept);
		this.add(Cancel);
		this.addMouseListener(this);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == Accept)
		{
			
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
