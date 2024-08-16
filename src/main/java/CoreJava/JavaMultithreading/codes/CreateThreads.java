package CoreJava.JavaMultithreading.codes;

public class CreateThreads {

}


// 1. Thread Class

/**
 * 1.1 Default Thread Creation using Thread class
 */
class DefaultThreadClass {
    public static void main(String[] args) {
        // Default without providing a name
        Thread thread = new Thread();
        thread.start();
        System.out.println(thread.getName());

        // Named Thread Creation
        Thread namedThread = new Thread("My Thread");
        namedThread.start();
        System.out.println(namedThread.getName());
    }
}

/**
 * 1.2. Extending Thread Class and overriding `run` method
 */
class ExtendThreadClass {
    public static class MyThread extends Thread {
        public void run() {
            System.out.println("MyThread has started...");
            System.out.println("MyThread finished...");
        }
    }

    public static void main(String[] args) {
        // Extended Thread Class
        MyThread thread = new MyThread();
        thread.start();
    }
}


// 2. Runnable Implementation

/**
 * 2.1 Passing Instance of a Runnable Implementation Class to Thread Object
 */
class RunnableImplementation {
    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnable has started...");
            System.out.println("MyRunnable finished...");
        }
    }

    public static void main(String[] args) {
        // Runnable Implementation class
        Thread runnableThread = new Thread(new MyRunnable());
        runnableThread.start();
    }
}

/**
 * 2.2 Passing Runnable Instance of Anonymous class implementation to Thread Object
 */
class RunnableAsAnonymousClass {
    public static void main(String[] args) {
        // Anonymous Class Implementation
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Runnable has started...");
                System.out.println("Anonymous Runnable finished...");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

/**
 * 2.3 Passing Runnable Instance created using Lambda Expression to Thread Object
 */
class LambdaRunnableImplementation {
    public static void main(String[] args) {
        // Functional Interface (Lambda Expression)
        // Creating the variable is optional (since the value can be directly passed to the Thread constructor)
        Runnable runnable = () -> {
            System.out.println("Lambda Runnable has started...");
            System.out.println("Lambda Runnable finished...");
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}