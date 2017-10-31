# Rock Paper Scissors Lizard Spock

[Rock Paper Scissors Lizard Spock](http://www.samkass.com/theories/RPSSL.html) is an expansion to Rock Paper Scissors devised by Sam Kass.  The player can select from 5 actions, not 3, with each possible play beating 2 others and losing to 2 others.

![RPSLS diagram](RPSLS.gif)

Your task: Write a bot that can play RPSLS.  Specifically, **fork this repo**, then create a class that implements the `Player` interface by including the following methods:
* `shoot()`: Returns a `Play` (determined however you like)
* `opponentsLastPlay(Play play)`: Returns nothing.  This will be called after your bot makes it play, to notify it what the opponent played, in case that affects your bot's behavior.

You'll probably also want to add your bot in the `main` method in `Tournament.java`.

When you're done, make a pull request!  At the end of the day, we'll run the tournament, and the team whose bot has the best score will win a prize, maybe!

## Specifics

* [Opening in Spring Tools Suite](#opening-in-spring-tools-suite)
* [What's The Deal With That `Play` Thing?](#whats-the-deal-with-that-play-thing)
* [Example Bots](#example-bots)

### Opening in Spring Tools Suite

You can find (somewhat terse) instructions for using the built-in Sprint Tools Git integration [here](http://ojitha.blogspot.com/2013/04/how-to-github-projects-in-spring-tool.html).

Alternately, to do things the less correct but possibly easier way, after you've forked and cloned this project, follow these steps to add it to Spring Tools Suite:

* Select File...Open Projects from File System...
* Click the Directory... button next to the long "Import source" dropdown.
* Select the folder you cloned, then click OK.
* The folder should now show with a checkbox next to it in the middle frame.  Make sure the box is actually checked, then click Finish.

### What's The Deal With That `Play` Thing?

In this project, `Play` is an Enum, a special type of class that exists for defining constants, specifically for cases where there are a limited number of potential values.  In this case, there are 5 and only 5 valid plays, so I can list them (or enumerate them, hence the name) in one place.

Think about it like this: What type should the `shoot()` method return?  One possible answer would be `String`--after all, the player is choosing `"rock"` or `"lizard"` or whatever.  But how much extra code would I have to write to make sure that I interpreted `"rock"` the same as `"ROCK"` the same as `"   rock"` the same as `"R*O*C*K!"`?  What should I do if the player returns something invalid, like `"vulcan"` or `"clown"`?  It's possible to handle all of this, but I would need to do so every time I receive a `shoot()`.

Another possible answer would be an `int`, or some other numeric type, between 0 and 4 (or 1 and 5, or some other range).  This avoids some of the problems with strings, as Java will recognize that `2` equals `2.0` equals `4/2`.  It's also easier to check that the move is allowed (`move >= 0 && move <= 4` would do it), although it still doesn't stop the bot from returning an invalid move in the first place.  This has an issue with [magic numbers](https://en.wikipedia.org/wiki/Magic_number_(programming)#Unnamed_numerical_constants), however.  There's no clear reason why 0 through 4 has been chosen, or why (for instance) "rock" would correspond with 0 or 1 would beat 0 and 4 but lose to 3 and 2.  It's less applicable in this case, but it also makes the code a little harder to expand, if we want to switch to regular Rock Paper Scissors (or some exotic 7-move variant), we have to remember every place we hardcoded into 0 through 4.

We're programming in Java, so maybe we could use a class for this.  Specifically, what about a class that has an attribute, let's say `value`, and a constructor that makes sure the given value is valid.  Something like this:

```java
public class Play {
	private String value;

	public Play(String value){
		if(!value.equals("rock") && !value.equals("paper") && !value.equals("scissors") && !value.equals("lizard") && !value.equals("spock")){
			throw new IllegalArgumentException("Invalid value: " + value);
		}

		this.value = value;
	}
}
```

Now we can say that `shoot()` has to return an instance of `Play`, and we've guaranteed that the bot will give a value that's valid (or else we'll get an error).  We could also define a method that compares two `Play`s, possibly with a nested `HashMap` structure, so we can always determine the winner.

The downside here is that it's somewhat wordy (all those `!value.equals()`s!).  Also, you might notice a potential efficiency; specifically, since every "rock" play should be the same, it doesn't make much sense for bots to always instantiate new versions of those plays, rather than creating a single instance that every bot refers to.  We can rewrite our `Play` class from before to include a few instances of specific plays as `static` fields:

```java
public class Play {
	private String value;

	public static final Play ROCK = new Play("rock");
	public static final Play PAPER = new Play("paper");
	public static final Play SCISSORS = new Play("scissors");
	public static final Play LIZARD = new Play("lizard");
	public static final Play SPOCK = new Play("spock");

	public Play(String value){
		if(!value.equals("rock") && !value.equals("paper") && !value.equals("scissors") && !value.equals("lizard") && !value.equals("spock")){
			throw new IllegalArgumentException("Invalid value: " + value);
		}

		this.value = value;
	}
}
```

...and at this point, we've basically re-implemented an Enum.  It's a special class that has a predefined list of values that we can reference.  For example, we can say that the `shoot()` method has to return a `Play` object, and it will have to be one of the five `Play`s that we've selected; anything else (including a `String` like `"rock"`) will be an error.  (The built-in Enum has an additional feature we didn't implement that prevents anyone else from instantiating a new instance, so you can *only* use the pre-defined list.)

Docs on Enums are [here](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html); I also liked [this guide](http://www.geeksforgeeks.org/enum-in-java/) from Geeks for Geeks.

**tl;dr Instead of the strings "rock", "paper", *et cetera*, use `Play.ROCK`, `Play.PAPER`, *et cetera*.**

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
}
```