package load;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import pojo.OutputAverageRegister;
import pojo.SpeedRegister;

public class FileLoadAgent extends Thread{
	private List<OutputAverageRegister> output = new ArrayList<OutputAverageRegister>();
	private BufferedWriter writer;
	
	FileLoadAgent(List<OutputAverageRegister> output,int i){
		this.output = output;
		try {
			writer = new BufferedWriter(new FileWriter("AverageSpeed_"+i));
			System.out.println("Ready for writing in: AverageSpeed_" + i + " with size " + output.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		output.stream()
		.sorted(Comparator.comparing(OutputAverageRegister::getTimestamp))
		.forEach(register -> {
			try {
				writer.write(register.getTimestamp()+","+register.getVelocity()+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
