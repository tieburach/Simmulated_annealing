package sample.Model;

public class Parameters {

    private static int stopCriteria = 10;
    private static int numberOfThreads = 1;
    private static String filepath;
    private static double currentMaterialRatio;
    private static final double ZELAZO = 0.051;
    private static final double ALUMINIUM = 0.392;
    private static final double BETON = 0.0034;
    private static final double MUR = 0.0015;
    private static double startingMaximum;
    private static int timeUnit = 1;

    public static int getTimeUnit() {
        return timeUnit;
    }

    public static void setTimeUnit(int timeUnit) {
        Parameters.timeUnit = timeUnit;
    }

    public static double getStartingMaximum() {
        return startingMaximum;
    }

    public static void setStartingMaximum(double startingMaximum) {
        Parameters.startingMaximum = startingMaximum;
    }






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

    public static String getFilepath() {
        return filepath;
    }

    public static void setFilepath(String filepath) {
        Parameters.filepath = filepath;
    }

    public static double getCurrentMaterialRatio() {
        return currentMaterialRatio;
    }

    public static void setCurrentMaterialRatio(String currentMaterial) {
        if (currentMaterial.equals("ALUMINIUM")){
            currentMaterialRatio = ALUMINIUM;
        } else if (currentMaterial.equals("ZELAZO")){
            currentMaterialRatio = ZELAZO;
        } else if (currentMaterial.equals("BETON")){
            currentMaterialRatio = BETON;
        } else if (currentMaterial.equals("MUR")){
            currentMaterialRatio = MUR;
        }else {
            currentMaterialRatio = 0;
        }

    }
}
