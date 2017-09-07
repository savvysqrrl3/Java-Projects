package RPSLS;

public class CopyCat implements Player {
	private Play last = Play.PAPER; 

	@Override
	public Play shoot() {
		// Always plays whatever the opponent played last.  Starts with paper to beat Bart.
		return last;
	}

	@Override
	public void opponentsLastPlay(Play play) {
		this.last = play;
	}

}
