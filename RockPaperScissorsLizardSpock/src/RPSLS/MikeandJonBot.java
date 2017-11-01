package RPSLS;

public class MikeandJonBot implements Player {
//	private String[] method = {"Paper","Bart","Bleeding","Copycat","Cyclist","Random"};
	private int[] resultsArray = {0,0,0,0,0};
//	Array Key -----> {Rock,Paper,Scissors,Lizard,Spock}
	private int counter = 0;
	String method;
	private Play[] cyclistPlays = {Play.PAPER, Play.SCISSORS, Play.ROCK, Play.SCISSORS, Play.PAPER};
	private Play[] copycatPlays = {Play.SCISSORS ,Play.ROCK,Play.SPOCK, Play.PAPER, Play.SCISSORS};
	private int paper;
	private int rock;
	private int scissors;
	private int lizard;
	private int spock;
	
		
	
	@Override
	public Play shoot() {
		if(counter >= 1000) {
			counter = 0;
			method = "";
			resultsArray[0]=0;
			resultsArray[1]=0;
			resultsArray[2]=0;
			resultsArray[3]=0;
			resultsArray[4]=0;
			rock = 0;
			paper = 0;
			scissors = 0;
			lizard = 0;
			spock = 0;
		}
		if(counter == 5){
			method = checkMethod();
		}
		counter ++;
		if(method == "Bart") {
			return Play.PAPER;
		} else if(method == "Bleeding") {
			return Play.LIZARD;
		} else if (method == "Copy"){
			return copycatPlays[counter % 5];
		} else if (method == "Cyclist") {
			return cyclistPlays[counter % 5];
		} else if(method == "Human") {
			if(rock > paper && rock > scissors && rock > lizard && rock > spock) {
				return Play.ROCK;
			} else if(paper > rock && paper > scissors && paper > lizard && paper > spock) {
				return Play.PAPER;
			} else if (scissors > rock && scissors > paper && scissors > lizard && scissors > spock) {
				return Play.SCISSORS;
			} else if (lizard > rock && lizard > paper && lizard > scissors && lizard > spock) {
				return Play.LIZARD;
			} else {
				return Play.SPOCK;
			}
		
		}
		return Play.PAPER;
	}

	@Override
	public void opponentsLastPlay(Play play) {		
		if(play == Play.ROCK) {
			resultsArray[0] ++;
			spock ++;
			paper ++;
		}
		if(play == Play.PAPER) {
			resultsArray[1] ++;
			lizard++;
			scissors ++;
		}
		if(play == Play.SCISSORS) {
			resultsArray[2] ++;
			spock ++;
			rock ++;
		}
		if(play == Play.LIZARD) {
			resultsArray[3] ++;
			scissors ++;
			rock++;			
		}
		if(play == Play.SPOCK) {
			resultsArray[4] ++;
			paper++;
			lizard++;
		}		
	}
	public String checkMethod(){ 
		if(resultsArray[0]==5) {
			return "Bart";
		}
		if(resultsArray[1]==4 && resultsArray[0]==1) {
			return "Copy";
		}
		if(resultsArray[0]==1 && resultsArray[1]==1 && resultsArray[2]==1 && resultsArray[3]==1 && resultsArray[4]==1) {
			return "Cyclist";
		}
		if(resultsArray[0]==0 && resultsArray[1]==0 && resultsArray[2] == 0) {
			return "Bleeding";
		}
		else {
			return "Human";
		}
		
	}

}

