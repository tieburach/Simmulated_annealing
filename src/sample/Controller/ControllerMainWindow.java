package sample.Controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import sample.Model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerMainWindow {

    public Button saveButton, nextGenerationButton, finishButton, continuosRunButton;
    public TextField numberOfGenerationTextField, actualTimeTextField, numberOfThreadsTextField, stopCriteriaTextField, actualMinimumTemperatureTextField;
    public StackPane imagePane;
    public ImageView imageView;
    public Button stopButton;
    private int numberOfGeneration = 1;
    private boolean continuosflg = true;

    public void initialize() {
        numberOfGenerationTextField.setText("Aktualna generacja: " + 1);
        numberOfThreadsTextField.setText("Ilosc procesow: " + Parameters.getNumberOfThreads());
        stopCriteriaTextField.setText("Kryterium stopu: " + Parameters.getStopCriteria() + "°C");
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        actualTimeTextField.setText("Aktualny czas: " + dateFormat.format(new Date()));
        actualMinimumTemperatureTextField.setText("Aktualna najniższa: " + Matrix.getMinTemperature());
    }


    public void continuosRunButtonAction() {
        stopButton.setVisible(true);
        continuosflg = true;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (Matrix.getMinTemperature()>Parameters.getStopCriteria() && continuosflg) {
                    nextGenerationButtonAction();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();

    }

    public void saveButtonAction() {
        Matrix.saveToFile(Parameters.getFilepath() + "\\macierzGeneracji" + (numberOfGeneration-1) + ".txt");
        HeatMapColors.saveImage(HeatMapColors.createColorScaleImage(), Parameters.getFilepath() + "\\generacja" +(numberOfGeneration-1) +".png");
    }

    public void nextGenerationButtonAction() {
        numberOfGenerationTextField.setText("Aktualna generacja: " + numberOfGeneration);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        actualTimeTextField.setText("Aktualny czas: " + dateFormat.format(new Date()));
        actualMinimumTemperatureTextField.setText("Aktualna najniższa: " + Matrix.getMinTemperature());
        numberOfGeneration++;

        CalculationThread calculationThread = null;
        for (int i = 0; i < Parameters.getNumberOfThreads(); i++) {
            calculationThread = new CalculationThread(i, RowsDivider.getBegginingRow(i), RowsDivider.getFinishingRow(i));
            calculationThread.start();
        }
        if (calculationThread != null && !calculationThread.isAlive()) {
            imageView.setImage(SwingFXUtils.toFXImage(HeatMapColors.createColorScaleImage(), null));
        }
    }

    public void finishButtonAction() {
        System.exit(0);
    }

    public void stopButtonAction(ActionEvent actionEvent) {
        continuosflg = false;
        stopButton.setVisible(false);
    }
}
