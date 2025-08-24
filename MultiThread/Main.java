import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class ThreadSearch extends Thread {
	private List<String> lines;
	private String targetPassword;
	private int threadId;
	private static volatile boolean found = false;

	public ThreadSearch(List<String> lines, String targetPassword, int threadId) {
		this.lines = lines;
		this.targetPassword = targetPassword;
		this.threadId = threadId;
	}

	@Override
	public void run() {
		System.out.println("Thread - " + threadId + " started: " + lines.size() + " lines to search");
		// int counter = 0;
		for (String line : lines) {
			if (found) {
				System.out.println("Thread - " + threadId + " exiting early, Password already found.");
				return;
			}

			if (line.equals(targetPassword)) {
				found = true;
				// System.out.println("Thread - " + threadId + " found the password: " + line) ;
				return;
			}
			// counter++;
			// if(counter % 100000 == 0){
			// // System.out.println("Thread - " + threadId + " processed " + counter + "
			// lines...");
			// }
		}
		System.out.println("Thread - " + threadId + " finished. Password not found in its chunk.");
	}
}

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String targetPassword = "howareyou9999";
		String filePath = "passwords/rockyou.txt";

		long startTime = System.currentTimeMillis();

		// Loading all lines into memory
		List<String> allLines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				allLines.add(line);
			}
		}

		// Split into chunks
		int numThreads = 4;
		int chunkSize = allLines.size() / numThreads;

		Thread[] threads = new Thread[numThreads];

		for (int i = 0; i < numThreads; i++) {
			int start = i * chunkSize;
			int end = (i == numThreads - 1) ? allLines.size() : (i + 1) * chunkSize;
			List<String> subList = allLines.subList(start, end);

			threads[i] = new ThreadSearch(subList, targetPassword, i + 1);
			threads[i].start();
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Total time taken: " + formatTime(endTime - startTime));
	}

	private static String formatTime(long millis) {
		long hrs = TimeUnit.MILLISECONDS.toHours(millis);
		long mins = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
		long secs = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
		long ms = millis % 1000;

		return String.format("%02d:%02d:%02d:%03d", hrs, mins, secs, ms);
	}
}
