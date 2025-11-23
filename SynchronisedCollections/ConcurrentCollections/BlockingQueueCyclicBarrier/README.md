


## Blocking Queue and Cyclic barrier


- `Cyclic Barrier`
	- basically to modify the `latch` counter

- How does `Cyclic Barrier` work under the hood?

- Important `Concurrent Collection` in java `Blocking Queue`

### Blocking Queue

- It is a data structure which allows multiple threads to safely put the items on to the queue and take items of the queue and this is done in concurrent manner
- There are two aspects of Blocking Queue 
1. The Blocking aspect
	- The thread will blocked if it tries to get an item from the queue if is empty, it will paused or blocked until an item becomes available
		- Similarly if a thread tries to add an item to the queue if it is full, it will be blocked until the space becomes available
	- FIFO -> item that is added first will be removed first

- In java `BlockingQueue` Interface is the parent interface for few other interfaces and the concrete implementation classes
	- Two interfaces which extends `BlockingQueue` are
	1. `BlockingDequeu`
		- it is a double ended queue that blocks threads when it reaches its capacity or becomes empty, thereby facilitating flow control using `producer` and `consumer`
		- it provides methods to access the queue from both the ends in a `thread safe` manner, due to its double ended nature the performance nature of `BlockingDequeue` may differ from those of blocking queue. Especially when there is a scenario to access both ends of the dequeue by multiple threads simultaneously
		- it provides access to both the ends in concurrent manner.
	2. `TransferQueue`
		- It is a specialized queue where producers can block until a consumer directly receives an item.
		- It extends the functionality of blocking queue by providing a method called as `transfer()`, what this is that it allows one thread to transfer an item directly to another waiting thread, avoiding the need to blocking.
		- If there are no waiting threads transfer behaves like `put()` and blocks until there is a space available for the item.
		- `TransferQueue` ensures a strong hand of coordination
			- example
				- say there is a factory where one worker produces an item and another worker packages them for shipping. The `transfer()` method would allow the producer to hand off an item directly to the packager, and the producer would wait until the packager is ready to receive it. This ensures that items are not left accumulating if the packager is busy, and the producer doesn't create new items unnecessarily.

### Major Implementation of BlockingQueue

1. `ArrayBlockingQueue`
	- It implements a `bounded blocking` queue and it is backed by `array` data structure.

2. `LinkedBlockingQueue`
	- It implements a `bounded or unbounded blocking queue` and is backed by a `linked list`

3. `PrirorityBlockingQueue` 
	- this implements a `BlockingQueue`, that orders elements based on their natural order or according to a specified comparator

4. `DelayQueue`
	- it implements a `BlockingQueue` of delayed elements, where an element can be taken out when it's delay has been expired
	- it is useful for `Scheduling tasks` to be executed after a certain delay or a specific time. 

5. `SynchronousQueue`
	- it implements a `zero capacity` BlockingQueue where each `insert` operation must wait for a corresponding `remove` operation by another thread and vice versa

### Blocking Queue operations

1. `put(E e)`
	- adds the specified element `e` to the queue if the space is available if the queue is full the operation is blocked until the queue is available 

2. `take()`
	- retrieves and removes the `head` of the queue, if the queue is empty the operations blocks and resumes when an element becomes available

3. `offer(E e)`
	- it adds the specified element to the queue, if a space is available, it returns `true` if the element was `successfully` added  or `false` if the queue is full as a pose to the `put()` blocks until the space is available 
.
4. `poll()`
	- retrieves the element from the `head` of the queue
	- it returns null if the queue is empty
	- it does not block the queue, if the queue is empty as of `take()` operation which blocks the queue until an element is available

5 `peek()`
	- `retreives` but does not `remove` the head of the queue
	- returns `null` if the queue is empty

- Some of the above method also provides `overloaded` signatures which allows us to pass `timeout` option. 
