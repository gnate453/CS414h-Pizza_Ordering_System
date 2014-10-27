package cs414.groupH.a4.menu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class viewMenu extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	JButton back_btn;
	JTable table;
	
	public viewMenu(Menu menu) {		
        
		back_btn = new JButton("Back");
		
		
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(menu.getMenuItems().size(), 2));       
        
		String dataValues[][] = new String[menu.getMenuItems().size()][2];
        for(int i=0; i<menu.getMenuItems().size(); i++){
        	dataValues[i][0] = menu.getMenuItems().get(i).getName();
        	dataValues[i][1] = String.valueOf(menu.getMenuItems().get(i).getPrice());
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

