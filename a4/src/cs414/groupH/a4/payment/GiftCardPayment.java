package cs414.groupH.a4.payment;

public class GiftCardPayment extends Payment {
	protected int balance;
	protected int cardNumber;
	public static void main(String args[]){
		GiftCardPayment p = new GiftCardPayment();
	}
	public GiftCardPayment(){
		new GiftCardPaymentDialog(this);
	}
	public void setFields(int b, int cn){
		balance = b;
		cardNumber = cn;
		System.out.println(this);
	}
	public String toString(){
		return "Balance: "+balance+" Card#: "+cardNumber;
	}
}
