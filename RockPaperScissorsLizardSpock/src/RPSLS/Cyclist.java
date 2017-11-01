package RPSLS;

public class Cyclist implements Player {
	private Play[] plays = {Play.ROCK, Play.PAPER, Play.SCISSORS, Play.LIZARD, Play.SPOCK};
	private int counter = 0;
	
	@Override
	public Play shoot() {
		// Always plays the same moves in the same order.
		// Very weak against CopyCat, natch
		counter = (counter + 1) % 5;
		return plays[counter];
	}

	@Override
	public void opponentsLastPlay(Play play) {

	}

}
