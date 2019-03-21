package transformation;

import java.util.List;
import java.util.Map;

import pojo.Register;

public interface ITransformationService {

	public Map<Long, Double> applyTransformation(List<? extends Register> toProcessList);	

}