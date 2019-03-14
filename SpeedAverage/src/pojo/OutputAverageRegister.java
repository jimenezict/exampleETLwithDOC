package pojo;

public class OutputAverageRegister {
	
	private long timestamp;
	private double velocity;
	
	public OutputAverageRegister(long l, double i){
		this.timestamp = l;
		this.velocity = i;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public Double getVelocity() {
		return velocity;
	}
	
	public String toString() {
		return velocity + ";" + timestamp;	
	}
}
