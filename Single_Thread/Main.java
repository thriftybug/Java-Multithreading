import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String targetPassword = "howareyou9999";

		String filePath = "../passwords/rockyou.txt";

		long startTime = System.currentTimeMillis();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			long count = 0;
			while ((line = br.readLine()) != null) {
				count++;
				if (line.equals(targetPassword)) {
					long endTime = System.currentTimeMillis();
					System.out.println("Password found: " + line);
					System.out.println("Lines checked: " + count);
					System.out.println("Time taken: " + formatTime(endTime - startTime));
					return;
				}
			}
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Password not found.");
		System.out.println("Time taken: " + formatTime(endTime - startTime));

	}

	private static String formatTime(long millis) {
		long hrs = TimeUnit.MILLISECONDS.toHours(millis);
		long mins = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
		long secs = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
		long ms = millis % 1000;

		return String.format("%02d:%02d:%02d:%03d", hrs, mins, secs, ms);
	}
}
