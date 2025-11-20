import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedueledThreadPool {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleWithFixedDelay(new ProbeClass(), 1000, 2000, TimeUnit.MILLISECONDS);

		try {
			if (!service.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
				service.shutdownNow();
			}
		} catch (Exception e) {
			service.shutdownNow();
		}
	}

}

class ProbeClass implements Runnable {

	@Override
	public void run() {
		System.out.println("Probing endpoint for updates...");
	}
}
