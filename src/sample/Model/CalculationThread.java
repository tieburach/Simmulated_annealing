package sample.Model;

public class CalculationThread extends Thread {
    private Thread thread;
    private String threadIndex;
    private int beginningRow;
    private int finishingRow;

    public CalculationThread(int threadIndex, int beginningRow, int finishingRow) {
        this.threadIndex = "" + threadIndex;
        this.beginningRow = beginningRow;
        this.finishingRow = finishingRow;
        System.out.println("Creating " + threadIndex);

    }

    public void run() {
        System.out.println("Running " +  threadIndex );
        Matrix.nextGeneration(beginningRow, finishingRow);
        System.out.println("Thread " +  threadIndex + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadIndex );
        if (thread == null) {
            thread = new Thread (this, threadIndex);
            thread.start ();
        }
    }
}
