package cs414.groupH.a4.payment;

public class Payment {
	double amount;
	boolean verified;
	public Payment(){
		verified = false;
		amount = 0;
	}
	public Payment(double a){
		amount = a;
	}
	public Payment(double a, boolean v){
		amount = a;
		verified = v;
	}
}
