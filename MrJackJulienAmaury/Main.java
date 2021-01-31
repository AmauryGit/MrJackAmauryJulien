import MrJackJulienAmaury.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main
{
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		General general = new General();
		int nb;

		ArrayList<Token> jeton = new ArrayList<Token>();
		jeton.add(new Token("Holmes", "Alibi", -1));
		jeton.add(new Token("Tourner", "Changer", -1));
		jeton.add(new Token("Tourner", "Joker", -1));
		jeton.add(new Token("Watson", "Tobi", -1));

		String[] joueur = new String[2];
		joueur[0] = "MrJack";
		joueur[1] = "M. Le Detective";

		for (int nbTour = 1 ; nbTour <= 8 ; nbTour++) {
			general.display();
			general.flag = -1;
			if (nbTour % 2 == 1) // impair
				for (int i = 0 ; i < 4 ; i++) jeton.get(i).aleaToken();
			else // pair
				for (int i = 0 ; i < 4 ; i++) jeton.get(i).changeFace();
			ArrayList<Token> jetonFlag = (ArrayList<Token>)jeton.clone();

			System.out.println("C'est au tour de " + joueur[nbTour % 2]
					+ ", voici les jetons actions :");
			for (int i = 0 ; i < 4 ; i++) {
				if (jetonFlag.get(i).face == 0)
					System.out.println(i + " : " + jetonFlag.get(i).name0);
				else
					System.out.println(i + " : " + jetonFlag.get(i).name1);
			}
			nb = -1;
			System.out.println("Tapez de 0 a 3 pour choisir le jeton action a piocher");
			while (nb != 0 && nb != 1 && nb != 2 && nb != 3) {
				nb = scanner.nextInt();
				if (nb != 0 && nb != 1 && nb != 2 && nb != 3)
					System.out.println("Veuillez retaper :");
			}
			whichJeton(jetonFlag.get(nb), general);
			general.display();
			jetonFlag.remove(jetonFlag.get(nb));

			general.changeTour();
			System.out.println("C'est au tour de " + joueur[(nbTour + 1) % 2]
					+ ", voici les jetons actions :");
			for (int i = 0 ; i < 3 ; i++) {
				if (jetonFlag.get(i).face == 0)
					System.out.println(i + " : " + jetonFlag.get(i).name0);
				else
					System.out.println(i + " : " + jetonFlag.get(i).name1);
			}
			nb = -1;
			System.out.println("Tapez de 0 a 2 pour choisir le jeton action a piocher");
			while (nb != 0 && nb != 1 && nb != 2) {
				nb = scanner.nextInt();
				if (nb != 0 && nb != 1 && nb != 2)
					System.out.println("Veuillez retaper :");
			}
			whichJeton(jetonFlag.get(nb), general);
			general.display();
			jetonFlag.remove(jetonFlag.get(nb));

			System.out.println("C'est au tour de " + joueur[(nbTour + 1) % 2]
                                        + ", voici les jetons actions :");
                        for (int i = 0 ; i < 2 ; i++) {
                                if (jetonFlag.get(i).face == 0)
                                        System.out.println(i + " : " + jetonFlag.get(i).name0);
                                else
                                        System.out.println(i + " : " + jetonFlag.get(i).name1);
                        }
			nb = -1;
			System.out.println("Tapez de 0 a 1 pour choisir le jeton action a piocher");
			while (nb != 0 && nb != 1) {
				nb = scanner.nextInt();
				if (nb != 0 && nb != 1)
					System.out.println("Veuillez retaper :");
			}
			whichJeton(jetonFlag.get(nb), general);
			general.display();
			jetonFlag.remove(jetonFlag.get(nb));

			general.changeTour();;
			System.out.println("C'est au tour de " + joueur[nbTour % 2]
                                        + ", voici le jetons action restant :");
			if (jetonFlag.get(0).face == 0)
                        	System.out.println(0 + " : " + jetonFlag.get(0).name0);
                        else
                        	System.out.println(0 + " : " + jetonFlag.get(0).name1);
                        whichJeton(jetonFlag.get(0), general);
			general.display();
                        jetonFlag.remove(jetonFlag.get(0));

			update(general);
			if (fin(general, nbTour) == true)
				break;
			general.changeTour();
			System.out.println("Nombre de sablier afficheable : " + general.mrJack.sablier);
		}
	}

	public static boolean fin(General general, int nbTour) {
		System.out.println("Nb de tuiles restantes " + nbCard(general));
		if (nbTour > 8) {
			System.out.println("Mrjack gagne");
			return (true);
		}
		else if (general.mrJack.sablier >= 6) {
			System.out.println("MrJack gagne");
			return (true);
		}
		else if (nbCard(general) <= 1) {
			System.out.println("Detective gagne");
			return (true);
		}
		return (false);
	}

	public static int nbCard(General general) {
		int count = 0;

		for (int i = 0 ; i < 3 ; i++) {
			for (int j = 0; j < 3 ; j++) {
				if (general.board[i][j].returnDistrict == false)
					count++;
			}
		}
		return (count);
	}

	public static boolean isVisibleMrJack(boolean[] tuile, General general) {
		for (int i = 0 ; i < 3 ; i++) {
			for (int j = 0 ; j < 3 ; j++) {
				if (tuile[i * 3 + j] == true && general.board[i][j].name == general.mrJack.cardMrJack.name)
					return (true);
			}
		}
		return (false);
	}

	public static void update(General general) {
		boolean[] tuile = new boolean[9];
		tuile[0] = isVisible0(general);
		tuile[1] = isVisible1(general);
		tuile[2] = isVisible2(general);
		tuile[3] = isVisible3(general);
		tuile[4] = isVisible4(general);
		tuile[5] = isVisible5(general);
		tuile[6] = isVisible6(general);
		tuile[7] = isVisible7(general);
		tuile[8] = isVisible8(general);
		
		if (isVisibleMrJack(tuile, general)) {
			if (tuile[0] == false) {
				general.board[0][0].returnDistrict = true;
			} if (tuile[1] == false) {
				general.board[0][1].returnDistrict = true;
			} if (tuile[2] == false) {
				general.board[0][2].returnDistrict = true;
			} if (tuile[3] == false) {
				general.board[1][0].returnDistrict = true;
			} if (tuile[4] == false) {
				general.board[1][1].returnDistrict = true;
			} if (tuile[5] == false) {
				general.board[1][2].returnDistrict = true;
			} if (tuile[6] == false) {
				general.board[2][0].returnDistrict = true;
			} if (tuile[7] == false) {
				general.board[2][1].returnDistrict = true;
			} if (tuile[8] == false) {
				general.board[2][2].returnDistrict = true;
			}
			general.mrJack.sablier++;
			}
		else {
			if (tuile[0] == true) {
                                general.board[0][0].returnDistrict = true;
                        } if (tuile[1] == true) {
                                general.board[0][1].returnDistrict = true;
                        } if (tuile[2] == true) {
                                general.board[0][2].returnDistrict = true;
                        } if (tuile[3] == true) {
                                general.board[1][0].returnDistrict = true;
                        } if (tuile[4] == true) {
                                general.board[1][1].returnDistrict = true;
                        } if (tuile[5] == true) {
                                general.board[1][2].returnDistrict = true;
                        } if (tuile[6] == true) {
                        	general.board[2][0].returnDistrict = true;
                        } if (tuile[7] == true) {
                                general.board[2][1].returnDistrict = true;
                        } if (tuile[8] == true) {
                                general.board[2][2].returnDistrict = true;
                        }
		}
	}

	public static boolean isVisible0(General general) {
		if (general.les3Detective.position[0] == 1 || general.les3Detective.position[1] == 1 || general.les3Detective.position[2] == 1) {
			if (general.board[0][0].orientationForWall != 1)
				return (true);
		}
		if (general.les3Detective.position[0] == 12 || general.les3Detective.position[1] == 12 || general.les3Detective.position[2] == 12) {
			if (general.board[0][0].orientationForWall != 4)
                                return (true);
		}
		if (general.les3Detective.position[0] == 4 || general.les3Detective.position[1] == 4 || general.les3Detective.position[2] == 4) {
                        if (general.board[0][0].orientationForWall != 2 && general.board[0][1].orientationForWall != 4 && general.board[0][1].orientationForWall != 2
					&& general.board[0][2].orientationForWall != 4 && general.board[0][2].orientationForWall != 2)
                                return (true);
                }
		if (general.les3Detective.position[0] == 9 || general.les3Detective.position[1] == 9 || general.les3Detective.position[2] == 9) {
                        if (general.board[0][0].orientationForWall != 3 && general.board[1][0].orientationForWall != 3 && general.board[1][0].orientationForWall != 1
                                        && general.board[2][0].orientationForWall != 3 && general.board[2][0].orientationForWall != 1)
                                return (true);
                }
		return (false);
	}

	public static boolean isVisible1(General general) {
        	if (general.les3Detective.position[0] == 2 || general.les3Detective.position[1] == 2 || general.les3Detective.position[2] == 2) {
                        if (general.board[0][1].orientationForWall != 1)
                                return (true);
                }
                if (general.les3Detective.position[0] == 12 || general.les3Detective.position[1] == 12 || general.les3Detective.position[2] == 12) {
                        if (general.board[0][0].orientationForWall != 4 && general.board[0][0].orientationForWall != 2 && general.board[0][1].orientationForWall != 4)
                                return (true);
                }
                if (general.les3Detective.position[0] == 4 || general.les3Detective.position[1] == 4 || general.les3Detective.position[2] == 4) {
                        if (general.board[0][1].orientationForWall != 2 && general.board[0][2].orientationForWall != 4 && general.board[0][2].orientationForWall != 2)
                                return (true);
                }
                if (general.les3Detective.position[0] == 8 || general.les3Detective.position[1] == 8 || general.les3Detective.position[2] == 8) {
                        if (general.board[0][1].orientationForWall != 3 && general.board[1][1].orientationForWall != 3 && general.board[1][1].orientationForWall != 1
                                        && general.board[2][1].orientationForWall != 3 && general.board[2][1].orientationForWall != 1)
                                return (true);
                }
		return (false);
	}

	public static boolean isVisible2(General general) {
        	if (general.les3Detective.position[0] == 3 || general.les3Detective.position[1] == 3 || general.les3Detective.position[2] == 3) {
                        if (general.board[0][0].orientationForWall != 1)
                                return (true);
                }
                if (general.les3Detective.position[0] == 12 || general.les3Detective.position[1] == 12 || general.les3Detective.position[2] == 12) {
                        if (general.board[0][0].orientationForWall != 4 && general.board[0][0].orientationForWall != 2 && general.board[0][1].orientationForWall != 2
                                        && general.board[0][1].orientationForWall != 4 && general.board[0][2].orientationForWall != 4)
                                return (true);
                }
                if (general.les3Detective.position[0] == 4 || general.les3Detective.position[1] == 4 || general.les3Detective.position[2] == 4) {
                        if (general.board[0][2].orientationForWall != 2)
                                return (true);
                }
                if (general.les3Detective.position[0] == 7 || general.les3Detective.position[1] == 7 || general.les3Detective.position[2] == 7) {
                        if (general.board[0][2].orientationForWall != 3 && general.board[1][2].orientationForWall != 3 && general.board[1][2].orientationForWall != 1
                                        && general.board[2][2].orientationForWall != 3 && general.board[2][2].orientationForWall != 1)
                                return (true);
                }
		return (false);
	}

	public static boolean isVisible3(General general) {
        	if (general.les3Detective.position[0] == 11 || general.les3Detective.position[1] == 11 || general.les3Detective.position[2] == 11) {
                        if (general.board[1][0].orientationForWall != 4)
                                return (true);
                }
                if (general.les3Detective.position[0] == 1 || general.les3Detective.position[1] == 1 || general.les3Detective.position[2] == 1) {
                        if (general.board[0][0].orientationForWall != 1 && general.board[0][0].orientationForWall != 3 && general.board[1][0].orientationForWall != 1)
                                return (true);
                }
                if (general.les3Detective.position[0] == 9 || general.les3Detective.position[1] == 9 || general.les3Detective.position[2] == 9) {
                        if (general.board[1][0].orientationForWall != 3 && general.board[2][0].orientationForWall != 3 && general.board[2][0].orientationForWall != 1)
                                return (true);
                }
                if (general.les3Detective.position[0] == 5 || general.les3Detective.position[1] == 5 || general.les3Detective.position[2] == 5) {
                        if (general.board[1][0].orientationForWall != 2 && general.board[1][1].orientationForWall != 2 && general.board[1][1].orientationForWall != 4
                                        && general.board[1][2].orientationForWall != 2 && general.board[1][2].orientationForWall != 4)
                                return (true);
                }
		return (false);
	}

	public static boolean isVisible4(General general) {
        	if (general.les3Detective.position[0] == 2 || general.les3Detective.position[1] == 2 || general.les3Detective.position[2] == 2) {
                        if (general.board[0][1].orientationForWall != 1 && general.board[0][1].orientationForWall != 3 && general.board[1][1].orientationForWall != 1)
                                return (true);
                }
                if (general.les3Detective.position[0] == 5 || general.les3Detective.position[1] == 5 || general.les3Detective.position[2] == 5) {
                        if (general.board[1][1].orientationForWall != 2 && general.board[1][2].orientationForWall != 2 && general.board[1][2].orientationForWall != 4)
                                return (true);
                }
                if (general.les3Detective.position[0] == 8 || general.les3Detective.position[1] == 8 || general.les3Detective.position[2] == 8) {
                        if (general.board[2][1].orientationForWall != 1 && general.board[2][1].orientationForWall != 3 && general.board[1][1].orientationForWall != 3)
                                return (true);
                }
                if (general.les3Detective.position[0] == 11 || general.les3Detective.position[1] == 11 || general.les3Detective.position[2] == 11) {
                        if (general.board[1][0].orientationForWall != 4 && general.board[1][0].orientationForWall != 2 && general.board[1][1].orientationForWall != 4)
                                return (true);
                }
		return (false);
	}

	public static boolean isVisible5(General general) {
        	if (general.les3Detective.position[0] == 3 || general.les3Detective.position[1] == 3 || general.les3Detective.position[2] == 3) {
                        if (general.board[0][2].orientationForWall != 1 && general.board[0][2].orientationForWall != 3 && general.board[1][2].orientationForWall != 1)
                                return (true);
                }
                if (general.les3Detective.position[0] == 5 || general.les3Detective.position[1] == 5 || general.les3Detective.position[2] == 5) {
                        if (general.board[1][2].orientationForWall != 2)
                                return (true);
                }
                if (general.les3Detective.position[0] == 7 || general.les3Detective.position[1] == 7 || general.les3Detective.position[2] == 7) {
                        if (general.board[1][2].orientationForWall != 3 && general.board[2][2].orientationForWall != 1 && general.board[2][2].orientationForWall != 3)
                                return (true);
                }
                if (general.les3Detective.position[0] == 11 || general.les3Detective.position[1] == 11 || general.les3Detective.position[2] == 11) {
                        if (general.board[1][0].orientationForWall != 4 && general.board[1][0].orientationForWall != 2 && general.board[1][1].orientationForWall != 4
                                        && general.board[1][1].orientationForWall != 2 && general.board[1][2].orientationForWall != 4)
                                return (true);
                }
		return (false);
	}

	public static boolean isVisible6(General general) {
        	if (general.les3Detective.position[0] == 1 || general.les3Detective.position[1] == 1 || general.les3Detective.position[2] == 1) {
                        if (general.board[0][0].orientationForWall != 1 && general.board[0][0].orientationForWall != 3 && general.board[1][0].orientationForWall != 1
                                        && general.board[1][0].orientationForWall != 3 && general.board[2][0].orientationForWall != 1)
                                return (true);
                }
                if (general.les3Detective.position[0] == 6 || general.les3Detective.position[1] == 6 || general.les3Detective.position[2] == 6) {
                        if (general.board[2][0].orientationForWall != 2 && general.board[2][1].orientationForWall != 4 && general.board[2][1].orientationForWall != 2
                                        && general.board[2][2].orientationForWall != 4 && general.board[2][2].orientationForWall != 2)
                                return (true);
                }
                if (general.les3Detective.position[0] == 9 || general.les3Detective.position[1] == 9 || general.les3Detective.position[2] == 9) {
                        if (general.board[2][0].orientationForWall != 3)
                                return (true);
                }
                if (general.les3Detective.position[0] == 10 || general.les3Detective.position[1] == 10 || general.les3Detective.position[2] == 10) {
                        if (general.board[2][0].orientationForWall != 4)
                                return (true);
                }
		return (false);
	}

	public static boolean isVisible7(General general) {
        	if (general.les3Detective.position[0] == 2 || general.les3Detective.position[1] == 2 || general.les3Detective.position[2] == 2) {
                        if (general.board[0][1].orientationForWall != 1 && general.board[0][1].orientationForWall != 3 && general.board[1][1].orientationForWall != 1
                                        && general.board[1][1].orientationForWall != 3 && general.board[2][1].orientationForWall != 1)
                                return (true);
                }
                if (general.les3Detective.position[0] == 6 || general.les3Detective.position[1] == 6 || general.les3Detective.position[2] == 6) {
                        if (general.board[2][1].orientationForWall != 2 && general.board[2][2].orientationForWall != 4 && general.board[2][2].orientationForWall != 2)
                                return (true);
                }
                if (general.les3Detective.position[0] == 8 || general.les3Detective.position[1] == 8 || general.les3Detective.position[2] == 8) {
                        if (general.board[2][1].orientationForWall != 3)
                                return (true);
                }
                if (general.les3Detective.position[0] == 10 || general.les3Detective.position[1] == 10 || general.les3Detective.position[2] == 10) {
                        if (general.board[2][1].orientationForWall != 4 && general.board[2][0].orientationForWall != 2 && general.board[2][0].orientationForWall != 4)
                                return (true);
                }
		return (false);
	}

	public static boolean isVisible8(General general) {
        	if (general.les3Detective.position[0] == 3 || general.les3Detective.position[1] == 3 || general.les3Detective.position[2] == 3) {
                        if (general.board[2][0].orientationForWall != 1 && general.board[2][0].orientationForWall != 3 && general.board[2][1].orientationForWall != 1
                                        && general.board[2][1].orientationForWall != 3 && general.board[2][2].orientationForWall != 1)
                                return (true);
                }
                if (general.les3Detective.position[0] == 6 || general.les3Detective.position[1] == 6 || general.les3Detective.position[2] == 6) {
                        if (general.board[2][2].orientationForWall != 2)
                                return (true);
                }
                if (general.les3Detective.position[0] == 7 || general.les3Detective.position[1] == 7 || general.les3Detective.position[2] == 7) {
                        if (general.board[2][2].orientationForWall != 3)
                                return (true);
                }
                if (general.les3Detective.position[0] == 10 || general.les3Detective.position[1] == 10 || general.les3Detective.position[2] == 10) {
                        if (general.board[2][0].orientationForWall != 2 && general.board[2][0].orientationForWall != 4 && general.board[2][1].orientationForWall != 2
                                        && general.board[2][1].orientationForWall != 4 && general.board[2][0].orientationForWall != 4)
                                return (true);
                }
		return (false);
	}

	public static void whichJeton(Token jeton, General general) {
		if (jeton.face == 0 && jeton.name0 == "Holmes") {
			int nb = 0;

			System.out.println("Tapez 1 ou 2 pour avancer de 1 ou 2 cases avec Holmes");
			while (nb != 1 && nb != 2) {
				nb = scanner.nextInt();
				if (nb != 1 && nb != 2)
					System.out.println("Veuillez retaper 1 ou 2 :");
			}
			if (general.les3Detective.position[0] == 11 && nb == 2)
				general.les3Detective.position[0] = 1;
			else if (general.les3Detective.position[0] == 12)
				general.les3Detective.position[0] = nb;
			else
				general.les3Detective.position[0] = general.les3Detective.position[0] + nb;
		} else if (jeton.face == 0 && jeton.name0 == "Tourner") {
			int nb = -1;
			int nb2 = -1;

                        System.out.println("Tapez de 0 a 8 pour selectionner la case");
                        while (nb < 0 || nb > 8) {
                                nb = scanner.nextInt();
                                if (nb < 0 || nb > 8)
                                        System.out.println("Veuillez retaper de 0 a 8 :");
                        }

			System.out.println("Tapez de 1 a 4 pour l'orientation du mur");
                        while (nb2 < 1 || nb2 > 4) {
                                nb2 = scanner.nextInt();
                                if (nb2 < 1 || nb2 > 4)
                                        System.out.println("Tapez de 1 a 4 pour l'orientation :");
                        }
			general.board[nb / 3][nb % 3].orientationForWall = nb2;
		} else if (jeton.face == 0 && jeton.name0 == "Tourner") {
			int nb = -1;
                        int nb2 = -1;

                        System.out.println("Tapez de 0 a 8 pour selectionner la case");
                        while (nb < 0 || nb > 8) {
                                nb = scanner.nextInt();
                                if (nb < 0 || nb > 8)
                                        System.out.println("Veuillez retaper de 0 a 8 :");
                        }

                        System.out.println("Tapez de 1 a 4 pour l'orientation du mur");
                        while (nb2 < 1 || nb2 > 4) {
                                nb2 = scanner.nextInt();
                                if (nb2 < 1 || nb2 > 4)
                                        System.out.println("Tapez de 1 a 4 pour l'orientation :");
                        }
                        general.board[nb / 3][nb % 3].orientationForWall = nb2;
		} else if (jeton.face == 0 && jeton.name0 == "Watson") {
			int nb = 0;

                        System.out.println("Tapez 1 ou 2 pour avancer de 1 ou 2 cases avec Watson");
                        while (nb != 1 && nb != 2) {
                                nb = scanner.nextInt();
                                if (nb != 1 && nb != 2)
                                        System.out.println("Veuillez retaper 1 ou 2 :");
                        }
                        if (general.les3Detective.position[1] == 11 && nb == 2)
                                general.les3Detective.position[1] = 1;
                        else if (general.les3Detective.position[1] == 12)
                                general.les3Detective.position[1] = nb;
                        else
                                general.les3Detective.position[1] = general.les3Detective.position[1] + nb;
		} else if (jeton.face == 1 && jeton.name1 == "Alibi") {
			if (general.turn == 0) {
				for (int i = 0 ; i < 8 ; i++) {
					if (general.board[i / 3][i % 3].name == general.cards.get(0).name) {
						general.board[i / 3][i % 3].returnDistrict = true;
						break;
					}
				}
			} else {
				general.mrJack.sablier = general.mrJack.sablier + general.cards.get(0).sablier;
				general.cards.remove(0);
			}
		} else if (jeton.face == 1 && jeton.name1 == "Changer") {
			int nb = -1;
			int nb2 = -1;

			System.out.println("Tapez le numero de la case que vous voulez echanger, allant de 0 compris a 8 compris");
			while (nb < 0 || nb > 8) {
                                nb = scanner.nextInt();
                                if (nb < 0 || nb > 8)
                                        System.out.println("Veuillez retaper entre 0 et 8:");
                        }
			System.out.println("Tapez le 2eme numero de la case que vous voulez echanger, allant de 0 compris a 8 compris");
			while (nb2 < 0 || nb2 > 8) {
                                nb2 = scanner.nextInt();
                                if ((nb2 < 0 || nb2 > 8) && nb2 != nb)
                                        System.out.println("Veuillez retaper entre 0 et 8:");
                        }
			int i = (nb) / 3;
			int j = (nb) % 3;
			int i2 = (nb2) / 3;
			int j2 = (nb2) % 3;
			District flag = general.board[i][j];
			general.board[i][j] = general.board[i2][j2];
			general.board[i2][j2] = flag;
		} else if (jeton.face == 1 && jeton.name1 == "Joker") {
			int nb = 0;

                        System.out.println("Tapez 1, 2 ou 3 pour avancer de 1 case respectivement Holmes, Watson et Tobi");
                        while (nb != 1 && nb != 2 && nb != 3) {
                                nb = scanner.nextInt();
                                if (nb != 1 && nb != 2 && nb != 3)
                                        System.out.println("Veuillez retaper 1 ou 2 ou 3 :");
                        }

			if (nb == 1) {
				if (general.les3Detective.position[0] == 12)
					general.les3Detective.position[0] = 1;
				else
					general.les3Detective.position[0]++;
			} else if (nb == 2) {
				if (general.les3Detective.position[1] == 12)
                                        general.les3Detective.position[1] = 1;
                                else
                                        general.les3Detective.position[1]++;
			} else {
				if (general.les3Detective.position[2] == 12)
                                        general.les3Detective.position[2] = 1;
                                else
                                        general.les3Detective.position[2]++;
			}
		} else if (jeton.face == 1 && jeton.name1 == "Tobi") {
			int nb = 0;

                        System.out.println("Tapez 1 ou 2 pour avancer de 1 ou 2 cases avec Holmes");
                        while (nb != 1 && nb != 2) {
                                nb = scanner.nextInt();
                                if (nb != 1 && nb != 2)
                                        System.out.println("Veuillez retaper 1 ou 2 :");
                        }
                        if (general.les3Detective.position[2] == 11 && nb == 2)
                                general.les3Detective.position[2] = 1;
                        else if (general.les3Detective.position[2] == 12)
                                general.les3Detective.position[2] = nb;
                        else
                                general.les3Detective.position[2] = general.les3Detective.position[2] + nb;
		}
	}
}
