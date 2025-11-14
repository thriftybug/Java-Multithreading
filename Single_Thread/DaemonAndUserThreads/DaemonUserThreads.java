
public class DaemonUserThreads {
	public static void main(String[] args) {
		Thread bgThread = new Thread(new DaemonHelper());
		Thread usrThread = new Thread(new UserThreadHelper());

		// to make a thread Daemon thread
		bgThread.setDaemon(true);

		bgThread.start();
		usrThread.start();
	}
}

class DaemonHelper implements Runnable {
	@Override
	public void run() {
		int count = 0;
		while (count < 10) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
			count++;
			System.out.println("DaemonHelper running..." + count);
		}
	}
}

class UserThreadHelper implements Runnable {
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
		System.out.println("User Thread done with execution...");
	}
}
