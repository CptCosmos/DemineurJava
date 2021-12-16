package demineur;

import java.util.*;

public class Utilitaires {
	
	/***
	 * renvoie une chaine d'au moins n caracteres composee d'espaces + s
	 * @param s chaine e formater
	 * @param n taille minimale de la chaene e retourner
	 * @return s augmentee d'espaces au debut
	 */
	public static String format(String s, int n) {
		int l = s.length();
		if (l < n) {
			for (int i = 1; i <= n-l; i++)  s = " " + s;
		}
		
		return s;
	}
	
	/***
	 * Retourne un entier choisi aleatoirement selon une loi uniforme entre 
	 *  les valeurs min et max
	 *  Vous pouvez utiliser la fonction Math.Random() qui 
	 *  renvoie un double tire aleatoirement selon une loi uniforme entre les 
	 *  valeurs 0 inclus et 1 exclus :
	 *  0 <= Math.Random() < 1
	 * @param min
	 * @param max
	 * @return
	 */
	public static int entierAleatoire(int min, int max) {
		// 
		return min + (int) Math.floor(Math.random()*(max-min+1)); 
	}
	
	
	public static int saisirEntier(Scanner sc, String entete, int min, int max) {
		int x = 0;
		boolean erreur;
		do {
			erreur = false;
			try {
				System.out.print(entete);
				x = Integer.parseInt(sc.nextLine());
				if ((x < min) || (x > max)) erreur = true;
			}
			catch (NumberFormatException e) {
				erreur = true;
				System.out.println("Entrez un entier entre "+min+" et "+max+" : ");
			}
		} while (erreur);
		return x;
	}
	
}
