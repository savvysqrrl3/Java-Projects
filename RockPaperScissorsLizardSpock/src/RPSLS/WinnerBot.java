package RPSLS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class WinnerBot implements Player{
	ArrayList<Play> recentPlays = new ArrayList<>();
	ArrayList<Play> allPlays = new ArrayList<>();
	
	
	@Override
	public Play shoot() {
		if (allPlays.size() >= 990) {
			allPlays.clear();
			recentPlays.clear();
		}
		if (recentPlays.size() > 1) {
			Play lastPlay = recentPlays.get(0);
			int count = 0;
			for (int i = 0; i < 2; i++) {
				if (recentPlays.get(i) == lastPlay) {
					count++;
				}
			}
			if (count > 1) {
				return getWinningPlay(lastPlay);
			}
			return getProbablePlay();
		} else if (recentPlays.size() == 0) {
			return Play.SPOCK;
		} else {
			return Play.LIZARD;
		}
		
		
	}
	
	private Play getWinningPlay(Play play) {
		if (play == Play.ROCK) {
			return Play.SPOCK;
		} else if (play == Play.PAPER) {
			return Play.LIZARD;
		} else if (play == Play.SCISSORS) {
			return Play.SPOCK;
		} else if (play == Play.SPOCK) {
			return Play.LIZARD;
		} else {
			return Play.ROCK;
		}
	}
	
	private Play getProbablePlay() {
		int rock = 0;
		int lizard = 0;
		int paper = 0;
		int scissors = 0;
		int spock = 0;
		
		for(Play play : recentPlays) {
			if(play == Play.ROCK) {
				rock += 3;
			}
			if(play == Play.PAPER) {
				paper += 3;
			}
			if(play == Play.SPOCK) {
				spock += 3;
			}
			if(play == Play.SCISSORS) {
				scissors += 3;
			}
			if(play == Play.LIZARD) {
				lizard += 3;
			}
		}
		
		for(Play play : allPlays) {
			if(play == Play.ROCK) {
				rock += 1;
			}
			if(play == Play.PAPER) {
				paper += 1;
			}
			if(play == Play.SPOCK) {
				spock += 1;
			}
			if(play == Play.SCISSORS) {
				scissors += 1;
			}
			if(play == Play.LIZARD) {
				lizard += 1;
			}
		}
		ArrayList<Tuple<Play, Integer>> plays = new ArrayList<Tuple<Play, Integer>>();
		plays.add(new Tuple(Play.ROCK, rock));
		plays.add(new Tuple(Play.PAPER, paper));
		plays.add(new Tuple(Play.SPOCK, spock));
		plays.add(new Tuple(Play.SCISSORS, scissors));
		plays.add(new Tuple(Play.LIZARD, lizard));
		
		Collections.sort(plays);
		return plays.get(0).getPlay();
		
	}
	@Override
	public void opponentsLastPlay(Play play) {
		if(recentPlays.size() < 10) {
			recentPlays.add(play);
		} else {
			Play removedPlay = recentPlays.remove(0);
			allPlays.add(removedPlay);
			recentPlays.add(play);
		}
	}

}
