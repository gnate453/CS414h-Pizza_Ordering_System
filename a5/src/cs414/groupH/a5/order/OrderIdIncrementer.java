package cs414.groupH.a5.order;

public class OrderIdIncrementer {
	private static int curOrderId = 0;
	
	public static String getNewOrderId() {
		curOrderId++;
		return Integer.toString(curOrderId);
	}
}
