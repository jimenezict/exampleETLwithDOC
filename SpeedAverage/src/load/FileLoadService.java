package load;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pojo.OutputAverageRegister;

public class FileLoadService implements ILoadService{

	public void save(Map<Long, Double> averagesOnPeriod, int i) {
		List<OutputAverageRegister> outputAverageRegisterList = new ArrayList<OutputAverageRegister>();
		
		for (Entry<Long, Double> entry : averagesOnPeriod.entrySet())
			outputAverageRegisterList.add(new OutputAverageRegister(entry.getKey(),entry.getValue()));
		
		(new FileLoadAgent(outputAverageRegisterList,i)).start();
	}
	
	public void switchOff() {
		System.out.println("Switching Off File Load");
	}

}
