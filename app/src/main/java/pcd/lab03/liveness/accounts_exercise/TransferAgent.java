package pcd.lab03.liveness.accounts_exercise;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class TransferAgent extends Thread {
	
	private static final int MAX_AMOUNT = 10;
	private final List<Lock> lock;
	private AccountManager man;
	private int numTransactions;

	TransferAgent(AccountManager man, int numTransactions, final List<Lock> lock){
		this.man = man;
		this.numTransactions = numTransactions;
		this.lock = lock;
	}
	
	public void run() {
		
		final Random gen = new Random();
		int numAccounts = man.getNumAccounts();
		
		for (int i = 0; i < numTransactions; i++){
			
			/* select the source account */
			int fromAcc = gen.nextInt(numAccounts);
			if(this.lock.get(fromAcc).tryLock()) {
				/* select the dest account */
				int toAcc = 0;
				do {
					toAcc = gen.nextInt(numAccounts);
				} while (toAcc == fromAcc);
				if(this.lock.get(toAcc).tryLock()) {

					/* select an amount of money to transfer */
					int amount = gen.nextInt(MAX_AMOUNT);

					/* try to transfer */
					this.lock.get(fromAcc).lock();
					this.lock.get(toAcc).lock();
					try {
						log("Transferring from " + fromAcc + " to " + toAcc + " amount " + amount + "...");
						man.transferMoney(fromAcc, toAcc, amount);
						log("done.");
					} catch (InsufficientBalanceException ex) {
						log("Not enough money.");
					} finally {
						this.lock.get(fromAcc).unlock();
						this.lock.get(toAcc).unlock();
					}
				}else{
					this.lock.get(fromAcc).unlock();
				}
			}
		}
	}
	
	private void log(String msg){
		synchronized(System.out){
			System.out.println("["+this+"] "+msg);
		}
	}
}