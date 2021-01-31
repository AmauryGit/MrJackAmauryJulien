package MrJackJulienAmaury;

public class District {
	public String name;
	public int orientationForWall; // 1 = Nord, 2 = Est, 3 = Sud et 4 = Ouest
	public boolean returnDistrict; // false = non, true = oui
	
	public District(int orientationForWall, String name, boolean returnDistrict) {
		this.name = name;
		this.orientationForWall = orientationForWall;
		this.returnDistrict = returnDistrict;
	}
}
