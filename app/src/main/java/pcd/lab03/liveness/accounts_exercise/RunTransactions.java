package pcd.lab03.liveness.accounts_exercise;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RunTransactions {

	private static final int NUM_TRANSFER_AGENTS = 20;
	private static final int NUM_ACCOUNTS = 5;
	private static final int NUM_ITERATIONS = 10000000;

	
	public static void main(String[] args) {
		final List<Lock> locks = Stream.generate(ReentrantLock::new).limit(NUM_ACCOUNTS).collect(Collectors.toList());
		final AccountManager man = new AccountManager(NUM_ACCOUNTS,1000);
		
		for (int i = 0; i < NUM_TRANSFER_AGENTS; i++){
			new TransferAgent(man, NUM_ITERATIONS, locks).start();
		}
	}
}
