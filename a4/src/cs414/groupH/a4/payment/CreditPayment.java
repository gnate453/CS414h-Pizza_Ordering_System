package cs414.groupH.a4.payment;
import java.util.Date;

public class CreditPayment extends Payment{
	protected String CardHolderName;
	protected int securityCode;
	protected int expiration;
	protected long cardNum;
	public static void main(String args[]){
		CreditPayment p = new CreditPayment();
	}
	public CreditPayment(){
		new CreditPaymentDialog(this);
	}
	public void setFields(String name, long cn, int sc, int exp) {
		CardHolderName = name;
		securityCode = sc;
		expiration = exp;
		cardNum = cn;
		System.out.println(this);
	}
	public String toString(){
		return CardHolderName + " Card#: " + cardNum + "Security Code: " + securityCode + " Exp: " +expiration;
	}
}
