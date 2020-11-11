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


See code in Solution 1

<hr>

Problem - We have likes, post,comments and new friends and we want to merge all these activities and then want to see then in order

Where each activity is fetch through seperate api

We will be using callable, future and threadPollExecutor for this

See solution 2, now we can do work asyncronously using threadPollExecutor


```
Program Started
doing some other task...
fetching likes from server
Counting i: 0 is multiple of 5
Counting i: 5 is multiple of 5
Counting i: 10 is multiple of 5
Counting i: 15 is multiple of 5
Counting i: 20 is multiple of 5
Counting i: 25 is multiple of 5
Counting i: 30 is multiple of 5
Counting i: 35 is multiple of 5
Counting i: 40 is multiple of 5
Counting i: 45 is multiple of 5
Counting i: 50 is multiple of 5
Counting i: 55 is multiple of 5
Counting i: 60 is multiple of 5
Counting i: 65 is multiple of 5
Counting i: 70 is multiple of 5
Counting i: 75 is multiple of 5
Counting i: 80 is multiple of 5
Counting i: 85 is multiple of 5
Counting i: 90 is multiple of 5
Counting i: 95 is multiple of 5
fetching posts from server
fetching comments from server
fetching friends from server
Like{createAt=Wed Jan 14 00:27:21 IST 1970}
Like{createAt=Thu Jan 15 04:14:01 IST 1970}
post{createAt=Mon Jan 19 18:30:41 IST 1970}
post{createAt=Mon Jan 19 23:47:21 IST 1970}
comment{createAt=Mon Jan 15 00:44:06 IST 2018}
friend{createAt=Thu Jul 12 12:20:48 IST 2018}
comment{createAt=Sat Aug 11 14:34:08 IST 2018}
friend{createAt=Mon Sep 03 23:40:46 IST 2018}
Program Terminated

```

<hr>
### Synchronization
To avoid variables from race condition in multithread situation we use threads, it is done with monitor object, 
every object has monitor object associated with it. Can be applied in block level or functional level. 
 
See implementation in Synchronization package

If we don't use synchronized keyword then we have
```
Now resource is disallowed now ..
I processed because processing was allowed true
```
But this is wrong as even after the disallowed, its processing, so to make
sure that atomic boolean if not accessed simultaneous by both method 
we use synchronised keyword for both methods

```
I processed because processing was allowed false
Now resource is disallowed now ..
```


<hr>

Some Synchronization is not easy to implement so we locks things, we will solve use case where we are syncing items data from server and then generating invoice from it.
We want to fetch the latests price from server and simultaneously generate invoice for this sync.

See Locks package.

If we don't use locks then getting following results, which is obvious
because sync takes 3 seconds and executor prepare the invoice till then, and this invoice is not updated as it
was created before the sync from the server.
```
main: got the invoice generated price 80
Got all the synced data from server and hired total price is 130
main: Okay we have synced the latest prices from server
program terminated !
```
If we use locks, then we can make sure that invoice is not created we are sync the data from server, and we get apt result.
```
Got all the synced data from server and hiked total price is 130
main: Okay we have synced the latest prices from server
main: got the invoice generated price 130
program terminated !
```

This is important use case in android.



