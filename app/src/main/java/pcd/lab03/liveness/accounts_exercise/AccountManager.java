package pcd.lab03.liveness.accounts_exercise;

import java.util.concurrent.locks.Lock;

public class AccountManager {
	
	private final Account[] accounts;

	public AccountManager(int nAccounts, int amount){
		accounts = new Account[nAccounts];
		for (int i = 0; i < accounts.length; i++){
			accounts[i] = new Account(amount);
		}
	}
	
	public void transferMoney(int from,	int to, int amount) throws InsufficientBalanceException {
		// users identified by the array index
		if(this.accounts[from].getBalance() < amount){
			throw new InsufficientBalanceException();
		}
		this.accounts[from].debit(amount);
		this.accounts[to].credit(amount);
	}
	
	public int getNumAccounts() {
		return accounts.length;
	}
}

