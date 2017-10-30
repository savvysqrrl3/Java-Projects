# Rock Paper Scissors Lizard Spock

[Rock Paper Scissors Lizard Spock](http://www.samkass.com/theories/RPSSL.html) is an expansion to Rock Paper Scissors devised by Sam Kass.  The player can select from 5 actions, not 3, with each possible play beating 2 others and losing to 2 others.

![RPSLS diagram](RPSLS.gif)

Your task: Write a bot that can play RPSLS.  Specifically, **fork this repo**, then create a class that implements the `Player` interface by including the following methods:
* `shoot()`: Returns a `Play` (determined however you like)
* `opponentsLastPlay(Play play)`: Returns nothing.  This will be called after your bot makes it play, to notify it what the opponent played, in case that affects your bot's behavior.

You'll probably also want to add your bot in the `main` method in `Tournament.java`.

When you're done, make a pull request!  At the end of the day, we'll run the tournament, and the team whose bot has the best score will win a prize, maybe!

* [Opening in Spring Tools Suite](#opening-in-spring-tools-suite)
* [What's The Deal With That `Play` Thing?]()
* [Example Bots](#example-bots)

### Opening in Spring Tools Suite

### What's The Deal With That `Play` Thing?

### Example Bots

```java
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

public class BleedingEdge implements Player {

	@Override
	public Play shoot() {
		// Only use the newest, hottest technology.
		if(Math.random() < 0.5) {
			return Play.LIZARD;
		} else {
			return Play.SPOCK;
		}
	}

	@Override
	public void opponentsLastPlay(Play play) {
	}
}

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
}```