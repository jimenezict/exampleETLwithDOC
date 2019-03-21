package extraction.SensorFile;

import java.util.List;
import java.util.stream.Stream;

import pojo.SensorRegister;

public interface IFileReadAgentService {
	
	List<String> fileReading(String fileLocation, long startLine);

	List<SensorRegister> objectParser(List<String> fileReading);

	List<SensorRegister> objectParser(List<String> fileReading,String sensorname);

}
