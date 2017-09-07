package RPSLS;

public class BartSimpson implements Player {

	@Override
	public Play shoot() {
		// Good ol' rock.  Nothing beats that.
		return Play.ROCK;
	}

	@Override
	public void opponentsLastPlay(Play play) {
	}

}
