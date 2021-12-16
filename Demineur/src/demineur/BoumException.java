package demineur;

public class BoumException extends Exception {

	private int lig;
	private int col;
	
	public BoumException(int lig, int col) {
		this.lig = lig;
		this.col = col;
	}
	
	@Override
	public String getMessage() {
		return "Vous avez cliqué sur la mine située en ("+lig+","+col+"). Game Over!";
	}
	
}
