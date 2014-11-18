package cs414.groupH.a5.menu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditItemDialog extends JDialog implements MouseListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;

	JButton back_btn;
	JTable table;
	
	public EditItemDialog() {		
        
		back_btn = new JButton("Back");		
		
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(Menu.getMenuItems().size(), 2));       
        
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
        
        table.getSelectionModel().addListSelectionListener(this);
        
        
        back_btn.addMouseListener(this);
        this.add(back_btn);
        
        this.add(pane);
        
        this.addMouseListener(this);
		this.setVisible(true);
	}

    @Override
    public void mouseClicked(MouseEvent me) {
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
		if (!e.getValueIsAdjusting()) {
            String itemName = table.getValueAt(table.getSelectedRow(), 0).toString();
            double price = Double.parseDouble(table.getValueAt(table.getSelectedRow(), 1).toString());
            this.dispose();
            new EditItemPage(itemName.replace("Special: ", ""), price, itemName.contains("Special: "));
    	}
	}
}
