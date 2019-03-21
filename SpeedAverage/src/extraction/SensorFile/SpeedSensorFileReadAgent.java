package extraction.SensorFile;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import pojo.SensorRegister;

public class SpeedSensorFileReadAgent implements IFileReadAgentService{

	@Override
	public List<String> fileReading(String fileLocation, long startLine) {
		List<SensorRegister> sensorList;
		try (Stream<String> lines = Files.lines(Paths.get(fileLocation + ".csv"))) {
			return lines.skip(startLine).collect(toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SensorRegister> objectParser(List<String> fileReading,String sensorname) {
		List<SensorRegister> list = new ArrayList<SensorRegister>();

		fileReading
		.stream()
		.forEach(line -> {
			if(line!=null && line.length() >0 && line.contains(",")) {
				long timestamp = new Long(line.split(",")[0]);
				double velocity = new Double(line.split(",")[1]);
				list.add(new SensorRegister(timestamp,velocity, sensorname));
			}	
		});
		return list;
	}

	@Override
	public List<SensorRegister> objectParser(List<String> fileReading) {
		return objectParser(fileReading,"UNESPECIFIED");
	}

}