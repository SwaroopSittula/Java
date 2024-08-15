package CoreJava.ConcurrencyAndMultithreading.codes;

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
 *
 * Other methods: Thread.yield()
 */
class ThreadLifeCycle {
    public static void main(String[] args) {
        System.out.println("\n\033[1mThread Life Cycle:\033[0m"); // [0m = end format,  [1m = bold, [3m = italic
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 2 started");
                    lock.wait(); // Wait for notification (released at 2 sec)
                    Thread.sleep(2000); // Simulate a blocking operation (0 to 2 sec in timed waiting)
                    System.out.println("Thread 2 resumed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Thread 2 state: " + thread1.getState());
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
                synchronized (lock) {
                    lock.notify();
                }
            }

            System.out.printf("Thread 2 state at %d seconds: %s\n", i, thread1.getState());
            if (thread1.getState().equals(Thread.State.TERMINATED))
                break;
        }

        // Thread Join Example
        System.out.println("\n\033[1mThread Join Example:\033[0m");
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Thread 1 started...");
                Thread.sleep(2000); // Simulate a blocking operation (0 to 2 sec in timed waiting)
                System.out.println("Thread 1 resumed...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.printf("Thread 1 in New state: %s\n", thread2.getState());
        thread2.start();
        System.out.printf("Thread 1 state at 0 seconds: %s\n", thread2.getState());
        // when thread.join() is not used, main thread continues to execute the next line separately without waiting for the thread to complete.
        try {
            // waits for a thread to terminate before proceeding to the next line of code
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Thread 1 state after Join: %s\n", thread2.getState());
    }
}
