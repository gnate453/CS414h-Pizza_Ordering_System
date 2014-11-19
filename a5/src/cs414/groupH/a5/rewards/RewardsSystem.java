package cs414.groupH.a5.rewards;

import java.util.Map;

public class RewardsSystem {
	private Map<String, RewardsRecord> data;
	private int pointsThreshold;
	
	public void setThreshold(int t) {
		pointsThreshold = t;
		for (String id : data.keySet()) {
			getCustomerRecord(id).checkCertificate(pointsThreshold);
		}
	}
	
	public boolean isMember(String id) {
		return data.containsKey(id);
	}
	
	public boolean newMember(String id) {
		if (!isMember(id)) {
			data.put(id, new RewardsRecord());
			return true;
		}
		else
			return false;
	}
	
	public boolean addPoints(String id, int p) {
		if (isMember(id)) {
			getCustomerRecord(id).addPoints(p);
			getCustomerRecord(id).checkCertificate(pointsThreshold);
			return true;
		}
		else
			return false;
	}
	
	public boolean redeemCertificate(String id) {
		if (isMember(id)) {
			return getCustomerRecord(id).redeemCertificate();
		}
		else
			return false;
	}
	
	private RewardsRecord getCustomerRecord(String id) {
		if (isMember(id)) {
			return data.get(id);
		}
		else 
			return null;
	}
	
	
	
}
