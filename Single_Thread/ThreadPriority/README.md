

## Thread Priority

![](assets/2025-11-14-13-53-57.png)

![](assets/2025-11-14-14-02-17.png)

- Initially, `main` thread will have a priority of 5, `NORM_PRIORITY`

- In example `ThreadPriorityExampl2.java`, the `main` thread getting the first priority, regardless of thread `one` having the higher priority is only for the first time. Once the execution starts it will go by `FIFO` manner, the `thread scheduler` will schedule the thread based on their priority, if they happen to be of the same priority `FIFO`  will be used to schedule the threads.
