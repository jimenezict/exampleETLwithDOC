package extraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import pojo.SpeedRegister;

public class FileReadMasterAgent {
	
	private Vector<SpeedRegister> speedRegisterBuffer;
	private List<FileReadAgent> agentList;
	
	public FileReadMasterAgent(int numberOfAgents) {
		agentList = new ArrayList<FileReadAgent>();
		speedRegisterBuffer = new Vector<SpeedRegister>();
		for(int i=0;i<numberOfAgents;i++) {
			agentList.add(new FileReadAgent("SpeedSensor" + i,this,i));
			agentList.get(i).start();
		}
	}

	public synchronized List<SpeedRegister> getAndCleanSpeedRegisterBuffer() {
		List<SpeedRegister> registersToReturn = new Vector<SpeedRegister>(); 
		registersToReturn.addAll(speedRegisterBuffer);
		speedRegisterBuffer.clear();
		return registersToReturn;
	}

	public synchronized void addRegister(SpeedRegister speedRegisterSample) {
		this.speedRegisterBuffer.add(speedRegisterSample);
	}

}
