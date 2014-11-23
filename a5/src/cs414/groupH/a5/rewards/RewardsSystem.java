package cs414.groupH.a5.rewards;

import java.util.HashMap;
import java.util.Map;

public class RewardsSystem {
	private static Map<String, RewardsRecord> data = new HashMap<String, RewardsRecord>();
	private static int pointsThreshold;
	
	public static void setThreshold(int t) {
		pointsThreshold = t;
		for (String id : data.keySet()) {
			getCustomerRecord(id).checkCertificate(pointsThreshold);
		}
	}
	
	public static boolean isMember(String id) {
		return data.containsKey(id);
	}
	
	public static boolean newMember(String id) {
		if (!isMember(id)) {
			data.put(id, new RewardsRecord());
			return true;
		}
		else
			return false;
	}
	
	public static boolean addPoints(String id, int p) {
		if (isMember(id)) {
			getCustomerRecord(id).addPoints(p);
			getCustomerRecord(id).checkCertificate(pointsThreshold);
			return true;
		}
		else
			return false;
	}
	
	public static boolean isEligible(String id) {
		if (isMember(id)) {
			RewardsRecord rr = getCustomerRecord(id);
			return (rr.getCertificates() > 0);
		}
		else
			return false;
	}
	
	public static boolean redeemCertificate(String id) {
		if (isMember(id)) {
			return getCustomerRecord(id).redeemCertificate();
		}
		else
			return false;
	}
	
	private static RewardsRecord getCustomerRecord(String id) {
		if (isMember(id)) {
			return data.get(id);
		}
		else 
			return null;
	}
}
