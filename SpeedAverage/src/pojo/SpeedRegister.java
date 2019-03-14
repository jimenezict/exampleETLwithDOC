package pojo;

import java.sql.Timestamp;

public class SpeedRegister {
	
	private long timestamp;
	private double velocity;
	private String fileSource;	
	
	public SpeedRegister(long l, double i, String filesource){
		this.timestamp = l;
		this.velocity = i;
		this.fileSource = filesource;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public Double getVelocity() {
		return velocity;
	}
	
	public String getFileSource() {
		return fileSource;
	}
	
	public String toString() {
		return fileSource + ':' + velocity + ':' + timestamp;	
	}
	
}
