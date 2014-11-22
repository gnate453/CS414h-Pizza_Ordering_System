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

	JButton back_btn;
	JTable table;
	
	DecimalFormat df = new DecimalFormat("#,##0.00");
	
	public ViewMenu() {		
        
		back_btn = new JButton("Back");		
		
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(1,2));       
        
		String[] items = InStoreHttpClient.getMenu().split(",");
		String dataValues[][] = new String[(items.length/3)][2];
		int j = 0;
        for(int i=0; i<items.length; i++){
        	if(items[i+2].equalsIgnoreCase("true")){
        		dataValues[j][0] = "Special: " + items[i];
        	}
        	else{
        		dataValues[j][0] = items[i];
        	}
        	dataValues[j][1] = df.format(Double.parseDouble(items[i+1])).replaceAll( "^-(?=0(.0*)?$)", "");
        	i++;
        	i++;
        	j++;
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
