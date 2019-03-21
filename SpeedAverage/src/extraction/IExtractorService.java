package extraction;

import java.util.List;
import java.util.Map;

import pojo.Register;

public interface IExtractorService {
	
	public void extractorConfigurer(Map<String,Object> parameters);
	
	public List<? extends Register> getAndCleanBuffer();
	
	public void switchOff();

}
