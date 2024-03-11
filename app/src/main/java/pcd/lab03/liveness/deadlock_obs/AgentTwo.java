package pcd.lab03.liveness.deadlock_obs;

public class AgentTwo extends Thread {
 	private final MyObserver obj;
	
 	public AgentTwo(final MyObserver obj){
 		this.obj = obj;
 	}
 	
	public void run(){
		while (true){
			log("overall state: "+obj.getOverallState());
		}
	}

	private void log(final String msg){
		synchronized(System.out){
			System.out.println("["+this+"] "+msg);
		}
	}
}

