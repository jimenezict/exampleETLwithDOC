package transformation;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pojo.SpeedRegister;

public class AverageTransformationService implements ITransformationService {

	public Map<Long, Double> applyTransformation(List<SpeedRegister> mainList) {
		Map<Long, Double> averageRoundList = mainList
				.stream()
				.sorted(Comparator.comparing(SpeedRegister::getTimestamp))
				.collect(Collectors.groupingBy(SpeedRegister::getTimestamp,Collectors.averagingDouble(SpeedRegister::getVelocity)));
		return averageRoundList;		
	}

	public List<SpeedRegister> nextRoundList(List<SpeedRegister> mainList, long initialTimestamp) {
		List<SpeedRegister> nextRoundList = mainList
				.stream()
				.filter(speedRegister -> speedRegister.getTimestamp() > initialTimestamp)
				.collect(toList());		
		return nextRoundList;
	}
	
	public List<SpeedRegister> mainFrameList(List<SpeedRegister> mainList, long initialTimestamp) {
		List<SpeedRegister> mainListToReturn = mainList
				.stream()
				.filter(speedRegister -> speedRegister.getTimestamp() <= initialTimestamp)
				.collect(toList());
		return mainListToReturn;
	}

}