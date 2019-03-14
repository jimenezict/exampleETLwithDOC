import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import extraction.FileReadMasterAgent;
import load.LoadService;
import pojo.SpeedRegister;
import transformation.TransformationService;

public class SpeedAverage {

	public static void main(String[] args) {
		
		List<SpeedRegister> toProcessRegisters = new ArrayList<SpeedRegister>();
		List<SpeedRegister> nextRoundRegisters = new ArrayList<SpeedRegister>();
		long initialTimestamp = System.currentTimeMillis();
		final int numberOfAgents = 2;
		FileReadMasterAgent m = new FileReadMasterAgent(numberOfAgents);
		
		do {
			List<SpeedRegister> actualRoundRegisters = m.getAndCleanSpeedRegisterBuffer();
			actualRoundRegisters.addAll(nextRoundRegisters);
			toProcessRegisters = TransformationService.mainFrameList(actualRoundRegisters, initialTimestamp);
			nextRoundRegisters = TransformationService.nextRoundList(actualRoundRegisters, initialTimestamp);
			
			try {
				TimeUnit.SECONDS.sleep(10);
				initialTimestamp = initialTimestamp + 10000;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}while(true);
	}		
}
