package pcd.lab03.liveness.deadlock_obs;

import java.util.ArrayList;
import java.util.List;

public class MyObservedEntity implements Observed {

	private final List<Observer> obsList;
	private int state;

	public MyObservedEntity(){
		obsList = new ArrayList<>();
	}

	public synchronized void register(final Observer obs) {
		obsList.add(obs);
	}

	public synchronized int getState() {
		return state;
	}

	public synchronized void changeState1() {
		state++;
		for (final Observer o: obsList){
			o.notifyStateChanged(this);
		}
	}

	public synchronized void changeState2() {
		state--;
		for (final Observer o: obsList){
			o.notifyStateChanged(this);
		}
	}
}
