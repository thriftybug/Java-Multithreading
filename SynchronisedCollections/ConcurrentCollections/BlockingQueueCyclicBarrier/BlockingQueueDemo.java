import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
	static final int QUEUE_CAPACITY = 10;
	static BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

	public static void main(String[] args) {

		Thread producer = new Thread(() -> {
			try {
				for (int i = 1; i <= 20; i++) {
					System.out.println("Task produced:  " + i);
					taskQueue.put(i);
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
		});

		Thread consumerOne = new Thread(() -> {
			try {
				while (true) {
					int task = taskQueue.take();
					procesTask(task, "consumerOne");
				}
			} catch (Exception e) {
				throw new RuntimeException();
			}
		});

		Thread consumerTwo = new Thread(() -> {
			try {
				while (true) {
					int task = taskQueue.take();
					procesTask(task, "consumerTwo");
				}
			} catch (Exception e) {
				throw new RuntimeException();
			}
		});

		producer.start();
		consumerOne.start();
		consumerTwo.start();
	}

	private static void procesTask(int task, String threadName) throws InterruptedException {
		System.out.println("Task being processed by " + threadName + " : " + task);
		Thread.sleep(500);
		System.out.println("Task consumed by " + threadName + " : " + task);
	}
}
