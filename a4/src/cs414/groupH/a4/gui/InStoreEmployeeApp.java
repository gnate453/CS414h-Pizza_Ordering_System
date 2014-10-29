package cs414.groupH.a4.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;

import cs414.groupH.a4.customer.Customer;
import cs414.groupH.a4.employee.Employee;
import cs414.groupH.a4.employee.EmployeeType;
import cs414.groupH.a4.manager.SystemManager;
import cs414.groupH.a4.menu.EditItemDialog;
import cs414.groupH.a4.menu.MenuItemDialog;
import cs414.groupH.a4.menu.viewMenu;
import cs414.groupH.a4.order.OrderDialog;
import cs414.groupH.a4.order.ViewOrders;

public class InStoreEmployeeApp extends JApplet implements MouseListener {

	private static final long serialVersionUID = 482548685808198776L;
	
	//Gui Members
	JButton placeOrder_btn;
	JButton addMenuItem_btn;
	JButton editMenuItem_btn;
	JButton addSpecial_btn;
	JButton editSpecial_btn;
	JButton login_btn;
	JButton logout_btn;
	JButton viewMenu_btn;
	JButton viewOrders_btn;
	
	JLabel loggedIn_lbl = new JLabel("Logged in as: ");
	JLabel emp_lbl = new JLabel("NOT LOGGED IN");

    //Application logic members
	static Employee empLoggedIn;

	public InStoreEmployeeApp() {
		super();

        //Initialize gui objects
        this.setSize(new Dimension(800, 500));
		this.setLayout(new GridLayout(2, 3));
        this.addMouseListener(this);

        login_btn = new JButton("Login Employee");
        logout_btn = new JButton("Logout Employee");
        placeOrder_btn = new JButton("Place Order");
        addMenuItem_btn = new JButton("Add Menu Item");
        editMenuItem_btn = new JButton("Edit Menu Item");
        addSpecial_btn = new JButton("Add Daily Special");
        editSpecial_btn = new JButton("Edit Daily Special");
        viewMenu_btn = new JButton("View Menu");
        viewOrders_btn = new JButton("View Orders");
        
        login_btn.addMouseListener(this);
        logout_btn.addMouseListener(this);
        placeOrder_btn.addMouseListener(this);
        addMenuItem_btn.addMouseListener(this);
        editMenuItem_btn.addMouseListener(this);
        addSpecial_btn.addMouseListener(this);
        editSpecial_btn.addMouseListener(this);
        viewMenu_btn.addMouseListener(this);
        viewOrders_btn.addMouseListener(this);
        
        renderView();

        initialData();
	}
	
	public void initialData() {
		//Login with no credentials
		SystemManager.addEmployee("", "John Smith", "", EmployeeType.manager);
		
		SystemManager.addEmployee("001", "John Smith", "password", EmployeeType.cashier);
		SystemManager.addEmployee("002", "Joh Smith", "password", EmployeeType.chef);
		SystemManager.addEmployee("003", "Jo Smith", "password", EmployeeType.manager);
		SystemManager.addEmployee("004", "J Smith", "password", EmployeeType.cashier);
	}
	
	public void renderView() {
		System.out.println("Rendering view...");
		this.add(loggedIn_lbl,0);
        this.add(emp_lbl,1);
        if (empLoggedIn == null) {
        	this.remove(logout_btn);
    		this.add(login_btn,2);       	
        }
        else {
        	this.remove(login_btn);
    		this.add(logout_btn,2);
        }
        this.add(viewMenu_btn,3);
        this.add(placeOrder_btn,4);

        if (empLoggedIn != null) {
	        if (empLoggedIn.getEmpType() == EmployeeType.manager) {
	        	this.setLayout(new GridLayout(4,3));
		        this.add(addMenuItem_btn);
		        this.add(editMenuItem_btn);
		        this.add(addSpecial_btn);
		        this.add(editSpecial_btn);
		        this.add(viewOrders_btn);
	        }
	        if (empLoggedIn.getEmpType() == EmployeeType.chef) {
	        	this.setLayout(new GridLayout(3,3));
	        	this.add(viewOrders_btn);
	        }
        }
        else {
        	this.setLayout(new GridLayout(2,3));
        	this.remove(addMenuItem_btn);
	        this.remove(editMenuItem_btn);
	        this.remove(addSpecial_btn);
	        this.remove(editSpecial_btn);
	        this.remove(viewOrders_btn);
        }
        this.repaint();
	}
	
	public static void loginEmployee(Employee e) {
		empLoggedIn = e;
		System.out.println("Logged in employee with ID '"+empLoggedIn.getEmployeeId()+"'");
	}
	public void logoutEmployee() {
		System.out.println("Logged out employee '"+empLoggedIn.getEmployeeId()+"'");
		empLoggedIn = null;
	}
	
    @Override
    public void mouseClicked(MouseEvent me) {
		if (me.getSource() == viewMenu_btn) {
			new viewMenu();
		}
		else if (me.getSource() == placeOrder_btn) {
			if (empLoggedIn != null) {
				//employee is creating order. Need customer info and address
				Customer c = new Customer();
				new OrderDialog(c);
			}
			else{
				//customer is creating order. 
				new OrderDialog(null);
			}
			
		}
		else if (me.getSource() == addMenuItem_btn) {
			new MenuItemDialog(false);
		}
		else if (me.getSource() == editMenuItem_btn) {
			new EditItemDialog();
		}
		else if (me.getSource() == addSpecial_btn) {
			new MenuItemDialog(true);
		}
		else if (me.getSource() == editSpecial_btn) {
		
		}
		else if (me.getSource() == viewOrders_btn) {
			new ViewOrders();
		}
		else if (me.getSource() == login_btn) {
			new LoginDialog();
			if (empLoggedIn != null) {
				emp_lbl.setText(empLoggedIn.getName());
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
