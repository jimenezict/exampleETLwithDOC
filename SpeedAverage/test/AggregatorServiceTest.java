import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import pojo.SpeedRegister;
import transformation.TransformationService;

public class AggregatorServiceTest {
	List<SpeedRegister> spTest = new ArrayList<SpeedRegister>();
	List<SpeedRegister> spTest2 = new ArrayList<SpeedRegister>();
	
	@Before public void initialize() {
		spTest = new ArrayList<SpeedRegister>();
		spTest.add(new SpeedRegister(1, 200, "filesource0"));
		spTest.add(new SpeedRegister(2, 210, "filesource0"));
		spTest.add(new SpeedRegister(2, 205, "filesource0"));
		spTest.add(new SpeedRegister(3, 200, "filesource0"));
		spTest.add(new SpeedRegister(3, 190, "filesource0"));
		
		spTest2 = new ArrayList<SpeedRegister>();		
		spTest2.add(new SpeedRegister(2, 205, "filesource0"));
		spTest2.add(new SpeedRegister(3, 200, "filesource0"));
		spTest2.add(new SpeedRegister(1, 200, "filesource0"));
		spTest2.add(new SpeedRegister(3, 190, "filesource0"));
		spTest2.add(new SpeedRegister(2, 210, "filesource0"));
	}
	
	@Test
	public void nextRoundListTest(){
		List<SpeedRegister> spNextRoundTest = TransformationService.nextRoundList(spTest,2);
		assertEquals(spNextRoundTest.size(),2);
	}
	
	@Test
	public void mainFrameListTest(){
		List<SpeedRegister> spMainFrameTest = TransformationService.mainFrameList(spTest,2);
		assertEquals(spMainFrameTest.size(),3);
	}
	
	@Test
	public void getAveragesOnPeriodTest(){
		Map<Long,Double> spAverageTest = TransformationService.getAveragesByTimestamp(spTest);
		assertTrue(spAverageTest.get(1L) == 200);
		assertTrue(spAverageTest.get(2L) == 207.5);
		assertTrue(spAverageTest.get(3L) == 195);
	}
	
	@Test
	public void getAveragesOnPeriodDisordedTest(){
		Map<Long,Double> spAverageTest = TransformationService.getAveragesByTimestamp(spTest2);
		assertTrue(spAverageTest.get(1L) == 200);
		assertTrue(spAverageTest.get(2L) == 207.5);
		assertTrue(spAverageTest.get(3L) == 195);
	}

}
