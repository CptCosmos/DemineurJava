package demineur;

import java.util.Arrays;

public class Demineur{

	private boolean[][] champ;
	private int nbMines;

	public Demineur(int hauteur, int largeur, int nbMines) {
		if (nbMines > hauteur*largeur) 
			throw new IllegalArgumentException("Il y a trop de mines.");
		
		this.champ = new boolean[hauteur][largeur];
		this.nbMines = nbMines;

		// On ajoute des mines :
		this.placerMines(nbMines);
	}

	public int getHauteur() { return this.champ.length; }
	public int getLargeur() { return this.champ[0].length; }
	


	private void placerMines(int nb) {
		// Code à compléter : placer nb mines
		int cpt = 0, i, j;
		
		while (cpt < nb) {
			i = Utilitaires.entierAleatoire(0, getHauteur()-1);
			j = Utilitaires.entierAleatoire(0, getLargeur()-1);
			if (this.champ[i][j] == false) { // ou if (!champ[i][j])
				cpt++;
				this.champ[i][j] = true;
			}
		}
	}

	void boumOuPasBoum(int lig, int col) throws BoumException
	{
		if (champ[lig][col]){ // sous-entendu champ[lig][col]==true
			throw new BoumException(lig, col);
		}
	}

	int compteMines(int lig, int col) throws BoumException {
		int res = 0;
		boumOuPasBoum(lig, col);
		// double-boucle qui parcourt les 8 voisins et la case choisie
		for (int i=lig-1; i<=lig+1; i++){
			for (int j=col-1; j<=col+1; j++){
				try {
					if (champ[i][j]){
						res = res + 1;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) { }
			}
		}
		return res;
	}

	@Override
	public String toString() {
		String s = "\n   ";
		
		for (int j = 0; j < getLargeur(); j++) {
			if (j>=10) s += " "+ j+" ";
			else s += "  "+j+" ";
		}
		s += "\n";
		
		for (int i = 0; i < champ.length; i++) {
			s += Utilitaires.format(""+i, 2) + " ";
			for (int j = 0; j < champ[i].length; j++) {
				if (this.champ[i][j]) s += "| * ";
				else s += "|   ";
			}
			s = s + "|\n";
		}
		
		return s;
	}

	
}
