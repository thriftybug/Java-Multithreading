

## Thread Synchronization


### Race Condition

- Two threads trying to access same resource at same time


### synchronized keyword

- it tells JVM that this method or block can be accessed by one and only one Thread at a given time
- we can use this on different levels


| Where Applied       | Example                               | Lock Used       | When Used                     |
| ------------------- | ------------------------------------- | --------------- | ----------------------------- |
| **Instance Method** | `public synchronized void m()`        | `this`          | Protect object state          |
| **Static Method**   | `public static synchronized void m()` | `MyClass.class` | Protect static state          |
| **Block**           | `synchronized(lock){...}`             | any object      | Most flexible + best practice |


- How does synchronized keyword work in java?
- each object in java is associated with a `monitor lock`
	- this is a mutual exclusion mechanism used for synchronization
	- whenever a thread enters a synchronized block, the thread acquires monitor lock associated with the object, on which the synchronization is applied. 

- only if the lock is available, if not the thread waits until the lock is available.
- If another thread is holding the lock, then the thread enters the `blocked` state, it has to wait until the lock becomes available
- monitor lock used by synchronized keyword is referred to as `Intrinsic lock` or `Monitor lock` of the object instance
- Each object have this lock, and the threads use them implicitly to acquire or release them

#### Problems with synchronized keyword

1. 
- when we apply synchronized at a method level, it applies to entire method
- we are blocking other threads from entering the method, the critical section maybe only 2 or 3 lines, and the method may be having other lines which do not involve synchronization.
- this leads to reduced concurrency and performance bottleneck

2.
- when synchronized is used at the method level we loose the fine grained control needed in the more complex scenario

3. 
- If a subclass overrides a synchronized method from superclass it also needs to be explicitly  mentioned as `synchronized`, to maintain the synchronization behaviour
- failing to do so will lead to unexpected failures and potential synchronization issues
