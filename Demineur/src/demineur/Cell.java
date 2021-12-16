package demineur;

public class Cell {

    private boolean revealed;
    public boolean isMine;
    public int nbMinesAround;
    public int col;
    public int line;

    public Cell(int line, int col) {
        this.revealed = false;
        this.nbMinesAround = 0;
        this.col = col;
        this.line = line;
    }

    public void RevealCell() {
        this.revealed = true;
    }

    // we should use java getter and setter
    public boolean getCellRevealValue() {
        return this.revealed;
    }

    public void SetAsMine() {
        this.isMine = true;
    }
}
