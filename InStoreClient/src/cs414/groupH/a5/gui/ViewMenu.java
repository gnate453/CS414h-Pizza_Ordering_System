package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cs414.groupH.a5.http.InStoreHttpClient;


public class ViewMenu extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private static final int TABLE_COLS = 2;
	private static final int NAME = 0;
	private static final int PRICE = 1;
	private static final int SPECIAL = 2;
	
	JButton back_btn;
	JTable table;
	
	DecimalFormat df = new DecimalFormat("#,##0.00");
	
	public ViewMenu() {		
        
		back_btn = new JButton("Back");		
		
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(1,2));       
        
		String[] menuItems = InStoreHttpClient.getMenu().split("&");
		//this.setLayout(new GridLayout(menuItems.length, TABLE_COLS));       
        
		String dataValues[][] = new String[menuItems.length][TABLE_COLS];
        for(int i=0; i<menuItems.length; i++){
        	String[] item = menuItems[i].split(",");
        	if(item[SPECIAL].equalsIgnoreCase("True")){
        		dataValues[i][NAME] = "Special: " + item[NAME];
        	}
        	else{
        		dataValues[i][NAME] = item[NAME];
        	}
        	dataValues[i][PRICE] = item[PRICE];
        }        
        
        String columnNames[] = {"Item","Price"};
        table = new JTable(dataValues, columnNames);
        JScrollPane pane = new JScrollPane(table);
        back_btn.addMouseListener(this);
        this.add(back_btn);
        
        this.add(pane);
        
        this.addMouseListener(this);
		this.setVisible(true);
	}

    @Override
    public void mouseClicked(MouseEvent me) {
    	
    		if (me.getSource() == back_btn)
    		{
    			this.setVisible(false);
    		}
    		if (me.getSource() == back_btn)
    		{
    			this.setVisible(false);
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
