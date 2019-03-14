import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SensorThread extends Thread {
	
	private String sensorFileName;
	FileOutputStream fos;
	int i=10;

	SensorThread(String sensorFileName){
		this.sensorFileName = sensorFileName;
	}
	
	private double noisedvelocity() {
		return 190 + Math.random()*20;
	}	
	
	public void run() {
		try {
			fos = new FileOutputStream(sensorFileName, false);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
				
		while(i>0) {
			try {
				fos.write((System.currentTimeMillis()/1000 + ";" + noisedvelocity() + "\r\n").getBytes());
				sleep(1000);
				i--;
			} catch (IOException e1) {
				e1.printStackTrace();
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}
