package cs414.groupH.a4.order;

public class Order {

	private String orderId;
	private boolean isComplete;
	private boolean isDelivered;
	
	public String getId(){
		return orderId;
	}
	
	
	public Order(String orderId){
		this.orderId = orderId;
		isComplete = false;
		isDelivered = false;
	}
	
	public void markComplete(){
		isComplete = true;
	}
	
}
