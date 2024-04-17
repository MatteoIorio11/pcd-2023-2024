package pcd.lab05.monitors.ex_barrier;

import java.awt.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Barrier - to be implemented
 */
public class FakeBarrier implements Barrier {
	private final Lock mutex;
	private final Condition allArrived;
	private final int barrierSize;
	private int arrived = 0;
	
	public FakeBarrier(final int nParticipants) {
		this.mutex = new ReentrantLock();
		this.allArrived = this.mutex.newCondition();
		this.barrierSize = nParticipants;
	}
	
	@Override
	public void hitAndWaitAll() throws InterruptedException {
		try{
			this.mutex.lock();
			this.arrived = this.arrived + 1;
			while(this.arrived != this.barrierSize){
				System.out.println("Thread: " + this.arrived + " waits for all the threads.");
				this.allArrived.await();
			}
			this.allArrived.signalAll();
		}finally {
			this.mutex.unlock();
		}
	}

	
}
