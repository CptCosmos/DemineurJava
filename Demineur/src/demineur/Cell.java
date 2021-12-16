package demineur;

public class Cell {

    private boolean revealed;
    public boolean isMine;
    public int nbMinesAround;
    public int col; // col
    public int line; // line

    public Cell(int line, int col) {

        // setup
        this.revealed = false;
        this.nbMinesAround = 0;
        this.col = col;
        this.line = line;

    }

    public void RevealCell() {
        this.revealed = true;
    }

    public boolean getCellRevealValue() {
        return this.revealed;
    }

    public void SetAsMine() {
        this.isMine = true;
    }
}
