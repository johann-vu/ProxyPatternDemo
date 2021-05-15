
public class Sandwich implements ISandwich {
	
	private String name;
	private String id;
	private String price;
	private String healthIndex;
	private String alergyInfos;
	
	public Sandwich(String pID, String pName, String pPrice, String pHealthIndex, String pAlergyInfos) {
		this.name = pName;
		this.id = pID;
		this.price = pPrice;
		this.healthIndex = pHealthIndex;
		this.alergyInfos = pAlergyInfos;
	}

	public String ID() {
		return this.id;
	}

	public String Name() {
		return this.name;
	}

	public String Price() {
		return this.price;
	}

	public String HealthIndex() {
		return this.healthIndex;
	}

	public String AlergyInfos() {
		return this.alergyInfos;
	}
}
