package CoreJava.JavaMultithreading.codes;

/**
 * Thread.stop() method is deprecated and should not be used.
 * Alternate to stop a thread
 */
public class ThreadStop {

    public static void main(String[] args) {
        StoppableRunnable runnable = new StoppableRunnable();
        Thread thread = new Thread(runnable, "Stoppable Thread Example");
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("request stop");
        // Note: Stop is done using the Runnable Object
        runnable.requestStop();
        System.out.println("stop requested");
    }

    public static class StoppableRunnable implements Runnable {
        private boolean stopRequested = false;

        public synchronized void requestStop() {
            // Note: if `this.` keyword is not added then compiler will add it
            stopRequested = true;
        }

        public synchronized boolean isStopRequested() {
            return stopRequested;
        }

        private void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("Stoppable Runnable has started...");
            while (!isStopRequested()) {
                sleep(1000);
                System.out.println("running...");
            }
            System.out.println("Stoppable Runnable has stopped...");
        }
    }
}
