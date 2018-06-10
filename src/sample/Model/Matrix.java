package sample.Model;

import java.io.*;

public class Matrix {
    private static double[][] matrix;

    private static int columns;
    private static int rows;

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
                Parameters.setStartingMaximum(getMaxTemperature());
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


    static double getCellTemperature(int row, int column) {
        return matrix[row][column];
    }

    private static void setCellTemperature(int row, int column, double value) {
        matrix[row][column] = value;
    }

    static int getColumns() {
        return columns;
    }

    static int getRows() {
        return rows;
    }

    public static double getMinTemperature() {
        double sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sum += Matrix.getCellTemperature(i,j);
            }
        }
        return sum/(Matrix.getRows()*Matrix.getColumns());
    }

    private static double getMaxTemperature() {
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


    static void nextGeneration(int rowStart, int rowEnd) {
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = 0; j < columns; j++) {
                setCellTemperature(i, j, calculateNewTemperature(i, j));
            }
        }
    }

    private static double calculateNewTemperature(int row, int col) {
        /*
        Temp =T.Otoczenia + (T.Poprzednia – T.Otoczenia) exp (-kt), gdzie t =1;
         */
        double newTemperature, ourResultProbability;
        double random = Math.random();
        double temperatureOfSurroundings = calculateSumOfNeighbours(row, col);
        newTemperature = temperatureOfSurroundings + (getCellTemperature(row, col) - temperatureOfSurroundings)
                * Math.exp(-(Parameters.getCurrentMaterialRatio() * Parameters.getTimeUnit()));
        ourResultProbability = calculateResultProbability(row, col);
        double proportion = ourResultProbability > Matrix.getCellTemperature(row, col) ? Matrix.getCellTemperature(row, col) / ourResultProbability : ourResultProbability / Matrix.getCellTemperature(row, col);

        if (proportion > random) {
            return newTemperature;
        } else {
          double neighbourValue;
          try{
              neighbourValue = Matrix.getCellTemperature(row-1, col);
          } catch (Exception e){
              neighbourValue = Matrix.getCellTemperature(row+1, col);
          }
          return neighbourValue;
        }

    }


    private static double calculateResultProbability(int row, int col) {
        double current, result = 0;
        int divider = 0;
        for (int i = row - 2; i <= row + 2; i++) {
            for (int j = col - 2; j <= col + 2; j++) {
                try {
                    result += Matrix.getCellTemperature(i, j);
                    divider++;
                } catch (Exception ignored) {
                }
            }
        }
        return result / divider;
    }

    public static double calculateSumOfNeighbours(int row, int col) {
        double currentTemperature, sumOfNeighbours = 0;
        double first, second, third, fourth;
        try {
            first = getCellTemperature(row - 1, col);
        } catch (Exception e) {
            first = 0;
        }
        try {
            second = getCellTemperature(row + 1, col);
        } catch (Exception e) {
            second = 0;
        }
        try {
            third = getCellTemperature(row, col - 1);
        } catch (Exception e) {
            third = 0;
        }
        try {
            fourth = getCellTemperature(row, col + 1);
        } catch (Exception e) {
            fourth = 0;
        }
        sumOfNeighbours = first + second + third + fourth;
        currentTemperature = sumOfNeighbours / 4;
        return currentTemperature;
    }
}
