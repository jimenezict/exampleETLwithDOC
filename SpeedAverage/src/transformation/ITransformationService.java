package transformation;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pojo.SpeedRegister;

public interface ITransformationService {

	public Map<Long, Double> applyTransformation(List<SpeedRegister> mainList);	

	public List<SpeedRegister> nextRoundList(List<SpeedRegister> mainList, long initialTimestamp);
	
	public List<SpeedRegister> mainFrameList(List<SpeedRegister> mainList, long initialTimestamp);

}