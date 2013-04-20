
public class WoWZone {

	/**
	 * @param args
	 */
	private String name;
	private float xLoc, yLoc;
	
	public WoWZone(String zoneName, float xLoc, float yLoc) {
		this.name = zoneName;
		this.xLoc = xLoc ;
		this.yLoc = yLoc ;
	}
	
	public String getZoneName() {
		return this.name;
	}
	
	public float getXLoc() {
		return this.xLoc ;
	}

	public float getYLoc() {
		return this.yLoc ;
	}
	
	
	
}
