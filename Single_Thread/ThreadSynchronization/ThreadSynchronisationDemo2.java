package ThreadSynchronisation;

public class ThreadSynchronisationDemo2 {

	private static int counter1 = 0;
	private static int counter2 = 0;

	public static void main(String[] args) {

		Thread one = new Thread(() -> {

			for (int i = 0; i < 10000; i++) {
				increment1();
			}
		});

		Thread two = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				increment2();
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
		System.out.println(counter1 + "--" + counter2);
	}

	public synchronized static void increment1() {
		counter1++;
	}

	public synchronized static void increment2() {
		counter2++;
	}
}

/*
 * The synchronized behavior is going to aquire at class level log
 * The thead two can't access increment1() method as a result
 *
 */
