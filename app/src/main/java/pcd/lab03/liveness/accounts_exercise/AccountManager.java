package pcd.lab03.liveness.accounts_exercise;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class AccountManager {
	
	private final Account[] accounts;
	private final List<Lock> lock;

	public AccountManager(int nAccounts, int amount, final List<Lock> lock){
		accounts = new Account[nAccounts];
		this.lock = lock;
		for (int i = 0; i < accounts.length; i++){
			accounts[i] = new Account(amount);
		}
	}
	
	public void transferMoney(int from,	int to, int amount) throws InsufficientBalanceException {
		// users identified by the array index
		final int min = Math.min(from, to);
		final int max = Math.max(from , to);
		this.lock.get(min).lock();
		this.lock.get(max).lock();
		try {
			if (this.accounts[from].getBalance() < amount) {
				throw new InsufficientBalanceException();
			}
			this.accounts[from].debit(amount);
			this.accounts[to].credit(amount);
		}finally {
			this.lock.get(min).unlock();
			this.lock.get(max).unlock();
		}

	}
	
	public int getNumAccounts() {
		return accounts.length;
	}
}

