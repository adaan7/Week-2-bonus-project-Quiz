package ba.adan.quizapp.quiz;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ScoreRecord implements Comparable<ScoreRecord> {
	
	private String username;
	private int score;
	private Calendar calendar;

	public ScoreRecord() {
		username = "";
		score = 0;
		calendar = new GregorianCalendar();
	}

	public ScoreRecord(String username, int score, Calendar calendar) {
		this.username = username;
		this.score = score;
		this.calendar = calendar;
	}

	public String getUsername() {
		return username;
	}

	public int getScore() {
		return score;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	@Override
	public int compareTo(ScoreRecord scoreRecord) {
		if (score > scoreRecord.getScore()) {
			return 1;
		} else if (score == scoreRecord.getScore()) {
			return 0;
		} else {
			return -1;
		}
	}

}
