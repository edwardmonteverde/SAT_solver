package A1Sat;

public class TimerMonteVerde {
	
	private float duration;
	private float start;
	private float stop;

	public void start() {
		this.start = System.currentTimeMillis();
	}

	public void stop() {
		this.stop = System.currentTimeMillis();
	}

	public double getDuration() {
		duration = this.stop - this.start;
		return duration;
	}
	
	public void printTime(){
		System.out.println("Time is " + duration + " seconds.");
	}

}