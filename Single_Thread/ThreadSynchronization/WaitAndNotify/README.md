


## Wait and Notify


- this can be called on Lock object
- when we call `.wait()` from say thread one
	- thread one will be suspended and go to `waiting`
- The `lock` will be available and will be used by thread two
- once after we call `.notify`, then whatever code that is available for that particular synchronized block then that code will be executed 


### difference between wait() and sleep(ms)

- `wait()` is used for inter thread communication. 
- `sleep(ms)` is used for just pausing the execution of given thread for a specified duration in milliseconds

- wait(long timeoutMillis)
    - **Purpose**: This method allows a thread to wait for a specified maximum amount of time (in milliseconds) for a notification. If a notification is received before the timeout, the thread wakes up. If the timeout expires and no notification is received, the thread automatically wakes up.
    - **Difference from `wait()`**:
        - `wait()` (without arguments) causes the current thread to wait indefinitely until another thread invokes the `notify()` or `notifyAll()` method for this object, or until the thread is interrupted.
        - `wait(long timeoutMillis)` provides a mechanism for a thread to avoid waiting forever. It's useful in scenarios where a thread needs to eventually proceed even if no notification arrives, or to implement a polling mechanism with a timeout.

- notifyAll()
    - **Purpose**: Wakes up all threads that are waiting on this object's monitor.
    - **Difference from `notify()`**:
        - `notify()` wakes up a single arbitrary thread that is waiting on this object's monitor. There's no guarantee which waiting thread will be chosen.
        - `notifyAll()` wakes up *all* waiting threads. This is useful when multiple threads might be waiting for different conditions, or when you want to ensure all potentially waiting threads get a chance to re-evaluate their conditions.
