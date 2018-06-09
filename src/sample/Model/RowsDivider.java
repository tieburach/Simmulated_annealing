package sample.Model;

import java.util.ArrayList;

public class RowsDivider {
    private static ArrayList <Integer> beginningList = new ArrayList<>();
    private static ArrayList <Integer> endingList = new ArrayList<>();

    public static void divideRowsIntoThreads(){
        int howManyForSingle = Matrix.getRows() /  Parameters.getNumberOfThreads();
        int current= 0;
        for (int i = 0; i < Parameters.getNumberOfThreads(); i++){
            beginningList.add(current);
            current += howManyForSingle;
            endingList.add(current-1);
        }
        if (current != Matrix.getRows()){
            endingList.remove(endingList.size()-1);
            endingList.add(Matrix.getRows()-1);
        }
    }

    public static int getBegginingRow(int threadNumber){
        return beginningList.get(threadNumber);
    }

    public static int getFinishingRow(int threadNumber){
        return endingList.get(threadNumber);
    }
}