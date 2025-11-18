public class WaitAndNotifyDemo {

	private static final Object LOCK = new Object();

	public static void main(String[] args) {

		Thread one = new Thread(() -> {
			try {
				method1();
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
		});

		Thread two = new Thread(() -> {
			try {
				method2();
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
		});

		one.start();
		two.start();
	}

	private static void method1() throws InterruptedException {
		synchronized (LOCK) {
			System.out.println("Hello from method1");
			LOCK.wait();
			System.out.println("Back again in method1");
		}
	}

	private static void method2() throws InterruptedException {
		synchronized (LOCK) {
			System.out.println("Hello from method2");
			LOCK.notify();
			System.out.println("Executing remaining code from method2");
		}

	}

}
