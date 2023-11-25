package project_227;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class main {


		public static void main(String[] args) {
			Queue<process> jobQueue = new LinkedList<>();

			Algorithms algo = new Algorithms(jobQueue);
			processReader PR = new processReader(jobQueue);

			Thread readFileThread = new Thread(new Runnable() { 
				@Override
				public void run() {
					PR.readTextFile(); 

				}
			});

			 Scanner scanner = new Scanner(System.in);
				System.out.print(
						"CHOOSE ONE OF THE FOLLOWING:\n1- First come first serve(FCFS)\n2- Shortest-Job-First (SJF)\n3- Round-Robin with time slice = 3 (RR-3)\n4- Round-Robin with time slice = 5 (RR-5) ==> ");
				try {
					int algorithmChoice = scanner.nextInt();

					Thread algorithmThread = null;
					switch (algorithmChoice) {
						case 1:
							algorithmThread = new Thread(new Runnable() {
								@Override
								public void run() {
									algo.FCFS();
								}
							});
							break;
						case 2:
							algorithmThread = new Thread(new Runnable() {
								@Override
								public void run() {
									algo.SJF();
								}	});
							break;
						case 3:
							algorithmThread = new Thread(new Runnable() {
								@Override
								public void run() {
									algo.RR(3);
								}
							});
							break;
						case 4:
							algorithmThread = new Thread(new Runnable() {
								@Override
								public void run() {
									algo.RR(5);
								}
							});
							break;
						default:
							System.out.println("Invalid choice. Please choose 1, 2, or 3.");
							return;
					}

					readFileThread.start();
					try {
						readFileThread.join(); //to wait for the reading to finish
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					algorithmThread.start();
				} catch (Exception e) {
					System.out.println("Please enter an integer");
					
				}

			}
	}

