package ba.adan.quizapp.quiz;

public class Answer {

	private String answer;
	private boolean correctAnswer;

	public Answer() {
		answer = "";
		correctAnswer = false;
	}

	public Answer(String answer) {
		this.answer = answer;
		correctAnswer = false;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

}
