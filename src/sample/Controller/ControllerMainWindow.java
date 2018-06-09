package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Model.Matrix;
import sample.Model.Parameters;

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


    public void continuosRunButtonAction(ActionEvent actionEvent) {
    }

    public void saveButtonAction(ActionEvent actionEvent) {
        Matrix.saveToFile(Parameters.getFilepath());
    }

    public void nextGenerationButtonAction(ActionEvent actionEvent) {
        Matrix.nextGeneration(0, Matrix.getRows());
        numberOfGenerationTextField.setText("Aktualna generacja: " + numberOfGeneration);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        actualTimeTextField.setText("Aktualny czas: " + dateFormat.format(new Date()));
        actualMinimumTemperatureTextField.setText("Aktualna najniższa: " + Matrix.getMinTemperature() );
        numberOfGeneration++;
    }

    public void finishButtonAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
