package pcd.lab02.lost_updates;


public class Worker extends Thread {
	
	private final UnsafeCounter counter;
	private final int ntimes;
	
	public Worker(final String name, final UnsafeCounter counter, final int ntimes){
		super(name);
		this.counter = counter;
		this.ntimes = ntimes;
	}
	
	public void run(){
		log("started");
		for (int i = 0; i < ntimes; i++){
			synchronized (this.counter) {
				this.counter.inc();
			}
		}
		log("completed");
	}
	
	private void log(String msg) {
		System.out.println("[ " + this.getName() + "] " + msg);
	}
	
}
