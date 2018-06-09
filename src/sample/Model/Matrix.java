package sample.Model;

import java.io.*;

public class Matrix {
    private static double[][] matrix;

    public static void setRows(int rows) {
        Matrix.rows = rows;
    }

    private static int columns;
    private static int rows;

    public Matrix(int rows, int columns) {
        matrix = new double[rows][columns];
    }

    public static void readFromFile(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = br.readLine();
            rows = Integer.parseInt(line);
            line = br.readLine();
            columns = Integer.parseInt(line);
            line = br.readLine();
            matrix = new double[rows][columns];
            while (line != null) {
                for (int i = 0; i < rows; i++) {
                    String[] parts = line.split(" ");
                    for (int j = 0; j < columns; j++) {
                        setCellTemperature(i, j, Double.parseDouble(parts[j]));
                    }
                    line = br.readLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void saveToFile(String filepath) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(filepath));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(rows + "\n" + columns + "\n");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    bw.write(getCellTemperature(i, j) + " ");
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static double getCellTemperature(int row, int column) {
        return matrix[row][column];
    }

    private static void setCellTemperature(int row, int column, double value) {
        matrix[row][column] = value;
    }

    static double[][] getMatrix() {
        return matrix;
    }

    public static int getColumns() {
        return columns;
    }

    public static int getRows() {
        return rows;
    }

    public static double getMinTemperature() {
        double min = getCellTemperature(0, 0);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (getCellTemperature(i, j) < min) {
                    min = getCellTemperature(i, j);
                }
            }
        }
        return min;
    }

    public static double getMaxTemperature() {
        double max = getCellTemperature(0, 0);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (getCellTemperature(i, j) > max) {
                    max = getCellTemperature(i, j);
                }
            }
        }
        return max;
    }


    public static void nextGeneration(int rowStart, int rowEnd) {
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = 0; j < columns; j++) {
                setCellTemperature(i, j, calculateNewTemperature(i, j)-1);
            }
        }
    }

    private static double calculateNewTemperature(int row, int col) {
        double currentTemperature = getCellTemperature(row, col);
        try {
            double sumOfNeighbours = getCellTemperature(row - 1, col)
                    + getCellTemperature(row + 1, col)
                    + getCellTemperature(row, col - 1)
                    + getCellTemperature(row, col + 1);
            currentTemperature = sumOfNeighbours/4;
        }catch (Exception e){
            //System.out.println("Warunek brzegowy");
        }
        return currentTemperature;
    }

    public static void createThreads(){

    }
}
