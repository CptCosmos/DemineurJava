package demineur;

public class Demineur {

	private Cell[][] champ;
	private int nbMines;
	private CellSlice cellsToCheck;

	public Demineur(int hauteur, int largeur, int nbMines) {
		if (nbMines > hauteur * largeur)
			throw new IllegalArgumentException("Il y a trop de mines.");

		this.cellsToCheck = new CellSlice();
		this.champ = new Cell[hauteur][largeur];

		for (int line = 0; line < this.champ.length; line++) {
			for (int col = 0; col < this.champ[line].length; col++) {
				this.champ[line][col] = new Cell(line, col);
			}
		}

		this.nbMines = nbMines;

		// On ajoute des mines :
		this.placerMines(nbMines);

		// Updating each cell nbMinesAround value
		this.computeNbMinesAroundEachCell();
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

		this.nbMines = cpt;
	}

	private void computeNbMinesAroundEachCell() {
		// Iterating over this.champ cells
		for (int line = 0; line < this.champ.length; line++) {
			for (int col = 0; col < this.champ[line].length; col++) {
				Cell cell = this.champ[line][col];

				int nbMinesAroundCell = this.countMineAroundCell(cell);
				cell.nbMinesAround = nbMinesAroundCell;
			}
		}

	}

	void isCellMine(Cell cell) throws BoumException {
		if (cell.isMine) { // sous-entendu champ[lig][col]==true
			throw new BoumException(cell.line, cell.col);
		}
	}

	int countMineAroundCell(Cell cell) {
		int nbMinesAroundCell = 0;

		// double-boucle qui parcourt les 8 voisins et la case choisie
		int cellLine = cell.line;
		int cellCol = cell.col;

		for (int line = cellLine - 1; line <= cellLine + 1; line++) {
			for (int col = cellCol - 1; col <= cellCol + 1; col++) {
				try {
					Cell iteringOnCell = champ[line][col];

					if (iteringOnCell.isMine) {
						nbMinesAroundCell++;
					}

				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		return nbMinesAroundCell;
	}

	void addUnrevealedCellNeighborsToCellToCheck(Cell cell) {
		int cellLine = cell.line;
		int cellCol = cell.col;

		int[][] neighborsCellsCoords = {
				{ cellLine + 1, cellCol },
				{ cellLine - 1, cellCol },
				{ cellLine, cellCol + 1 },
				{ cellLine, cellCol - 1 },
		};

		for (int index = 0; index < neighborsCellsCoords.length; index++) {
			try {
				int line = neighborsCellsCoords[index][0];
				int col = neighborsCellsCoords[index][1];
				Cell iteratingOnCell = champ[line][col];

				boolean isNotRevealedAndNotMine = !iteratingOnCell.isMine
						&& !iteratingOnCell.getCellRevealValue();

				if (isNotRevealedAndNotMine) {

					iteratingOnCell.RevealCell();

					// boolean cellNbMinesAroundIs0 = iteratingOnCell.nbMinesAround == 0;
					boolean cellIsNotInCellsToCheck = this.cellsToCheck.indexOf(iteratingOnCell) == -1;
					if (cellIsNotInCellsToCheck) {
						this.cellsToCheck.addElement(iteratingOnCell);
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
	}

	void handleClickOnCell(Cell cell) throws BoumException {

		cell.RevealCell();
		this.isCellMine(cell);
		this.cellsToCheck.addElement(cell);
		// this.addUnrevealedCellNeighborsToCellToCheck(cell);

		int count = this.cellsToCheck.getCount();
		do {
			// Retrieving last slice element and shifting it
			Cell lastCell = this.cellsToCheck.getElementAtIndex(count - 1);

			// If current cell nbMinesArount is 0 then look forward it's neighbors
			boolean shouldCheckLastCellNeighbors = lastCell.nbMinesAround == 0;
			if (shouldCheckLastCellNeighbors) {
				this.addUnrevealedCellNeighborsToCellToCheck(lastCell);
			}

			this.cellsToCheck.removeElementAt(count - 1);
			// Reset count
			count = this.cellsToCheck.getCount();
		} while (count > 0);

	}

	public Cell GetCell(int line, int col) {
		return this.champ[line][col];
	}

	public String getRawBoard() {
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
				Cell cell = this.champ[i][j];

				if (cell.isMine) {
					s += "| * ";
				} else {
					s += "| " + cell.nbMinesAround + " ";
				}
			}
			s += "|\n";
		}
		s = s + "|\n";
		return s;
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
				Cell cell = this.champ[i][j];
				boolean cellIsRevealed = cell.getCellRevealValue();

				if (cellIsRevealed) {
					if (cell.isMine) {
						s += "| * ";
					} else {
						s += "| " + cell.nbMinesAround + " ";
					}
				} else {
					s += "|   ";
				}

			}
			s = s + "|\n";
		}

		return s;
	}

}
