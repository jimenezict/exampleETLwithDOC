import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import extraction.FileExtractorService;
import extraction.IExtractorService;
import load.FileLoadService;
import load.ILoadService;
import pojo.SpeedRegister;
import transformation.AverageTransformationService;
import transformation.ITransformationService;

public class SpeedAverage {
	
	static ILoadService loadService;
	static IExtractorService extractorService;
	static ITransformationService transformationService;

	public static void main(String[] args) throws InterruptedException {
		
		List<SpeedRegister> toProcessRegisters = new ArrayList<SpeedRegister>();
		List<SpeedRegister> nextRoundRegisters = new ArrayList<SpeedRegister>();
		long initialTimestamp = System.currentTimeMillis()/1000;		
		
		initialize();
		
		int i = 0;
		TimeUnit.SECONDS.sleep(3);
		
		do {
			List<SpeedRegister> actualRoundRegisters = extractorService.getAndCleanSpeedRegisterBuffer();
			actualRoundRegisters.addAll(nextRoundRegisters);
			toProcessRegisters = transformationService.mainFrameList(actualRoundRegisters, initialTimestamp);
			nextRoundRegisters = transformationService.nextRoundList(actualRoundRegisters, initialTimestamp);
			System.out.println("Loop Number: " + i + 
					", Actual Number: " + actualRoundRegisters.size() +
					", To Process Registers: " + toProcessRegisters.size() +
					", Next RoundRegisters: " + nextRoundRegisters.size());
			loadService.save(transformationService.applyTransformation(toProcessRegisters),i);
			TimeUnit.SECONDS.sleep(10);
			initialTimestamp = initialTimestamp + 10;
			i++;
		}while(true);
	}

	private static void initialize() {
		loadService = new FileLoadService();
		transformationService = new AverageTransformationService();
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("threads", 2);
		parameters.put("filepatern", "SpeedSensor");
		extractorService  = new FileExtractorService();		
		extractorService.extractorConfigurer(parameters);
	}		
}