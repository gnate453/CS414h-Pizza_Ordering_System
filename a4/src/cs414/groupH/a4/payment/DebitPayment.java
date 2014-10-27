package cs414.groupH.a4.payment;

public class DebitPayment extends Payment {
	protected int pin;
	protected String CardHolderName;
	protected int securityCode;
	protected int expiration;
	protected int cardNum;
	public static void main(String args[]){
		DebitPayment p = new DebitPayment();
	}
	public DebitPayment(){
		new DebitPaymentDialog(this);
	}
	public void setFields(String name, int cn, int sc, int exp, int p) {
		CardHolderName = name;
		securityCode = sc;
		expiration = exp;
		cardNum = cn;
		pin = p;
		System.out.println(this);
	}
	public String toString(){
		return CardHolderName + " Card#:  " + cardNum + " SecurityCode: " + securityCode + " Exp: " +expiration+" Pin#: "+pin;
	}
}
