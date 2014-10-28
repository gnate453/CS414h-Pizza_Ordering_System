package cs414.groupH.a4.order;

public class OrderIdIncrementer {
	private static int curOrderId = 0;
	
	public static String getNewOrderId() {
		curOrderId++;
		return Integer.toString(curOrderId);
	}
}
