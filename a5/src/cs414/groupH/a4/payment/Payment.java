package cs414.groupH.a4.payment;

public class Payment {
	
	protected double amount;
	protected boolean verified;
	
	public Payment(){
		//Removed because this is a super class
		new PaymentDialog(this);
	}
	public Payment(double a){
		amount = a;
	}
	public Payment(double a, boolean v){
		amount = a;
		verified = v;
	}
	public String toString(){
		return "Amount: "+amount;
	}
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
