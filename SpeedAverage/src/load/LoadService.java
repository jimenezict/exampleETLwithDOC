package load;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pojo.OutputAverageRegister;

public class LoadService {

	public static void saveFile(Map<Long, Double> averagesOnPeriod, int i) {
		List<OutputAverageRegister> outputAverageRegisterList = new ArrayList<OutputAverageRegister>();
		
		for (Entry<Long, Double> entry : averagesOnPeriod.entrySet())
			outputAverageRegisterList.add(new OutputAverageRegister(entry.getKey(),entry.getValue()));
		
		(new LoadAgent(outputAverageRegisterList,i)).start();
	}


}
