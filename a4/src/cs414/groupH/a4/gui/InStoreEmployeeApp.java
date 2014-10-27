package cs414.groupH.a4.gui;


import javax.swing.*;

import cs414.groupH.a4.employee.Employee;
import cs414.groupH.a4.employee.EmployeeType;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class InStoreEmployeeApp extends JApplet implements MouseListener {

	private static final long serialVersionUID = 482548685808198776L;
	
	//Gui Members
	JButton placeOrder_btn;
	JButton editOrder_btn;
	JButton addMenuItem_btn;
	JButton editMenuItem_btn;
	JButton addSpecial_btn;
	JButton editSpecial_btn;
	JButton login_btn;
	JButton logout_btn;
	
	JLabel loggedIn_lbl = new JLabel("Logged in as: ");
	JLabel emp_lbl = new JLabel("NOT LOGGED IN");

    //Application logic members

    //ArrayList<Order> orders
    //ArrayList<Customer> customers
    Employee empLoggedIn;
    Menu menu;

	public InStoreEmployeeApp() {
		super();

        //Initialize gui objects
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(2, 3));
        this.addMouseListener(this);
        
        login_btn = new JButton("Login Employee");
        login_btn.addMouseListener(this);
        
        logout_btn = new JButton("Logout Employee");

        placeOrder_btn = new JButton("Place Order");

        editOrder_btn = new JButton("Edit Order");

        addMenuItem_btn = new JButton("Add Menu Item");

        editMenuItem_btn = new JButton("Edit Menu Item");

        addSpecial_btn = new JButton("Add Daily Special");

        editSpecial_btn = new JButton("Edit Daily Special");
        
        renderView();

        //initialize application logic objects

	}
	
	public void renderView() {
		this.add(loggedIn_lbl);
        this.add(emp_lbl);
        if (empLoggedIn == null) {
        	this.remove(logout_btn);
    		this.add(login_btn,2);       	
        }
        else {
        	this.remove(login_btn);
    		this.add(logout_btn,2);
        }
        this.add(placeOrder_btn);
        this.add(editOrder_btn);
        if (empLoggedIn != null) {
	        if (empLoggedIn.getEmpType() == EmployeeType.manager) {
		        this.add(addMenuItem_btn);
		        this.add(editMenuItem_btn);
		        this.add(addSpecial_btn);
		        this.add(editSpecial_btn);
	        }
        }
	}
	
	public void loginEmployee(Employee e) {
		empLoggedIn = e;
	}
	public void logoutEmployee() {
		empLoggedIn = null;
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
            	new LoginDialog();
            	renderView();
            }
            if (me.getSource() == logout_btn)
            {
            	logoutEmployee();
            	renderView();
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

