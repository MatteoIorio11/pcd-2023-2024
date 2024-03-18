package pcd.lab04.sem.ex;

import java.util.concurrent.Semaphore;

public class Ponger extends Thread {

	final Semaphore pingM;
	final Semaphore pongM;
	public Ponger(final Semaphore pingM, final Semaphore pongM) {
		this.pingM = pingM;
		this.pongM = pongM;
	}	
	
	public void run() {
		while (true) {
			try {
				this.pingM.acquire();
				System.out.println("pong!");
				this.pongM.release();
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
			}
		}
	}
}