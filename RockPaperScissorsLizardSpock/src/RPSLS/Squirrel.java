package RPSLS;
import java.util.ArrayList;

public class Squirrel implements Player {

	private ArrayList<Play> allOpponentMoves = new ArrayList<>();
	private Play myPrevious;
	private ArrayList<Play> myMoves = new ArrayList<>();
	private int round = 0;
	private Play[] cyclist = {Play.PAPER, Play.SCISSORS, Play.ROCK, Play.SPOCK, Play.LIZARD};
	private Play[] copycat = {Play.SCISSORS, Play.ROCK, Play.SPOCK, Play.PAPER, Play.LIZARD, Play.SCISSORS, Play.SPOCK, Play.LIZARD, Play.ROCK, Play.PAPER};
	
	public Play[] getCyclist() {
		return cyclist;
	}
	public Play[] getCopycat() {
		return copycat;
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


	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
	
	public Play otherBotStrategy() {
		return Play.SPOCK;
	}
	
	public Play eliminateBartStrategy() {
		return Play.SPOCK;
	}
	
	public Play eliminateCyclistStrategy() {
		int counter = 0;
		
		if (round == 4) {
			return Play.SPOCK;
		}
		else if (round == 5) {
			return Play.LIZARD;
		}
		else {
			Play[] plays = getCyclist();
			counter = (counter + 1) % 5;
			return plays[counter];
		}
	}
	
	public Play eliminateCopyCatStrategy() {
		int counter = 0;
		
		if (round == 5) {
			return Play.LIZARD;
		}
		else {
			Play[] plays = getCopycat();
			counter = (counter + 1) % 10;
			return plays[counter];
		}
	}

	@Override
	public Play shoot() {

		ArrayList<Play> allPlays = this.getAllOpponentMoves();
		round++;
		System.out.println(round);
		if(round > 1000) {
			round = 1;
		}
//		System.out.println("*************");
		if(round == 1) {

			return Play.PAPER;
		}
		else if(round == 2) {
			return Play.SCISSORS;
		}
		else if(round == 3) {
			if(allPlays.get(0) == Play.ROCK && allPlays.get(1) == Play.ROCK) {
				return this.eliminateBartStrategy();
			}
			else {
				return Play.ROCK;
			}
		}
			
		else if(round == 4) {
			if(allPlays.get(0) == Play.ROCK && allPlays.get(1) == Play.PAPER && allPlays.get(2) == Play.SCISSORS) {
				return this.eliminateCyclistStrategy();
			}
			else {
				return Play.SPOCK;
			}
		}
		else if(round == 5) {
			if(allPlays.get(0) == Play.PAPER && allPlays.get(1) == Play.PAPER && allPlays.get(2) == Play.SCISSORS && allPlays.get(3) == Play.ROCK) {
				return this.eliminateCopyCatStrategy();
			}
			else {
				return Play.LIZARD;
			}
		}
		else {
			return this.otherBotStrategy();
		}
	
		
	}

	@Override
	public void opponentsLastPlay(Play play) {

		this.allOpponentMoves.add(play);
	}

	public void myPlays(Play move) {

		this.myMoves.add(move);
	}
	
}
