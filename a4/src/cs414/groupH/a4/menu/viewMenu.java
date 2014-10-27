package cs414.groupH.a4.menu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;


public class viewMenu extends JDialog implements MouseListener {

	private static final long serialVersionUID = 1L;

	JButton back_btn;
	
	public viewMenu(Menu menu) {		
        
		back_btn = new JButton("Back");
		
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(menu.getMenuItems().size(), 2));
        this.addMouseListener(this);        
        
        for(int i=0; i<menu.getMenuItems().size(); i++){
        	menu.getMenuItems().get(i);
        }        
        
        back_btn.addMouseListener(this);
        this.add(back_btn);
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

