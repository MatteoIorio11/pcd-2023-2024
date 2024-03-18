package pcd.lab04.sem.ex;

import java.util.concurrent.Semaphore;

public class Pinger extends Thread {

	final Semaphore pingM;
	final Semaphore pongM;
	public Pinger(final Semaphore pingM, final Semaphore pongM) {
		this.pingM = pingM;
		this.pongM = pongM;
	}	
	
	public void run() {
		while (true) {
			try {

				System.out.println("ping!");
				this.pingM.release();
				Thread.sleep(5000);
				this.pongM.acquire();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
			}
		}
	}
}