package project_227;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Scanner;

public class processReader {
	Queue<process> jobQueue;
	public processReader(Queue<process> jobQueue) {
		this.jobQueue = jobQueue;
	}
    public void readTextFile() {
	synchronized (jobQueue) {
		try {
			File textfile = new File("C:\\Users\\azouz\\OneDrive\\Desktop\\job2.txt");
			Scanner input =new Scanner(textfile);
			while(input.hasNextLine()){
				input.nextLine();
				String line = input.nextLine();
				String[] parts = line.split(",");
					if(!parts[0].isEmpty()){
						int prId =Integer.parseInt(parts[0].trim());
						if(!parts[1].isEmpty()){
							int brust =Integer.parseInt(parts[1].trim());
							if(!parts[2].isEmpty()){
								int memory =Integer.parseInt(parts[2].trim());
								if(memory<=Algorithms.mainMemory){
									jobQueue.add(new process(prId,brust,memory));
									Algorithms.processCounter++;
								}
								}else{
								System.out.println("Erorr: memory is empty");
							}
						}else{
							System.out.println("Erorr: process burst time is empty");
						}
					}else{
						System.out.println("Erorr: process id is empty");
					}			}
			input.close();
		}catch(Exception e){e.printStackTrace();}		
	}}
   
}
