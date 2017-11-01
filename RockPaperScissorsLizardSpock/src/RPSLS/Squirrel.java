package RPSLS;
import java.util.ArrayList;

public class Squirrel implements Player {

	private Play previous;
	private ArrayList<Play> allOpponentMoves = new ArrayList<>();
	private Play myPrevious;
	private ArrayList<Play> myMoves = new ArrayList<>();
	
	
	public Play getPrevious() {
		System.out.println("The opponent's move was " + this.getPrevious());
		return previous;
	}

	public ArrayList<Play> getAllOpponentMoves() {
		return allOpponentMoves;
	}


	public Play getMyPrevious() {
		return myPrevious;
	}


	public ArrayList<Play> getMyMoves() {
		return myMoves;
	}


	@Override
	public Play shoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void opponentsLastPlay(Play play) {
		this.previous = play;
		this.allOpponentMoves.add(play);
	}

	public void myPlays(Play move) {
		this.previous = move;
		this.myMoves.add(move);
	}
	
}
