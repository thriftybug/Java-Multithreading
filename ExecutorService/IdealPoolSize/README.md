


## Ideal Pool Size

- Ideal Thread pool size
	- Is your task CPU intensive 
		- one thread is one os level thread
		- if a cpu has 4 cores at max, we could execute 4 tasks in parallel at once
		- so creating hundreds of thread won't help, infact it results to a performance degradation
		- what we can do is have the same number of threads as the number of cores in the CPU
			- this does not ensure that all the cores will be running the threads only
			- some of the cores will be utilized by other tasks and os level processes as well 
	- Is your task IO intensive
	- Use a combinational approach
