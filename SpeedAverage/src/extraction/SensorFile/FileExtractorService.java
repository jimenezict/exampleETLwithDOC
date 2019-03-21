package extraction.SensorFile;

import java.util.List;
import java.util.Map;

import extraction.IExtractorService;
import pojo.SensorRegister;

public class FileExtractorService implements IExtractorService{
	
	FileReadMasterAgent f;

	@Override
	public void extractorConfigurer(Map<String, Object> parameters) {
		f = new FileReadMasterAgent((int) parameters.get("threads"), parameters.get("filepatern").toString());
	}

	@Override
	public List<SensorRegister> getAndCleanBuffer() {
		return f.getAndCleanBuffer();
	}

	@Override
	public void switchOff() {
		f.switchOff();		
	}

}
