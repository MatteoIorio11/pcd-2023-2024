package pcd.lab04.sem.ex;

import java.util.concurrent.Semaphore;

/**
 * Unsynchronized version
 * 
 * @TODO make it sync 
 * @author aricci
 *
 */
public class TestPingPong {
	public static void main(String[] args) {
		final Semaphore ping = new Semaphore(0);
		final Semaphore pong = new Semaphore(0);
		new Pinger(ping, pong).start();
		new Ponger(ping, pong).start();

		pong.release();
	}

}
