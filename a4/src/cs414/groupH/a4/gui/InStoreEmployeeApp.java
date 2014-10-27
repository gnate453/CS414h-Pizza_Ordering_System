package cs414.groupH.a4.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class InStoreEmployeeApp extends JApplet implements MouseListener {

    //Gui Members
	JButton placeOrder_btn;
	JButton editOrder_btn;
	JButton addMenuItem_btn;
	JButton editMenuItem_btn;
	JButton addSpecial_btn;
	JButton editSpecial_btn;
	JButton login_btn;
	JButton logout_btn;

    //Application logic members

    //ArrayList<Order> orders
    //ArrayList<Customer> customers
    //ArrayList<Employee> employees
    Menu menu;

	public InStoreEmployeeApp() {
		super();

        //Initialize gui objects

        this.setSize(340,400);
		this.setLayout(new GridLayout(5, 2));
        this.addMouseListener(this);

        placeOrder_btn = new JButton("Place Order");
        this.add(placeOrder_btn);

        editOrder_btn = new JButton("Edit Order");
        this.add(editOrder_btn);

        addMenuItem_btn = new JButton("Add Menu Item");
        this.add(addMenuItem_btn);

        editMenuItem_btn = new JButton("Edit Menu Item");
        this.add(editMenuItem_btn);

        addSpecial_btn = new JButton("Add Daily Special");
        this.add(addSpecial_btn);

        editSpecial_btn = new JButton("Edit Daily Special");
        this.add(editSpecial_btn);

        login_btn = new JButton("Login Employee");
        this.add(login_btn);

        logout_btn = new JButton("Logout Employee");
        this.add(logout_btn);

        //initialize application logic objects

	}

    @Override
    public void mouseClicked(MouseEvent me) {

            if (me.getSource() == placeOrder_btn)
            {

            }
	        if (me.getSource() == editOrder_btn)
            {

            }
	        if (me.getSource() == addMenuItem_btn)
            {

            }
            if (me.getSource() == editMenuItem_btn)
            {

            }
	        if (me.getSource() == addSpecial_btn)
            {

            }
            if (me.getSource() == editSpecial_btn)
            {

            }
            if (me.getSource() == login_btn)
            {

            }
            if (me.getSource() == logout_btn)
            {

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

