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
		if (allPlays.size() >= 995) {
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
		int weight = (allPlays.size() / 10) + 3;
		
		for(Play play : recentPlays) {
			if(play == Play.ROCK) {
				rock += weight;
			}
			if(play == Play.PAPER) {
				paper += weight;
			}
			if(play == Play.SPOCK) {
				spock += weight;
			}
			if(play == Play.SCISSORS) {
				scissors += weight;
			}
			if(play == Play.LIZARD) {
				lizard += weight;
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
//		System.out.println("Index 0: " + plays.get(0).getNum());
//		System.out.println("Index 4: " + plays.get(4).getNum());
		Play win = getWinningPlay(plays.get(4).getPlay());
		return win;
		
	}
	@Override
	public void opponentsLastPlay(Play play) {
		if(recentPlays.size() < 5) {
			recentPlays.add(play);
		} else {
			Play removedPlay = recentPlays.remove(0);
			allPlays.add(removedPlay);
			recentPlays.add(play);
		}
	}

}
