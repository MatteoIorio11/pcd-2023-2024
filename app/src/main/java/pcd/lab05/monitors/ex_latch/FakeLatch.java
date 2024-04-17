package pcd.lab05.monitors.ex_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Latch - to be implemented
 */
public class FakeLatch implements Latch {
	private final CountDownLatch latch;
	public FakeLatch(final int size) {
		this.latch = new CountDownLatch(size);
	}
	
	@Override
	public void await() throws InterruptedException {
		this.latch.await();
	}
	@Override
	public void countDown() {
		this.latch.countDown();
	}

	
}
