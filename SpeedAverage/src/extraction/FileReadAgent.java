package extraction;

import pojo.SpeedRegister;

public class FileReadAgent extends Thread{
	
	FileReadMasterAgent masterAgent;
	String fileLocation;
	
	FileReadAgent(String fileLocation, FileReadMasterAgent masterAgent,int pos) {
		this.masterAgent = masterAgent;
		this.fileLocation = fileLocation;
	}	
		
	public void run() {
		
		while(true) {
			masterAgent.addRegister(new SpeedRegister(System.currentTimeMillis(),200,fileLocation));
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
