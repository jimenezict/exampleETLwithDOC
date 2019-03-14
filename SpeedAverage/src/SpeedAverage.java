import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import extraction.FileReadMasterAgent;
import load.LoadService;
import pojo.SpeedRegister;
import transformation.TransformationService;

public class SpeedAverage {

	public static void main(String[] args) throws InterruptedException {
		
		List<SpeedRegister> toProcessRegisters = new ArrayList<SpeedRegister>();
		List<SpeedRegister> nextRoundRegisters = new ArrayList<SpeedRegister>();
		long initialTimestamp = System.currentTimeMillis();

		final int numberOfAgents = 2;
		FileReadMasterAgent m = new FileReadMasterAgent(numberOfAgents);
		int i = 0;
		TimeUnit.SECONDS.sleep(3);
		
		do {
			List<SpeedRegister> actualRoundRegisters = m.getAndCleanSpeedRegisterBuffer();
			actualRoundRegisters.addAll(nextRoundRegisters);
			toProcessRegisters = TransformationService.mainFrameList(actualRoundRegisters, initialTimestamp);
			nextRoundRegisters = TransformationService.nextRoundList(actualRoundRegisters, initialTimestamp);
			System.out.println("Loop Number: " + i + 
					", Actual Number: " + actualRoundRegisters.size() +
					", To Process Registers: " + toProcessRegisters.size() +
					", Next RoundRegisters: " + nextRoundRegisters.size());
			LoadService.saveFile(TransformationService.getAveragesByTimestamp(toProcessRegisters),i);
			TimeUnit.SECONDS.sleep(10);
			initialTimestamp = initialTimestamp + 10000;
			i++;
		}while(true);
	}		
}
