package MrJackJulienAmaury;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class General {
	public ArrayList<Card> cards;
	public Les3Detective les3Detective;
	public MrJack mrJack;
	public District[][] board = new District[3][3];
	public Random rand = new Random();
	public int flag;
	public int turn;

	public General() {
		this.turn = 0;
		this.flag = -1;

		cards = new ArrayList<Card>();
		cards.add(new Card("0", 2));
		cards.add(new Card("1", 1));
		cards.add(new Card("2", 1));
		cards.add(new Card("3", 1));
		cards.add(new Card("4", 1));
		cards.add(new Card("5", 1));
		cards.add(new Card("6", 1));
		cards.add(new Card("7", 0));
		cards.add(new Card("8", 0));
		Collections.shuffle(cards);

		les3Detective = new Les3Detective();

		board[0][0] = new District(4, cards.get(0).name, false);
		board[0][1] = new District(rand.nextInt(4) + 1, cards.get(1).name, false);
		board[0][2] = new District(2, cards.get(2).name, false);
		board[1][0] = new District(rand.nextInt(4) + 1, cards.get(3).name, false);
		board[1][1] = new District(rand.nextInt(4) + 1, cards.get(4).name, false);
		board[1][2] = new District(rand.nextInt(4) + 1, cards.get(5).name, false);
		board[2][0] = new District(rand.nextInt(4) + 1, cards.get(6).name, false);
		board[2][1] = new District(3, cards.get(7).name, false);
		board[2][2] = new District(rand.nextInt(4) + 1, cards.get(8).name, false);
		Collections.shuffle(cards);

		mrJack = new MrJack(cards.get(0), 0);
		System.out.println("MrJack est : " + cards.get(0).name);
		cards.remove(0);
	}

	public void changeTour() {
		this.turn = (this.turn == 0) ? 1 : 0;
	}

	public void display() {
		System.out.println("|----------------|----------------||----------------||----------------|----------------|");
		System.out.print("||||||||||||||||||");
		displayDetective(1);
		System.out.print("||");
		displayDetective(2);
		System.out.print("||");
		displayDetective(3);
		System.out.println("||||||||||||||||||");
		for (int i = 0 ; i < 3 ; i++) {
			System.out.print("|----------------|");
			if (board[i][0].orientationForWall != 1) {
				System.out.print("                ||");
			} else {
				System.out.print("----------------||");
			}

			if (board[i][1].orientationForWall != 1) {
                                System.out.print("                ||");
                        } else {
                                System.out.print("----------------||");
                        }

			if (board[i][2].orientationForWall != 1) {
                                System.out.print("                |");
                        } else {
                                System.out.print("----------------|");
                        }
			System.out.println("----------------|");
			System.out.print("|");
			displayDetective(12 - i);
			if (board[i][0].orientationForWall != 4) {
                                System.out.print(" ");
                        } else {
                                System.out.print("|");
                        }
			if (board[i][0].returnDistrict == false) {
				System.out.print("        " + board[i][0].name  + "       ");
			} else
				System.out.print("                ");
			if (board[i][0].orientationForWall != 2) {
                                System.out.print(" ");
                        } else {
                                System.out.print("|");
                        }
			if (board[i][1].orientationForWall != 4) {
                                System.out.print(" ");
                        } else {
                                System.out.print("|");
                        }
			if (board[i][1].returnDistrict == false) {
                                System.out.print("        " + board[i][1].name  + "       ");
                        } else
                                System.out.print("                ");
			if (board[i][1].orientationForWall != 2) {
                                System.out.print(" ");
                        } else {
                                System.out.print("|");
                        }
                        if (board[i][2].orientationForWall != 4) {
                                System.out.print(" ");
                        } else {
                                System.out.print("|");
                        }
			if (board[i][2].returnDistrict == false) {
                                System.out.print("        " + board[i][2].name  + "       ");
                        } else
                                System.out.print("                ");
			if (board[i][2].orientationForWall != 2) {
                                System.out.print(" ");
                        } else {
                                System.out.print("|");
                        }
			displayDetective(4 + i);
			System.out.println("|");

			System.out.print("|----------------|");
                        if (board[i][0].orientationForWall != 3) {
                                System.out.print("                ||");
                        } else {
                                System.out.print("----------------||");
                        }

                        if (board[i][1].orientationForWall != 3) {
                                System.out.print("                ||");
                        } else {
                                System.out.print("----------------||");
                        }

                        if (board[i][2].orientationForWall != 3) {
                                System.out.print("                |");
                        } else {
                                System.out.print("----------------|");
                        }
                        System.out.println("----------------|");
		}
		System.out.print("||||||||||||||||||");
                displayDetective(9);
                System.out.print("||");
                displayDetective(8);
                System.out.print("||");
                displayDetective(7);
                System.out.println("||||||||||||||||||");
		System.out.println("|----------------|----------------||----------------||----------------|----------------|\n\n");
	}

	public void displayDetective(int i) {
		if (les3Detective.position[0] == i && les3Detective.position[1] == i && les3Detective.position[2] == i) { // les 3
			System.out.print("Holmes-Wats-Toby");
		} else if (les3Detective.position[0] == i && les3Detective.position[1] == i) { // Holmes et Watson
			System.out.print("  Holmes-Watson ");
		} else if (les3Detective.position[1] == i && les3Detective.position[2] == i) { // Watson et Toby
			System.out.print("   Watson-Toby  ");
		} else if (les3Detective.position[0] == i && les3Detective.position[2] == i) { // Holmes et Toby
			System.out.print("   Holmes-Toby  ");
		} else if (les3Detective.position[0] == i) { // Holmes
			System.out.print("     Holmes     ");
		} else if (les3Detective.position[1] == i) { // Watson
			System.out.print("     Watson     ");
		} else if (les3Detective.position[2] == i) { // Toby
			System.out.print("      Toby      ");
		} else {
			System.out.print("                ");
		}
	}
}
