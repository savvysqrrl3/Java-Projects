package RPSLS;

import java.util.EnumMap;

public enum Play {
	ROCK, PAPER, SCISSORS, LIZARD, SPOCK;
	
	private static final EnumMap<Play, EnumMap<Play, Integer>> whatBeatsWhat = new EnumMap<Play, EnumMap<Play, Integer>>(Play.class){
		{
			EnumMap<Play, Integer> rockResults = new EnumMap<Play, Integer>(Play.class);
			rockResults.put(ROCK, 0);
			rockResults.put(PAPER, -1);
			rockResults.put(SCISSORS, 1);
			rockResults.put(LIZARD, 1);
			rockResults.put(SPOCK, -1);
			
			EnumMap<Play, Integer> paperResults = new EnumMap<Play, Integer>(Play.class);
			paperResults.put(ROCK, 1);
			paperResults.put(PAPER, 0);
			paperResults.put(SCISSORS, -1);
			paperResults.put(LIZARD, -1);
			paperResults.put(SPOCK, 1);
			
			EnumMap<Play, Integer> scissorsResults = new EnumMap<Play, Integer>(Play.class);
			scissorsResults.put(ROCK, -1);
			scissorsResults.put(PAPER, 1);
			scissorsResults.put(SCISSORS, 0);
			scissorsResults.put(LIZARD, 1);
			scissorsResults.put(SPOCK, -1);
			
			EnumMap<Play, Integer> lizardResults = new EnumMap<Play, Integer>(Play.class);
			lizardResults.put(ROCK, -1);
			lizardResults.put(PAPER, 1);
			lizardResults.put(SCISSORS, -1);
			lizardResults.put(LIZARD, 0);
			lizardResults.put(SPOCK, 1);
			
			EnumMap<Play, Integer> spockResults = new EnumMap<Play, Integer>(Play.class);
			spockResults.put(ROCK, 1);
			spockResults.put(PAPER, -1);
			spockResults.put(SCISSORS, 1);
			spockResults.put(LIZARD, -1);
			spockResults.put(SPOCK, 0);
			
			put(ROCK, rockResults);
			put(PAPER, paperResults);
			put(SCISSORS, scissorsResults);
			put(LIZARD, lizardResults);
			put(SPOCK, spockResults);
		}
	};
	
	public static int results(Play play1, Play play2) {
		return whatBeatsWhat.get(play1).get(play2);
	}
}
