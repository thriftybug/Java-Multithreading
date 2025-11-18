
### LockWithCustomObjects


- We `synchronized` keyword at the block level and the locks which the threads will then make use

```java
private static void increment1() {
		synchronized (lock1) {
			counter1++;
		}
	}

	private static void increment2() {
		synchronized (lock2) {
			counter2++;
		}
	}
```

