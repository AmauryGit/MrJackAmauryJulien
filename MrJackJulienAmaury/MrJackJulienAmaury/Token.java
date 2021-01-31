package MrJackJulienAmaury;
import java.util.Random;

public class Token {
	public String name0;
	public String name1;
	public int face; // 0 = pile et 1 = face
	public Random rand = new Random();

	public Token(String name0, String name1, int face) {
		this.name0 = name0;
		this.name1 = name1;
		this.face = face;
	}

	public void changeFace() {
		if (this.face == 0)
			this.face = 1;
		else
			this.face = 0;
	}

	public void aleaToken() {
		this.face = rand.nextInt(2);
	}
}
