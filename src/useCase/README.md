### Usecase

We want to perform task 1,2,3 in separate thread and want to get notified when all taks are completed.

### Some Use Cases
1. Android: Synchronize app’s data with the server through APIs and remove the loader when all completes.
2. Backend: Save data in database, file logs, and cache. Send response on all the task completion.


### Process
1. A process is an instance of a computer program that is being executed.
2. When an Android application starts, the Android system starts a new Linux process for the application with a single thread of execution called the main thread.

### Threads
1. A program can contains two or more parts that can run in parallel. Each part of such a program is called a thread.
2. They help in utilizing the multicore processors and also reduce the ideal CPU time of a single processor.
3. Creating too many threads slows down the execution
4. Only a few threads run in parallel, others wait for the CPU to get free.


### Build the programming model
#### Program Components
- Task: It is a Runnable that will contains the code to be executed in a separate thread.
- Worker: It will be responsible for creating a thread and running the supplied task.
- Executor: It will create workers to handle the tasks. It will also be responsible for broadcasting the completion of all the tasks.




