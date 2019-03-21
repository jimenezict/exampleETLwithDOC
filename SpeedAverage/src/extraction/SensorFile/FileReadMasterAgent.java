package extraction.SensorFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import pojo.Register;
import pojo.SensorRegister;

public class FileReadMasterAgent {
	
	private Vector<SensorRegister> registerBuffer;
	private List<FileReadAgent> agentList;
	private int numberOfAgents;
	
	public FileReadMasterAgent(int numberOfAgents, String namepatern) {
		this.numberOfAgents = numberOfAgents;
		agentList = new ArrayList<FileReadAgent>();
		registerBuffer = new Vector<SensorRegister>();
		for(int i=1;i<numberOfAgents+1;i++) {
			agentList.add(new FileReadAgent(namepatern + i,this));
			agentList.get(i-1).start();
		}
	}

	public synchronized List<SensorRegister> getAndCleanBuffer() {
		System.out.println("Master Agent providing data....:" + registerBuffer.size());
		List<SensorRegister> registersToReturn = new Vector<SensorRegister>(); 
		registersToReturn.addAll(registerBuffer);
		registerBuffer.clear();
		return registersToReturn;
	}	

	public void switchOff() {
		for(int i=0;i<numberOfAgents;i++) {
			agentList.get(i).switchOff();
		}
	}

	public void addRegisters(List<SensorRegister> parsedObject) {
		this.registerBuffer.addAll(parsedObject);		
	}
}
