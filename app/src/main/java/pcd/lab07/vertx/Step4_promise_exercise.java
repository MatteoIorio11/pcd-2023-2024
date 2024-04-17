package pcd.lab07.vertx;

import io.vertx.core.*;


/**
 * Exercise
 * 
 * -- implement an async protected method getDelayedRandom(int delay)
 *    that returns a random value between 0 and 1 after the specified
 *    amount of time
 *    
 * -- in the "start" method, test the behaviour of the method by using it.
 * 
 */
class VerticleWithPromise extends AbstractVerticle {
	
	public void start() {
		log("started.");
		final Future<Double> future = this.getDelayedRandom(1000);
		future.onSuccess(res -> {
			log("Random value: " + res);
		});
		log("Exited");
	}

	protected Future<Double> getDelayedRandom(final int delay){
		log("Future creation...");
		final Promise<Double> promise = Promise.promise();
		this.getVertx().setTimer(delay, handler -> {
			promise.complete(Math.random());
		});
		log("Future creation is done.");
		return promise.future();
	}

	private void log(String msg) {
		System.out.println("[REACTIVE AGENT]["+Thread.currentThread()+"]" + msg);
	}
}

public class Step4_promise_exercise {
	public static void main(String[] args) {
		
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new VerticleWithPromise());
	}
}

