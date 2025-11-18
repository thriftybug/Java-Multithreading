import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

	public static void main(String[] args) {

		Worker worker = new Worker(5, 0);
		Thread t1 = new Thread(() -> {
			try {
				worker.produce();
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				worker.consume();
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
		});

		t1.start();
		t2.start();
	}
}

class Worker {

	private int sequence = 0;
	private final Integer top;
	private final Integer bottom;
	private final List<Integer> container;
	private final Object lock = new Object();

	public Worker(Integer top, Integer bottom) {
		this.top = top;
		this.bottom = bottom;
		this.container = new ArrayList<>();
	}

	public void produce() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (container.size() == top) {
					System.out.println("Container is full, waiting for the items to be removed");
					lock.wait();
				} else {
					System.out.println(sequence + " added to the container");
					container.add(sequence++);
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}

	public void consume() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (container.size() == bottom) {
					System.out.println("Container is empty, waiting for the items to be filled");
					lock.wait();
				} else {
					System.out.println(container.removeFirst() + " removed from the container");
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}
}
