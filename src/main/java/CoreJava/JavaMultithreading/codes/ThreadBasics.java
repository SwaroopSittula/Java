package CoreJava.JavaMultithreading.codes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

public class ThreadBasics {
    public static void main(String[] args) {
        ThreadProperties.main(args);
        ThreadStatesAndPriorities.main(args);
        ThreadMethods.main(args);
        ThreadLifeCycle.main(args);
        DaemonThread.main(args);
        // Note:
        // Daemon thread in above class keeps running
        // since there is a user thread running in below ThreadJoin class
        ThreadJoin.main(args);
    }
}


/**
 * Thread Properties: ID, name, state and priority
 */
class ThreadProperties {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();

        System.out.println("\033[1mThread Properties:\033[0m"); // [0m = end format,  [1m = bold, [3m = italic
        System.out.printf("Thread Id: %d\nThread Name: %s\nThread State: %s\nThread Priority: %s\n", t.getId(), t.getName(), t.getState(), t.getPriority());

    }
}


/**
 * Thread States (Enum):
 * Values: new, runnable, blocked, waiting, timed_waiting, terminated
 * Thread Priority:
 * Range: [1, 10] inclusive.
 * Default/Normal Priority = 5.
 */
class ThreadStatesAndPriorities {
    public static void main(String[] args) {
        System.out.println("\n\033[1mThread States:\033[0m");
        EnumSet.allOf(Thread.State.class).forEach(System.out::println);

        System.out.println("\n\033[1mThread Priorities:\033[0m");
        System.out.printf("Min Priority: %d\nNormal Priority: %d\nMax Priority: %d\n", Thread.MIN_PRIORITY, Thread.NORM_PRIORITY, Thread.MAX_PRIORITY);
    }
}


/**
 * List of Unique methods in Thread Class
 */
class ThreadMethods {
    public static void main(String[] args) {
        Method[] methods = Thread.class.getMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));

        System.out.println("\n\033[1mThread Methods:\033[0m");
        for (int i = 0; i < methods.length; i++)
            if (i == 0 || !methods[i - 1].getName().equals(methods[i].getName()))
                System.out.println(methods[i].getName());
    }
}


/**
 * Thread Life Cycle Example
 * Note: Thread.getState() method can be used to inspect a thread's current state at runtime.
 * thread.wait() ⇒ Current thread changes and thread.getState() produce error when used.
 * thread.join() ⇒ waits for a thread to terminate before proceeding to the next line of code
 * <p>
 * Other methods: Thread.yield()
 */
class ThreadLifeCycle {
    public static void main(String[] args) {
        System.out.println("\n\033[1mThread Life Cycle:\033[0m");
        // lock can also be created using ThreadLifeCycle.class (replace `lock`)
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 1 started...");
                    lock.wait(); // Wait for notification (released at 2 sec)
                    Thread.sleep(2000); // Simulate a blocking operation (0 to 2 sec in timed waiting)
                    System.out.println("Thread 1 resumed... (RUNNABLE)");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Thread 1 state: " + thread1.getState());
        thread1.start();
        for (int i = 0; i < 100; i++) {
            if (i != 0) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (i == 2) {
                // Simulate a notification to thread 1
                // At 2 sec main thread is operating the lock to send notification => thread 1 is blocked during notification
                synchronized (lock) {
                    lock.notify();
                }
            }

            System.out.printf("Thread 1 state at %d seconds: %s\n", i, thread1.getState());
            if (thread1.getState().equals(Thread.State.TERMINATED))
                break;
        }

        // Thread Join Example
        System.out.println("\n\033[1mThread Join Example:\033[0m");
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Thread 2 started...");
                Thread.sleep(2000); // Simulate a blocking operation (0 to 2 sec in timed waiting)
                System.out.println("Thread 2 resumed...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.printf("Thread 2 in New state: %s\n", thread2.getState());
        thread2.start();
        System.out.printf("Thread 2 state at 0 seconds: %s\n", thread2.getState());
        // when thread.join() is not used, main thread continues to execute the next line separately without waiting for the thread to complete.
        try {
            // waits for a thread to terminate before proceeding to the next line of code
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Thread 2 state after Join: %s\n", thread2.getState());
    }
}


/**
 * Thread Daemon:
 * JVM terminates a daemon thread abruptly (undefined state)
 * if there are no user threads running (Main thread, and it's non-daemon child threads)
 * <p>
 * Runnable is passed using lambda expression
 */
class DaemonThread {
    public static void main(String[] args) {
        System.out.println("\n\033[1mDaemon Thread Example:\033[0m");

        Thread daemonThread = new Thread(() -> {
            // Infinitely Running Thread
            while (true) {
                sleep(1000);
                System.out.println("Daemon Thread is Running...");
            }
        }, "Daemon Thread Example");

        // Making the Thread as Daemon Thread => Runs as long as the Main Thread is still running
        daemonThread.setDaemon(true);
        daemonThread.start();

        // The Main Thread is Sleeping
        sleep(3000);
        System.out.println("Main Thread is completed..");
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


/**
 * Thread Join:
 * Thread Join can be used to make the main thread wait until the child thread finishes its execution
 */
class ThreadJoin {
    public static void main(String[] args) {
        System.out.println("\n\033[1mThread Join Example:\033[0m");

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sleep(1000);
                System.out.println("Daemon Thread is Running (join)...");
            }
        });

        // Since this a Daemon Thread it should only run until the main thread is running
        thread.setDaemon(true);
        thread.start();

        try {
            // The Main Thread is kept waiting until this thread completes its execution
            thread.join();
            System.out.println("Daemon Thread is completed..");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sleep(1000);
        System.out.println("Main Thread is completed..");
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}