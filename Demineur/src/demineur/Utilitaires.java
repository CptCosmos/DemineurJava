package demineur;

import java.util.*;

public class Utilitaires {
	
	/***
	 * renvoie une cha�ne d'au moins n caract�res compos�e d'espaces + s
	 * @param s cha�ne � formater
	 * @param n taille minimale de la cha�ne � retourner
	 * @return s augment�e d'espaces au d�but
	 */
	public static String format(String s, int n) {
		int l = s.length();
		if (l < n) {
			for (int i = 1; i <= n-l; i++)  s = " " + s;
		}
		
		return s;
	}
	
	/***
	 * Retourne un entier choisi al�atoirement selon une loi uniforme entre 
	 *  les valeurs min et max
	 *  Vous pouvez utiliser la fonction Math.Random() qui 
	 *  renvoie un double tir� al�atoirement selon une loi uniforme entre les 
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
				if ((x < min) || (x > max)) {
					erreur = true;
					System.out.println("Veuillez entrer un entier entre "+min+" et "+max+"");
				}
			}
			catch (NumberFormatException e) {
				erreur = true;
				System.out.println("Entrez un entier entre "+min+" et "+max+" : ");
			}
		} while (erreur);
		return x;
	}
	
}
