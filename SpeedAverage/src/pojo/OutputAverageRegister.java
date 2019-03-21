package pojo;

public class OutputAverageRegister extends Register{
	
	private double velocity;
	
	public OutputAverageRegister(long l, double i){
		this.timestamp = l;
		this.velocity = i;
	}
	
	public Double getVelocity() {
		return velocity;
	}
	
	public String toString() {
		return velocity + ";" + timestamp;	
	}
}
