package cs414.groupH.a4.payment;

public class CashPayment extends Payment {
	
	public CashPayment(){
		new CashPaymentDialog(this);
		//System.out.println(this);
	}
	public void setFields(double a){
		amount = a;
		//System.out.println(this);
	}
	public String toString(){
		return "Cash: "+amount;
	}
}
