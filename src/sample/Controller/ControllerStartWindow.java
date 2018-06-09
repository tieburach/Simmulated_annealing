package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Model.MainWindow;
import sample.Model.Matrix;
import sample.Model.StartWindow;
import sample.Model.Parameters;

import java.io.File;

public class ControllerStartWindow {
    public Button browseButton, helpButton, submitButton;
    public TextField selectedFileTextField;
    public TextField selectedTemperature;
    public CheckBox celciusCheckBox, kelvinCheckBox, fahrenheitCheckBox;
    public ComboBox numberOfThreadsBox;


    //wartosci domyslne
    public void initialize(){
        selectedTemperature.setText("10");
        numberOfThreadsBox.setValue("4");
    }

    public void browseButtonAction(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Znajdz plik z macierza startowa");
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(StartWindow.getStage());
            Matrix.readFromFile(file.getAbsolutePath());
            //Matrix.getCellTemperature(2, 1);
            //Matrix.saveToFile(file.getAbsolutePath());


        } catch (Exception ignored) {
        }
    }

    public void helpButtonAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Informacje do działania programu");
        alert.setContentText("");
        alert.showAndWait();
    }

    public void submitButtonAction(ActionEvent actionEvent) {
        //ustawienie zmiennej stopu, przeliczanie
        if (kelvinCheckBox.isSelected()){
            Parameters.setStopCriteria(Integer.parseInt(selectedTemperature.getText())-273);
        } else if (fahrenheitCheckBox.isSelected()){
            Parameters.setStopCriteria((int) ((Float.parseFloat(selectedTemperature.getText())-32)* 5 / 9));
        } else {
            Parameters.setStopCriteria(Integer.parseInt(selectedTemperature.getText()));
        }

        Parameters.setNumberOfThreads(Integer.parseInt(numberOfThreadsBox.getValue().toString()));
        System.out.println("Liczba watkow:");
        System.out.println(Parameters.getNumberOfThreads());

        //przerzucenie stage
        StartWindow.getStage().close();
        MainWindow mainWindow = new MainWindow(new Stage());
        mainWindow.start();

    }

    public void celciusCheckBoxAction(ActionEvent actionEvent) {
        kelvinCheckBox.setSelected(false);
        fahrenheitCheckBox.setSelected(false);
    }

    public void kelvinCheckBoxAction(ActionEvent actionEvent) {
        celciusCheckBox.setSelected(false);
        fahrenheitCheckBox.setSelected(false);
    }

    public void fahrenheitCheckBoxAction(ActionEvent actionEvent) {
        celciusCheckBox.setSelected(false);
        kelvinCheckBox.setSelected(false);
    }
}
