


## SingleThreadExecutor

![](../assets/2025-11-19-22-26-55.png)

- In a SingleThreadExecutor the size of thead pool will be one
- there will be only one thread in the `ThreadPool`
- If at all due to some exception the thread is killed, the executor will make sure that the thread will be re-created and the execution of the tasks won't be stopped. 
- As there is only one thread, it is guaranteed that the `Tasks` would be ran sequentially 
