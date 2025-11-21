
package ConcurrentCollections.CountdownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	public static void main(String[] args) throws InterruptedException {
		int numOfChefs = 3;
		CountDownLatch latch = new CountDownLatch(numOfChefs);

		new Thread(new Chef("ChefA", "Pizza", latch)).start();
		new Thread(new Chef("ChefB", "Salad", latch)).start();
		new Thread(new Chef("ChefC", "Pasta", latch)).start();

		latch.await();

		System.out.println("All dishes are ready, let's start serving the customers");
	}

}

class Chef implements Runnable {

	private final String name;
	private final String dish;
	private final CountDownLatch latch;

	public Chef(String name, String dish, CountDownLatch latch) {
		this.latch = latch;
		this.name = name;
		this.dish = dish;
	}

	@Override
	public void run() {
		try {
			System.out.println("Chef " + name + " started preparing dish " + dish);
			Thread.sleep(2000);
			System.out.println("Chef " + name + " has finished preparing " + dish);
			latch.countDown();
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}
}
