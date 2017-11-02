package RPSLS;

// See also: http://www.samkass.com/theories/RPSSL.html

import java.util.ArrayList;
import java.util.Arrays;

public class Tournament {
	private static ArrayList<Player> bots = new ArrayList<>();

	private static int NUMBER_OF_ROUNDS = 1000; 
	
	private static int match(Player player1, Player player2) {
		return match(player1, player2, false);
	}
	
	private static int match(Player player1, Player player2, boolean log) {
		int runningTotal = 0;
		
		for(int i = 0; i < NUMBER_OF_ROUNDS; i++) {
			Play move1 = player1.shoot();
			Play move2 = player2.shoot();
			runningTotal += Play.results(move1, move2);
			player1.opponentsLastPlay(move2);
			player2.opponentsLastPlay(move1);	
			
			if(log) {
				System.out.printf("%s played %s; %s played %s \n", player1.getClass().getSimpleName(), move1, player2.getClass().getSimpleName(), move2);
			}
		}
		
		if(log) {
			System.out.println();
		}
		
		return runningTotal;
	}
	
	public static void main(String[] args) {
		bots.add(new BartSimpson());
		bots.add(new BleedingEdge());
		bots.add(new CopyCat());
		bots.add(new Cyclist());
		bots.add(new Squirrel());
		bots.add(new WinnerBot());

		
		System.out.println(bots.size());
		int[][] results = new int[bots.size()][bots.size()];
		
		for(int bot1 = 0; bot1 < bots.size() - 1; bot1++) {
			for(int bot2 = bot1 + 1; bot2 < bots.size(); bot2++ ) {
				int res = match(bots.get(bot1), bots.get(bot2));
				// For testing, consider using 
//				match(bots.get(bot1), bots.get(bot2), true);
				// The extra flag will print each bot's play to the console.
				
				results[bot1][bot2] = res;
				results[bot2][bot1] = -res;
			}
		}
		
		System.out.println(Arrays.deepToString(results));
		
		for(int i = 0; i < bots.size(); i++) {
			int wins = 0;
			for(int j = 0; j < results[i].length; j++) {
				if(results[i][j] > 0) {
					wins++;
				}
			}
			System.out.println(bots.get(i).getClass().getSimpleName() + ": " + wins);
		}
	}

}
