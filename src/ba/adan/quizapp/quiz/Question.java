package ba.adan.quizapp.quiz;

import java.util.ArrayList;

public class Question implements Comparable<Question> {

	private String question;
	private ArrayList<Answer> answerList;

	public Question() {
		question = "";
		answerList = new ArrayList<>();
	}

	public Question(String question, ArrayList<Answer> answerList) {
		this.question = question;
		this.answerList = answerList;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(ArrayList<Answer> answerList) {
		this.answerList = answerList;
	}

	@Override
	public int compareTo(Question question1) {
		return question.compareTo(question1.getQuestion());
	}

}
