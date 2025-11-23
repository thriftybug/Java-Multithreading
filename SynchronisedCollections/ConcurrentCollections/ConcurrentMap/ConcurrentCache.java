import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCache {

	private static final Map<String, String> cache = new ConcurrentHashMap<>();

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			final int threadName = i;

			new Thread(() -> {

				String key = "Key @ " + threadName;
				for (int j = 0; j < 3; j++) {
					String value = getCachedValue(key);
					System.out.println("Thread : " + Thread.currentThread().getName() + " : KEY = " + key + " value : " + value);
				}
			}).start();
		}

	}

	private static String getCachedValue(String key) {
		String value = cache.get(key);

		if (value == null) {
			value = computeValue(key);
			cache.put(key, value);
		}
		return value;
	}

	private static String computeValue(String key) {
		System.out.println(key + " Key not present in the cache, so going to compute");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return "Value for " + key;
	}
}
