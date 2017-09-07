package RPSLS;

public class BleedingEdge implements Player {

	@Override
	public Play shoot() {
		if(Math.random() < 0.5) {
			return Play.LIZARD;
		} else {
			return Play.SPOCK;
		}
	}

	@Override
	public void opponentsLastPlay(Play play) {
		// TODO Auto-generated method stub

	}

}
