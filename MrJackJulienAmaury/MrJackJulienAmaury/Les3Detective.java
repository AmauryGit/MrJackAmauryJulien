package MrJackJulienAmaury;

public class Les3Detective {
	public String[] name = new String[3]; // nom
	public int[] position = new int[3]; // comprise entre 1 compris et 12 compris

	public Les3Detective() {
		this.name[0] = "Holmes";
		this.name[1] = "Watson";
		this.name[2] = "Tobi";
		this.position[0] = 12;
		this.position[1] = 4;
		this.position[2] = 8;
	}

	public void oneMorePosition(int index) {
		if (this.position[index] == 12)
			this.position[index] = 1;
		else
			this.position[index]++;
	}
}
