package RPSLS;

import java.util.Comparator;

public class Tuple<Play, Integer> implements Comparable{
	private Play play;
	private Integer num;
	
	public Tuple() {}
	
	public Tuple(Play play, Integer num) {
		this.play = play;
		this.num = num;
	}
	
		public int compareTo(Tuple play2) {
			return ((int) num - (int) play2.getNum());
		}

	public Play getPlay() {
		return play;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
