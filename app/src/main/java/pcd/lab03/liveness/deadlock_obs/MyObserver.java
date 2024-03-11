package pcd.lab03.liveness.deadlock_obs;

import java.util.ArrayList;
import java.util.List;

class MyObserver implements Observer {

	private final List<Observed> obsList;

	public MyObserver(){
		this.obsList = new ArrayList<Observed>();
	}
	
	public synchronized void observe(final Observed obj){
		obsList.add(obj);
		obj.register(this);
	}
	
	public void notifyStateChanged(final Observed obs) {
		synchronized(System.out){
			System.out.println("state changed: "+obs.getState());
		}
	}

	public synchronized int getOverallState() {
		int sum = 0;
		for (final Observed o: obsList){
			sum += o.getState();
		}
		return sum;
	}
}
