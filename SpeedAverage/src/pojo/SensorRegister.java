package pojo;

import java.sql.Timestamp;

public class SensorRegister extends Register{
	
	private double velocity;
	private String fileSource;	
	
	public SensorRegister(long l, double i, String filesource){
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
