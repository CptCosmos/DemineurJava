package demineur;

public class Cell {

    public boolean revealed;
    public boolean isMine;
    public int nbMinesAround;
    public int col; // col
    public int line; // line

    public Cell(int x, int y) {

        // setup
        this.revealed = false;
        this.nbMinesAround = 0;
        this.col = x;
        this.line = y;

    }

    public void RevealCell() {
        this.revealed = true;
    }

    public void SetAsMine() {
        this.isMine = true;
    }
}
