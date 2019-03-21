package extraction;

import java.util.List;
import java.util.Map;

import pojo.SpeedRegister;

public interface IExtractorService {
	
	public void extractorConfigurer(Map<String,Object> parameters);
	
	public List<SpeedRegister> getAndCleanSpeedRegisterBuffer();

}
