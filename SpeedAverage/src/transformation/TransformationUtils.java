package transformation;

import static java.util.stream.Collectors.toList;

import java.util.List;

import pojo.Register;

public class TransformationUtils {
	
	public static List<? extends Register> nextRoundList(List<? extends Register> mainList, long initialTimestamp) {
		List<Register> nextRoundList = mainList
				.stream()
				.filter(register -> register.getTimestamp() > initialTimestamp)
				.collect(toList());		
		return nextRoundList;
	}
	
	public static List<? extends Register> mainFrameList(List<? extends Register> mainList, long initialTimestamp) {
		List<Register> mainListToReturn = mainList
				.stream()
				.filter(speedRegister -> speedRegister.getTimestamp() <= initialTimestamp)
				.collect(toList());
		return mainListToReturn;
	}
}
