package demineur;

import java.util.*;

public class MainDemineur {

	public static void main(String[] args) throws BoumException {

		System.out.println("*****" + Utilitaires.format("123443", 5) + "*****");

		// ???
		int N = 10000;
		int[] compteur = new int[6];

		for (int i = 1; i <= N; i++) {
			int alea = Utilitaires.entierAleatoire(1, 5);
			compteur[alea]++;
		}
		for (int i = 1; i <= 5; i++) {
			System.out.println("Frequence de " + i + " : " + (double) compteur[i] / N);
		}

		System.out.println(Arrays.toString(compteur));

		System.out.println((int) Math.floor(-5.88));
		///

		System.out.println("TOTOTO");

		Scanner sc = new Scanner(System.in);

		try {
			Demineur jeu = new Demineur(15, 12, 25);
			System.out.println(jeu);
			int icol, ilig, nbmines;
			while (true) {

				// Waiting for user selection
				ilig = Utilitaires.saisirEntier(sc, "Entrez le numero de ligne: ",
						0, jeu.getHauteur() - 1);
				icol = Utilitaires.saisirEntier(sc, "Entrez le numero de colonne: ",
						0, jeu.getLargeur() - 1);

				Cell selectedCell = jeu.GetCell(ilig, icol);
				///

				nbmines = jeu.compteMines(selectedCell);
				System.out.println("Nombre de mines: " + nbmines);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (BoumException e) {
			System.out.println(e.getMessage());
		}

		sc.close();
	}
}