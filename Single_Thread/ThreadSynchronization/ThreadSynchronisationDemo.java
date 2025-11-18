
package ThreadSynchronisation;

public class ThreadSynchronisationDemo {

	static int counter = 0;

	public static void main(String[] args) {

		Thread one = new Thread(() -> {

			for (int i = 0; i < 10000; i++) {
				increment();
			}
		});

		Thread two = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				increment();
			}
		});

		one.start();
		two.start();

		try {
			one.join();
			two.join();
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
		System.out.println(counter);
	}

	public synchronized static void increment() {
		counter++;
	}

}
/*
 * 1. Load
 * 2. Increment
 * 3. Set back the value
 * counter = 0; incrementValue = 1 < thread one
 * counter = 0; incrementValue = 1 < thread two
 * Two threads trying to access and modify the same resources (counter) at the
 * same time
 */
