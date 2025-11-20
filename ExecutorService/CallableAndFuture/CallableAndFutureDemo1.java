import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableAndFutureDemo1 {
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		try (ExecutorService service = Executors.newFixedThreadPool(2)) {
			Future<Integer> num = service.submit(new ReturnValueTask());
			// System.out.println(num.get());
			System.out.println(num.get(6, TimeUnit.SECONDS));
			System.out.println("Main Thread execution completed...");
		}
	}
}

class ReturnValueTask implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		Thread.sleep(5000);
		return 100;
	}

}
