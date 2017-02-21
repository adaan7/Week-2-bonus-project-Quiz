package ba.adan.quizapp.account;

public class Account implements Comparable<Account> {

	private String username;
	private String password;
	private boolean isAdmin;
	private int highScore;

	public Account() {
		username = "";
		password = "";
		isAdmin = false;
		highScore = 0;
	}

	public Account(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		highScore = 0;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int compareTo(Account account) {
		return username.compareTo(account.getUsername());
	}

	@Override
	public String toString() {
		return "Username: " + this.username + ", High score: " + this.highScore;
	}

}
