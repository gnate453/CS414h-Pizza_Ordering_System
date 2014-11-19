package cs414.groupH.a5.rewards;

public class RewardsRecord {
	private int points;
	private int certificates;
	
	public RewardsRecord() {
		points = 0;
		certificates = 0;
	}

	public void addPoints(int p) {
		points += p;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getCertificates() {
		return certificates;
	}
	
	public boolean checkCertificate(int threshold) {
		if (points >= threshold) {
			points -= threshold;
			certificates++;
			return true;
		}
		else
			return false;
	}
	
	public boolean redeemCertificate() {
		if (certificates > 0) {
			certificates--;
			return true;
		}
		else
			return false;
	}
	
}
