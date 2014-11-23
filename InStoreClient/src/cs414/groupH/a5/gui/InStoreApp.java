package cs414.groupH.a5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;

public class InStoreApp extends JApplet implements MouseListener {

	private static final long serialVersionUID = 482548685808198776L;
	
	//Gui Members
	JButton placeOrder_btn;
	JButton addMenuItem_btn;
	JButton editMenuItem_btn;
	JButton login_btn;
	JButton logout_btn;
	JButton viewMenu_btn;
	JButton viewOrders_btn;
	
	JLabel loggedIn_lbl = new JLabel("Logged in as: ");
	JLabel emp_lbl = new JLabel("NOT LOGGED IN");

    //Application logic members
	static String empLoggedInID;
	static String empLoggedInName;
	static String empLoggedInType;

	public InStoreApp() {
		super();

        //Initialize gui objects
		this.setLayout(new GridLayout(2, 3));
        this.addMouseListener(this);

        login_btn = new JButton("Login Employee");
        logout_btn = new JButton("Logout Employee");
        placeOrder_btn = new JButton("Place Order");
        addMenuItem_btn = new JButton("Add Menu Item");
        editMenuItem_btn = new JButton("Edit Menu Item");
        viewMenu_btn = new JButton("View Menu");
        viewOrders_btn = new JButton("View Orders");
        
        login_btn.addMouseListener(this);
        logout_btn.addMouseListener(this);
        placeOrder_btn.addMouseListener(this);
        addMenuItem_btn.addMouseListener(this);
        editMenuItem_btn.addMouseListener(this);
        viewMenu_btn.addMouseListener(this);
        viewOrders_btn.addMouseListener(this);
        
        renderView();
	}
	
	public void renderView() {
		//System.out.println("Rendering view...");
		this.add(loggedIn_lbl,0);
        this.add(emp_lbl,1);
        if (empLoggedInID == null) {
        	this.remove(logout_btn);
    		this.add(login_btn,2);       	
        }
        else {
        	this.remove(login_btn);
    		this.add(logout_btn,2);
        }
        this.add(viewMenu_btn,3);
        this.add(placeOrder_btn,4);

        if (empLoggedInID != null) {
        	this.add(viewOrders_btn);
	        if (empLoggedInType.equalsIgnoreCase("manager")) {
	        	this.setLayout(new GridLayout(3,3));
		        this.add(addMenuItem_btn);
		        this.add(editMenuItem_btn);
	        }
        }
        else {
        	this.setLayout(new GridLayout(2,3));
        	this.remove(addMenuItem_btn);
	        this.remove(editMenuItem_btn);
	        this.remove(viewOrders_btn);
        }
        this.repaint();
	}
	
	public static void loginEmployee(String id, String name, String type) {
		empLoggedInID = id;
		empLoggedInName = name;
		empLoggedInType = type;
		//System.out.println("Logged in employee with ID '"+empLoggedIn.getEmployeeId()+"'");
	}
	public void logoutEmployee() {
		//System.out.println("Logged out employee '"+empLoggedIn.getEmployeeId()+"'");
		empLoggedInID = null;
		empLoggedInName = "";
		empLoggedInType = "";
	}
	
    @Override
    public void mouseClicked(MouseEvent me) {
		if (me.getSource() == viewMenu_btn) {
			new ViewMenu();
		}
		else if (me.getSource() == placeOrder_btn) {
			if (empLoggedInID != null) {
				//employee is creating order. Need customer info and address
				new CustomerDialog();
				new OrderDialog();
			}
			else{
				//customer is creating order. 
				new OrderDialog();
			}
			
		}
		else if (me.getSource() == addMenuItem_btn) {
			new MenuItemDialog();
		}
		else if (me.getSource() == editMenuItem_btn) {
			new EditItemDialog();
		}
		else if (me.getSource() == viewOrders_btn) {
			new ViewOrders();
		}
		else if (me.getSource() == login_btn) {
			new LoginDialog();
			if (empLoggedInID != null) {
				emp_lbl.setText(empLoggedInName);
			}
			renderView();
		}
		else if (me.getSource() == logout_btn) {
			logoutEmployee();
			emp_lbl.setText("NOT LOGGED IN");
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
