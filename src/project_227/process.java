package project_227;

class process {
	private int id;
	private int burstTime;
	private int requeiredmemory;
	private int arrivalTime;
	private int waitingTime;
	private int turnAroundTime;
	private int completionTime;
	private int remainingTime;

	public process(int id, int burstTime, int requeiredmemory) {
		this.id = id;
		this.burstTime = burstTime;
		this.requeiredmemory = requeiredmemory;
		this.arrivalTime = 0;
		this.waitingTime = 0;
		this.turnAroundTime = 0;
		this.completionTime = 0;
		this.remainingTime = burstTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public void setTurnAroundTime(int turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public int getId() {
		return id;
	}

	public int getRequeiredMemory() {
		return requeiredmemory;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getTurnAroundTime() {
		return turnAroundTime;
	}

	public int getCompletionTime() {
		return completionTime;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setCompletionTime(int ct) {
		this.completionTime = ct;
	}

	public void setRemainingTime(int rt) {
		this.remainingTime = rt;
	}

	public void setArrivalTime(int at) {
		this.arrivalTime = at;
	}

}
