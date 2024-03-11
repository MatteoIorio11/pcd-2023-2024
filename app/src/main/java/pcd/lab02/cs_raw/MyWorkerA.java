package pcd.lab02.cs_raw;

public class MyWorkerA extends Worker {
	
	final private Object lock; // shared object
	
	public MyWorkerA(final String name, final Object lock){
		super(name);
		this.lock = lock;
	}
	
	public void run(){
		while (true){
		  action1();	
		  synchronized(this.lock){
			  action2(); // critical section
			  action3(); // critical section
		  }
		}
	}
	
	protected void action1(){
		println("a1");
		wasteRandomTime(100,500);	
	}
	
	protected void action2(){
		println("a2");
		wasteRandomTime(300,700);	
	}
	protected void action3(){
		println("a3");
		wasteRandomTime(300,700);	
	}
}

