package demineur;

public class Demineur {

	private Cell[][] champ;
	private int nbMines;

	public Demineur(int hauteur, int largeur, int nbMines) {
		if (nbMines > hauteur * largeur)
			throw new IllegalArgumentException("Il y a trop de mines.");

		this.champ = new Cell[hauteur][largeur];

		for (int line = 0; line < this.champ.length; line++) {
			for (int col = 0; col < this.champ[line].length; col++) {
				this.champ[line][col] = new Cell(line, col);
			}
		}

		this.nbMines = nbMines;

		// On ajoute des mines :
		this.placerMines(nbMines);
	}

	public int getHauteur() {
		return this.champ.length;
	}

	public int getLargeur() {
		return this.champ[0].length;
	}

	// This method will spread mine in the game board
	// It will also mutate the Demineur class nbMines value
	private void placerMines(int nb) {
		// Code a completer : placer nb mines
		int cpt = 0, i, j;

		System.out.println("BEGIN");
		while (cpt < nb) {
			i = Utilitaires.entierAleatoire(0, getHauteur() - 1);
			j = Utilitaires.entierAleatoire(0, getLargeur() - 1);

			Cell currCell = this.champ[i][j];

			boolean selectedCellIsNotAlreadyMine = !currCell.isMine;
			if (selectedCellIsNotAlreadyMine) {
				cpt++;
				currCell.isMine = true;
			}
		}
		System.out.println("END");

		this.nbMines = cpt;
	}

	void boumOuPasBoum(Cell cell) throws BoumException {
		if (cell.isMine) { // sous-entendu champ[lig][col]==true
			throw new BoumException(cell.line, cell.col);
		}
	}

	int compteMines(Cell cell) throws BoumException {
		int res = 0;
		boumOuPasBoum(cell);
		// double-boucle qui parcourt les 8 voisins et la case choisie
		int cellLine = cell.line;
		int cellCol = cell.col;

		for (int line = cellLine - 1; line <= cellLine + 1; line++) {
			for (int col = cellCol - 1; col <= cellCol + 1; col++) {
				try {
					Cell iteringOnCell = champ[line][col];

					if (iteringOnCell.isMine) {
						res++;
					}

				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		return res;
	}

	public Cell GetCell(int line, int col) {
		return this.champ[line][col];
	}

	// This allow to just println the class
	@Override
	public String toString() {
		String s = "\n   ";

		for (int j = 0; j < getLargeur(); j++) {
			if (j >= 10)
				s += " " + j + " ";
			else
				s += "  " + j + " ";
		}
		s += "\n";

		for (int i = 0; i < champ.length; i++) {
			s += Utilitaires.format("" + i, 2) + " ";
			for (int j = 0; j < champ[i].length; j++) {
				if (this.champ[i][j].isMine) {
					s += "| * ";
				} else {
					s += "|   ";
				}
			}
			s = s + "|\n";
		}

		return s;
	}

}
