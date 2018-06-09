package sample.Model;

import java.io.*;

public class Matrix {
    private static double[][] matrix;
    private static int columns;
    private static int rows;

    public Matrix (int rows, int columns){
        matrix = new double[rows][columns];
    }

    public static void readFromFile (String filepath){
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
                    for (int j = 0; j < columns ; j++) {
                        setCellTemperature(i, j, Double.parseDouble(parts[j]));
                    }
                    line = br.readLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void saveToFile(String filepath){
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public static double getCellTemperature(int row, int column){
        return matrix[row][column];
    }

    public static void setCellTemperature(int row, int column, double value){
        matrix[row][column] = value;
    }

    public static double[][] getMatrix() {
        return matrix;
    }

    public static int getColumns() {
        return columns;
    }

    public static int getRows() {
        return rows;
    }


}
