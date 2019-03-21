import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import pojo.SensorRegister;
import transformation.AverageTransformationService;
import transformation.TransformationUtils;

public class AggregatorServiceTest {
	List<SensorRegister> spTest = new ArrayList<SensorRegister>();
	List<SensorRegister> spTest2 = new ArrayList<SensorRegister>();
	AverageTransformationService agg = new AverageTransformationService();
	
	@Before public void initialize() {
		spTest = new ArrayList<SensorRegister>();
		spTest.add(new SensorRegister(1, 200, "filesource0"));
		spTest.add(new SensorRegister(2, 210, "filesource0"));
		spTest.add(new SensorRegister(2, 205, "filesource0"));
		spTest.add(new SensorRegister(3, 200, "filesource0"));
		spTest.add(new SensorRegister(3, 190, "filesource0"));
		
		spTest2 = new ArrayList<SensorRegister>();		
		spTest2.add(new SensorRegister(2, 205, "filesource0"));
		spTest2.add(new SensorRegister(3, 200, "filesource0"));
		spTest2.add(new SensorRegister(1, 200, "filesource0"));
		spTest2.add(new SensorRegister(3, 190, "filesource0"));
		spTest2.add(new SensorRegister(2, 210, "filesource0"));
	}
	
	@Test
	public void nextRoundListTest(){
		List<SensorRegister> spNextRoundTest = (List<SensorRegister>) TransformationUtils.nextRoundList(spTest,2);
		assertEquals(spNextRoundTest.size(),2);
	}
	
	@Test
	public void mainFrameListTest(){
		List<SensorRegister> spMainFrameTest = (List<SensorRegister>) TransformationUtils.mainFrameList(spTest,2);
		assertEquals(spMainFrameTest.size(),3);
	}
	
	@Test
	public void getAveragesOnPeriodTest(){
		Map<Long,Double> spAverageTest = agg.applyTransformation(spTest);
		assertTrue(spAverageTest.get(1L) == 200);
		assertTrue(spAverageTest.get(2L) == 207.5);
		assertTrue(spAverageTest.get(3L) == 195);
	}
	
	@Test
	public void getAveragesOnPeriodDisordedTest(){
		Map<Long,Double> spAverageTest = agg.applyTransformation(spTest2);
		assertTrue(spAverageTest.get(1L) == 200);
		assertTrue(spAverageTest.get(2L) == 207.5);
		assertTrue(spAverageTest.get(3L) == 195);
	}

}
