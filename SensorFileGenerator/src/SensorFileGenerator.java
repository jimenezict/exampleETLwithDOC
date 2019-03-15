
public class SensorFileGenerator {

	public static void main(String[] args) {
		String filePatern = "../SpeedAverage/SpeedSensor";
		SensorThread sensor_generator_1 = new SensorThread(filePatern + "1.csv" );
		SensorThread sensor_generator_2 = new SensorThread(filePatern + "2.csv" );
		
		sensor_generator_1.start();
		sensor_generator_2.start();
	}
}
