package extraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import pojo.SpeedRegister;

public class FileReadMasterAgent {
	
	private Vector<SpeedRegister> speedRegisterBuffer;
	private List<FileReadAgent> agentList;
	private int numberOfAgents;
	
	public FileReadMasterAgent(int numberOfAgents, String namepatern) {
		this.numberOfAgents = numberOfAgents;
		agentList = new ArrayList<FileReadAgent>();
		speedRegisterBuffer = new Vector<SpeedRegister>();
		for(int i=1;i<numberOfAgents+1;i++) {
			agentList.add(new FileReadAgent(namepatern + i,this));
			agentList.get(i-1).start();
		}
	}

	public synchronized List<SpeedRegister> getAndCleanSpeedRegisterBuffer() {
		System.out.println("Master Agent providing data....:" + speedRegisterBuffer.size());
		List<SpeedRegister> registersToReturn = new Vector<SpeedRegister>(); 
		registersToReturn.addAll(speedRegisterBuffer);
		speedRegisterBuffer.clear();
		return registersToReturn;
	}

	public synchronized void addRegister(SpeedRegister speedRegisterSample) {
		this.speedRegisterBuffer.add(speedRegisterSample);
	}

	public void switchOff() {
		for(int i=0;i<numberOfAgents;i++) {
			agentList.get(i).switchOff();
		}
	}
}
