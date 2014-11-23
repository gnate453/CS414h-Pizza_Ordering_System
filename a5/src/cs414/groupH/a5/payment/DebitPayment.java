package cs414.groupH.a5.payment;

public class DebitPayment extends Payment {
	protected String pin;
	protected String CardHolderName;
	protected String securityCode;
	protected String expiration;
	protected String cardNum;
	
	public DebitPayment(){
		//new DebitPaymentDialog(this);
	}
	public void setFields(String name, String cn, String sc, String exp, String p) {
		CardHolderName = name;
		securityCode = sc;
		expiration = exp;
		cardNum = cn;
		pin = p;
		//System.out.println(this);
	}
	public String toString(){
		return CardHolderName + " Card#:  " + cardNum + " SecurityCode: " + securityCode + " Exp: " +expiration+" Pin#: "+pin;
	}
}
