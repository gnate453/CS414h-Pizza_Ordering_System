package cs414.groupH.a5.payment;

public class CreditPayment extends Payment{
	protected String CardHolderName;
	protected String securityCode;
	protected String expiration;
	protected String cardNum;
	
	public CreditPayment(){
		new CreditPaymentDialog(this);
	}
	public void setFields(String name, String cn, String sc, String exp) {
		CardHolderName = name;
		securityCode = sc;
		expiration = exp;
		cardNum = cn;
		//System.out.println(this);
	}
	public String toString(){
		return CardHolderName + " Card#: " + cardNum + "Security Code: " + securityCode + " Exp: " +expiration;
	}
}
