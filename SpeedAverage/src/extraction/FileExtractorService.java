package extraction;

import java.util.List;
import java.util.Map;

import pojo.SpeedRegister;

public class FileExtractorService implements IExtractorService{
	
	FileReadMasterAgent f;

	@Override
	public void extractorConfigurer(Map<String, Object> parameters) {
		f = new FileReadMasterAgent((int) parameters.get("threads"), parameters.get("filepatern").toString());
	}

	@Override
	public List<SpeedRegister> getAndCleanSpeedRegisterBuffer() {
		return f.getAndCleanSpeedRegisterBuffer();
	}

}
