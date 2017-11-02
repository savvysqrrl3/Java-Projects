package RPSLS;

import java.util.ArrayList;

public class Awareqwx implements Player{
	
	private Play myPlay = Play.SPOCK;
	
	private Play[] playList = {Play.ROCK, Play.PAPER, Play.SCISSORS, Play.LIZARD, Play.SPOCK};

	private ArrayList<Play> myPlays;
	private ArrayList<Play> theirPlays;
	
	public Awareqwx()
	{
		myPlays = new ArrayList<Play>();
		theirPlays = new ArrayList<Play>();
	}
	
	@Override
	public Play shoot() {
		
		myPlay = findPlay();
		
		myPlays.add(myPlay);
		if(myPlays.size() > 100)
		{
			myPlays.remove(0);
		}
		return myPlay;
	}

	@Override
	public void opponentsLastPlay(Play play) {
		theirPlays.add(play);
		if(theirPlays.size() > 100)
		{
			theirPlays.remove(0);
		}
	}
	
	private int[] findMax(int[] arr)
	{
		int[] max = new int[4];
		max[0] = max[2] = arr[0];
		max[1] = max[3] = 0;
		for(int i = 1; i < arr.length; i++)
		{
			if(arr[i] > max[0])
			{
				max[2] = max[0];
				max[3] = max[1];
				max[0] = arr[i];
				max[1] = i;
			}
			else if(arr[i] > max[2])
			{
				max[2] = arr[i];
				max[3] = i;
			}
		}
		return max;
	}
	
	private boolean plusMinus(int i, int j, int d)
	{
		return j >= i - d && j <= i + d;
	}
	
	private Play counter(Play p)
	{
		switch(p)
		{
		case ROCK:
			return Play.PAPER;
		case PAPER:
			return Play.SCISSORS;
		case SCISSORS:
			return Play.ROCK;
		case LIZARD:
			return Play.SCISSORS;
		case SPOCK:
			return Play.PAPER;
		default:
			return Play.SPOCK;
		}
	}
	
	private Play counter(Play p, Play q)
	{
		switch(p)
		{
		case ROCK:
			switch(q) {
			case ROCK:
				return Play.PAPER;
			case PAPER:
				return Play.PAPER;
			case SCISSORS:
				return Play.SPOCK;
			case LIZARD:
				return Play.ROCK;
			case SPOCK:
				return Play.PAPER;
			default:
				return Play.PAPER;
			}
		case PAPER:
			switch(q) {
			case ROCK:
				return Play.PAPER;
			case PAPER:
				return Play.SCISSORS;
			case SCISSORS:
				return Play.SCISSORS;
			case LIZARD:
				return Play.SCISSORS;
			case SPOCK:
				return Play.LIZARD;
			default:
				return Play.SCISSORS;
			}
		case SCISSORS:
			switch(q) {
			case ROCK:
				return Play.SPOCK;
			case PAPER:
				return Play.SCISSORS;
			case SCISSORS:
				return Play.ROCK;
			case LIZARD:
				return Play.ROCK;
			case SPOCK:
				return Play.SPOCK;
			default:
				return Play.ROCK;
			}
		case LIZARD:
			switch(q) {
			case ROCK:
				return Play.ROCK;
			case PAPER:
				return Play.SCISSORS;
			case SCISSORS:
				return Play.ROCK;
			case LIZARD:
				return Play.ROCK;
			case SPOCK:
				return Play.LIZARD;
			default:
				return Play.SCISSORS;
			}
		case SPOCK:
			switch(q) {
			case ROCK:
				return Play.PAPER;
			case PAPER:
				return Play.LIZARD;
			case SCISSORS:
				return Play.SPOCK;
			case LIZARD:
				return Play.LIZARD;
			case SPOCK:
				return Play.PAPER;
			default:
				return Play.PAPER;
			}
		default:
			return Play.SPOCK;
		}
	}
	
	private Play findPlay()
	{
		if(myPlays.size() == 0 && theirPlays.size() == 0)
		{
			return Play.SPOCK;
		}
		
		Play play = Play.SPOCK;
		
		int[] plays = new int[5];
		
		for(int i = 0; i < theirPlays.size(); i++)
		{
			switch(theirPlays.get(i)) {
			case ROCK:
				plays[0] ++;
				break;
			case PAPER:
				plays[1] ++;
				break;
			case SCISSORS:
				plays[2] ++;
				break;
			case LIZARD:
				plays[3] ++;
				break;
			case SPOCK:
				plays[4] ++;
				break;
			}
		}
		
		int[] most = findMax(plays);
		
		int threshold = 3;
		
		if(plusMinus(most[0], most[2], threshold))
		{
			boolean test = true;
			for(int i = 0; i < plays.length; i++)
			{
				for(int j = 0; j < plays.length; j++)
				{
					if(i != j)
					{
						if(!plusMinus(plays[i], plays[j], threshold))
						{
							test = false;
						}
					}
				}
				if(test)
				{
					return counter(theirPlays.get(theirPlays.size() - 1));
				}
				else
				{
					play = counter(playList[most[1]], playList[most[3]]);
				}
			}
		}
		else
		{
			play = counter(playList[most[1]]);
		}
		
		return play;
	}

}