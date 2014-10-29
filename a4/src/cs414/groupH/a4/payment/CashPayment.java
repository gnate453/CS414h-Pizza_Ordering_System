package cs414.groupH.a4.payment;

public class CashPayment extends Payment {
	
	public static void main(String args[]){
		CashPayment p = new CashPayment();
	}
	public CashPayment(){
		new CashPaymentDialog(this);
		System.out.println(this);
	}
	public void setFields(double a){
		amount = a;
		//System.out.println(this);
	}
	public String toString(){
		return "Cash: "+amount;
	}
}
