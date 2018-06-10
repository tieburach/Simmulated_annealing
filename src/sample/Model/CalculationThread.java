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
    }

    public void run() {
        Matrix.nextGeneration(beginningRow, finishingRow);
    }

    public void start () {
        if (thread == null) {
            thread = new Thread (this, threadIndex);
            thread.start ();
        }
    }
}
