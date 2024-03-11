package pcd.lab03.liveness.deadlock_obs;

public class AgentOne extends Thread {
 	private final MyObservedEntity obj;
	
 	public AgentOne(final MyObservedEntity obj){
 		this.obj = obj;
 	}
 	
	public void run(){
		while (true){
			obj.changeState1();
			obj.changeState2();
		}
	}
}
