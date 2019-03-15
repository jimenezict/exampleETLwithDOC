package extraction;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import pojo.SpeedRegister;

public class FileReadAgent extends Thread{
	
	FileReadMasterAgent masterAgent;
	String fileLocation;
	long startLine = 0;
	
	FileReadAgent(String fileLocation, FileReadMasterAgent masterAgent) {
		this.masterAgent = masterAgent;
		this.fileLocation = fileLocation;
	}
	
	private void addNewRegister(String line) {
		if(line!=null && line.length() >0 && line.contains(",")) {
			long timestamp = new Long(line.split(",")[0]);
			double velocity = new Double(line.split(",")[1]);
			masterAgent.addRegister(new SpeedRegister(timestamp,velocity,fileLocation));
		}else {
			System.out.println("Not valid line detected");
		}
		
	}
	
	private void randomFunction() throws InterruptedException {
		masterAgent.addRegister(new SpeedRegister(System.currentTimeMillis(),190 + Math.random()*20,fileLocation));
		sleep(1000);
	}
	
	private void incrementalFileReading() throws IOException, InterruptedException {
		try (Stream<String> lines = Files.lines(Paths.get(fileLocation + ".csv"))) {
		    List<String> latestLines = lines.skip(startLine).collect(toList());
		    startLine =  startLine + latestLines.stream().count();
		    latestLines.stream().forEach(line -> addNewRegister(line));
		    sleep(1000);
		}
	}	

	public void run() {		
		while(true) {
			try {
				incrementalFileReading();
			} catch (InterruptedException | IOException e) {
				System.out.println("Error on the Read Agent");
				e.printStackTrace();
			}
		}		
	}
}
