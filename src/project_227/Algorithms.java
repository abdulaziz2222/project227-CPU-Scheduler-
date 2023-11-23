package project_227;

import java.util.LinkedList;
import java.util.Queue;


public class Algorithms {
	Queue<process> jobQueue;
	Queue<process> readyQueue = new LinkedList<>();
	public static int mainMemory = 8192;
	int waitingTime = 0;
	int turnAroundTime = 0;
	static int processCounter = 0;

	
	public Algorithms(Queue<process> jobQueue)
	{
		this.jobQueue = jobQueue;
	}

	
	public static void GanttChart(process p, int firstNum, int NextNum)
	{
		System.out.println("------------------------------------");
		System.out.format("%-1s %1s %1s %5s %5s %5s %10s" , "|" ,  " p" + p.getId() , " | " , "" + firstNum , "-->" , "" + NextNum , "| \n");
		
	}

	
	public static void Table(Queue<process> temp, int counter) 
	{
		process[] p = temp.toArray(new process[temp.size()]);
		System.out.println("------------------------------------");

		System.out.println("Proccess ID\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");

		int totalWT = 0;
		int totalTA = 0;
		
		for (int i = 0; i < counter; i++)
		{
			
			totalWT += p[i].getWaitingTime();
			totalTA += p[i].getTurnAroundTime();

			System.out.println(p[i].getId() + "\t\t" + p[i].getArrivalTime() + "\t\t"
					+ p[i].getBurstTime() + "\t\t" + p[i].getWaitingTime() + "\t\t"
					+ p[i].getTurnAroundTime());
		}

		System.out.println("--------------------------------------------------------------------");
		System.out.println("Calculate Average Waiting Time: " + (totalWT / (double) counter));
		System.out.println("Calculate Average Turnaround Time: " + (totalTA / (double) counter));

	}

	
	public void FCFS()
	{
		synchronized (jobQueue)
		{
			int maxSize = 0;
			Queue<process> temp = new LinkedList<>(); //we need a temp queue to save all the processes in it to print them in table method (we can't use readyQueue as it is clearing each iteration)
			int totalTime = 0;

			while (!jobQueue.isEmpty() || !readyQueue.isEmpty())
			{
				while (!jobQueue.isEmpty() && maxSize + jobQueue.peek().getRequeiredMemory() <= mainMemory)
				{
					process process = jobQueue.poll();
					readyQueue.add(process);
					
					maxSize += process.getRequeiredMemory();
				}
				
				for (process process : readyQueue)
				{
					int waitingTime = totalTime - process.getArrivalTime(); //in case there is an arrival time
					process.setWaitingTime(Math.max(0, waitingTime));
					GanttChart(process, totalTime, totalTime + process.getRemainingTime());
					totalTime += process.getBurstTime();
					int turnAroundTime = totalTime - process.getArrivalTime();
					process.setTurnAroundTime(turnAroundTime);
					temp.add(process);

					
					maxSize -= process.getRequeiredMemory();
				}
				
				readyQueue.clear(); 
			}

			Table(temp, processCounter);
		}
	}

	
	public void RR(int quantum)
	{
		synchronized (jobQueue)
		{
			Queue<process> readyQueue = new LinkedList<>();
			int totalTime = 0;
			int maxSize = 0;
			Queue<process> temp = new LinkedList<>();

			
			while (!readyQueue.isEmpty() || !jobQueue.isEmpty())
			{
				while (!jobQueue.isEmpty() && maxSize + jobQueue.peek().getRequeiredMemory() <= mainMemory)
				{
					process pro = jobQueue.poll();
					readyQueue.add(pro);
					maxSize += pro.getRequeiredMemory();
				}

			
				
				while (!readyQueue.isEmpty())
				{
					process process = readyQueue.poll();

					
					if (process.getRemainingTime() > 0)
					{
						if (process.getRemainingTime() > quantum)
						{
							
							GanttChart(process, totalTime, totalTime + Math.min(quantum, process.getRemainingTime()));
							totalTime += quantum;
							process.setRemainingTime(process.getRemainingTime() - quantum);
							readyQueue.add(process);
						} else
						{
							
							GanttChart(process, totalTime, totalTime + Math.min(quantum, process.getRemainingTime()));
							totalTime += process.getRemainingTime();
							process.setWaitingTime(totalTime - process.getBurstTime());
							process.setRemainingTime(0);
							process.setTurnAroundTime(totalTime - process.getArrivalTime()); //in case there is an arrival time
							temp.add(process);
							
							maxSize -= process.getRequeiredMemory();
						}

					}
				}
			}

			Table(temp, processCounter);
		}
	}

}
