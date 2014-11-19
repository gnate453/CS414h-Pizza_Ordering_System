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


public class ViewMenu extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	JButton back_btn;
	JTable table;
	
	DecimalFormat df = new DecimalFormat("#,##0.00");
	
	public ViewMenu() {		
        
		back_btn = new JButton("Back");		
		
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(1,2));       
        
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

