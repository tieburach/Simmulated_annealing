package sample.Model;

public class Parameters {

    private static int stopCriteria = 10;
    private static int numberOfThreads = 1;
    private static String filepath;

    public static int getStopCriteria() {
        return stopCriteria;
    }

    public static void setStopCriteria(int stopCriteria) {
        Parameters.stopCriteria = stopCriteria;
    }

    public static int getNumberOfThreads() {
        return numberOfThreads;
    }

    public static void setNumberOfThreads(int numberOfThreads) {
        Parameters.numberOfThreads = numberOfThreads;
    }
}
