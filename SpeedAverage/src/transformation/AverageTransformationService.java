package transformation;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pojo.Register;
import pojo.SensorRegister;

public class AverageTransformationService implements ITransformationService {

	@Override
	public Map<Long, Double> applyTransformation(List<? extends Register> toProcessList) {
		Map<Long, Double> averageRoundList =((List<SensorRegister>) toProcessList)
				.stream()
				.sorted(Comparator.comparing(Register::getTimestamp))
				.collect(Collectors.groupingBy(Register::getTimestamp,Collectors.averagingDouble(SensorRegister::getVelocity)));
		return averageRoundList;		
	}

	

}