package RPSLS;

public enum Play {
	ROCK, SPOCK, PAPER, LIZARD, SCISSORS;
	
	public static int results(Play play1, Play play2) {
		int diff = (play1.ordinal() - play2.ordinal() + 5) % 5;
		
		if(diff == 0) {
			return 0;
		} else if(diff <= 2) {
			return 1;
		} else {
			return -1;
		}
	}
}
