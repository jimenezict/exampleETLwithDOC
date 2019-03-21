import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import extraction.IExtractorService;
import extraction.SensorFile.FileExtractorService;
import load.FileLoadService;
import load.ILoadService;
import pojo.Register;
import transformation.AverageTransformationService;
import transformation.ITransformationService;
import transformation.TransformationUtils;

public class SpeedAverage {
	
	static ILoadService loadService;
	static IExtractorService extractorService;
	static ITransformationService transformationService;

	public static void main(String[] args) throws InterruptedException {
		
		List<? extends Register> toProcessRegisters = new ArrayList<Register>();
		List<? extends Register> nextRoundRegisters = new ArrayList<Register>();
		long initialTimestamp = System.currentTimeMillis()/1000;
		Integer iterations = 3;
		
		initialize();
		
		int i = 0;
		TimeUnit.SECONDS.sleep(3);
		
		do {
			List<? extends Register> actualRoundRegisters = extractorService.getAndCleanBuffer();
			toProcessRegisters = TransformationUtils.mainFrameList(actualRoundRegisters, initialTimestamp);
			nextRoundRegisters = TransformationUtils.nextRoundList(actualRoundRegisters, initialTimestamp);
			System.out.println("Loop Number: " + i + 
					", Actual Number: " + actualRoundRegisters.size() +
					", To Process Registers: " + toProcessRegisters.size() +
					", Next RoundRegisters: " + nextRoundRegisters.size());
			loadService.save(transformationService.applyTransformation(toProcessRegisters),i);
			TimeUnit.SECONDS.sleep(10);
			initialTimestamp = initialTimestamp + 10;
			i++;
		}while(i<iterations);
		
		switchOff();
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
	
	private static void switchOff() {
		extractorService.switchOff();
		loadService.switchOff();
	}	
}