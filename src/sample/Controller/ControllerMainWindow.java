package sample.Controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerMainWindow {

    public Button saveButton, nextGenerationButton, finishButton, continuosRunButton;
    public TextField numberOfGenerationTextField, actualTimeTextField, numberOfThreadsTextField, stopCriteriaTextField, actualMinimumTemperatureTextField;
    private  int numberOfGeneration = 1;

    public void initialize(){
        numberOfGenerationTextField.setText("Aktualna generacja: " + 1);
        numberOfThreadsTextField.setText("Ilosc procesow: " + Parameters.getNumberOfThreads());
        stopCriteriaTextField.setText("Kryterium stopu: " + Parameters.getStopCriteria() + "°C");
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        actualTimeTextField.setText("Aktualny czas: " + dateFormat.format(new Date()));
        actualMinimumTemperatureTextField.setText("Aktualna najniższa: " + Matrix.getMinTemperature() );
    }


    public void continuosRunButtonAction() {


    }

    public void saveButtonAction() {
        Matrix.saveToFile(Parameters.getFilepath());
    }

    public void nextGenerationButtonAction() {
        numberOfGenerationTextField.setText("Aktualna generacja: " + numberOfGeneration);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        actualTimeTextField.setText("Aktualny czas: " + dateFormat.format(new Date()));
        actualMinimumTemperatureTextField.setText("Aktualna najniższa: " + Matrix.getMinTemperature() );
        numberOfGeneration++;

        RowsDivider.divideRowsIntoThreads();
        for (int i = 0; i <Parameters.getNumberOfThreads() ; i++) {
            CalculationThread calculationThread = new CalculationThread(i, RowsDivider.getBegginingRow(i), RowsDivider.getFinishingRow(i));
            calculationThread.start();
        }
    }

    public void finishButtonAction() { ;
        HeatMapColors.saveImage(HeatMapColors.createColorScaleImage(), "JAKIESCOS.png");
    }
}
