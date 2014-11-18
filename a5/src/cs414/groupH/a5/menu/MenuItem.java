package cs414.groupH.a5.menu;


public class MenuItem {
	private String name;
	private double price;
	private boolean isDailySpecial;
	
	public MenuItem(String name) {
		this.name = name;
		this.price = 0.0;
		this.isDailySpecial = false;
	}
	public MenuItem(String name, double price) {
		this.name = name;
		this.price = price;
		this.isDailySpecial = false;
	}
	public MenuItem(String name, double price, boolean isDailySpecial) {
		this.name = name;
		this.price = price;
		this.isDailySpecial = isDailySpecial;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean isDailySpecial() {
		return isDailySpecial;
	}
	public void setDailySpecial(boolean isDailySpecial) {
		this.isDailySpecial = isDailySpecial;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		MenuItem item = (MenuItem) obj;
		
		if (!this.name.equals(item.getName())) {
			return false;
		}
		else {
			return true;
		}
	}
	
}
