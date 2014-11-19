package ibyscus.standup.stup;

public class Timer {
	private int time;
	
	/****************************
	 * Constructeurs
	 ****************************/
	public Timer() {
		setTime(30);
	}
	
	public Timer(int i) {
		setTime(i);
	}
	
	/*****************************
	 * Getteurs
	 *****************************/
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
}
