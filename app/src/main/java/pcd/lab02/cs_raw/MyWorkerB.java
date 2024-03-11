package pcd.lab02.cs_raw;

public class MyWorkerB extends Worker {
	
	final private Object lock;
	
	public MyWorkerB(final String name, final Object lock){
		super(name);
		this.lock = lock;
	}

	public void run(){
		while (true){
			// sync using the object lock
		  synchronized(this.lock){
			  action1();	
			  action2();
		  }
		  action3();
		}
	}
	
	protected void action1(){
		println("b1");
		wasteRandomTime(0,1000);	
	}
	
	protected void action2(){
		println("b2");
		wasteRandomTime(100,200);	
	}
	protected void action3(){
		println("b3");
		wasteRandomTime(1000,2000);	
	}
}
