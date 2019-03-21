package extraction.SensorFile;

import java.util.List;

import pojo.SensorRegister;

public class FileReadAgent extends Thread{
	
	FileReadMasterAgent masterAgent;
	String fileLocation;
	long startLine = 0;
	boolean running = true;
	IFileReadAgentService fileReadAgentService;
	
	FileReadAgent(String fileLocation, FileReadMasterAgent masterAgent) {
		this.masterAgent = masterAgent;
		this.fileLocation = fileLocation;
		fileReadAgentService = new SpeedSensorFileReadAgent();
	}

	public void switchOff() {
		running = false;
	}
	
	public void run() {		
		while(running) {
			try {				
				List<SensorRegister> parsedObject = fileReadAgentService
						.objectParser(fileReadAgentService.fileReading(fileLocation,startLine),fileLocation);
				startLine = startLine + parsedObject.size();
				masterAgent.addRegisters(parsedObject);				
				sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Error on the Read Agent");
				e.printStackTrace();
			}
		}		
		System.out.println("Finalizing Thread " + fileLocation);
	}
}
