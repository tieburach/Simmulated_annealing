package sample.Controller;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Model.*;

import java.io.File;

public class ControllerStartWindow {
    public Button browseButton, helpButton, submitButton;
    public TextField selectedFileTextField, selectedTemperature;
    public CheckBox celciusCheckBox, kelvinCheckBox, fahrenheitCheckBox;
    public ComboBox numberOfThreadsBox;
    public ComboBox rodzajMaterialu;
    public TextField timeUnitTextField;


    //wartosci domyslne
    public void initialize() {
        selectedTemperature.setText("10");
        numberOfThreadsBox.setValue("4");
    }

    public void browseButtonAction() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Znajdz plik z macierza startowa");
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(StartWindow.getStage());
            Matrix.readFromFile(file.getAbsolutePath());
            Parameters.setFilepath(file.getParentFile().getAbsolutePath());
            selectedFileTextField.setText("" + file.getAbsolutePath());
        } catch (Exception ignored) {
        }
    }

    public void helpButtonAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Informacje do działania programu");
        alert.setContentText("");
        alert.showAndWait();
    }

    public void submitButtonAction() {
        //ustawienie zmiennej stopu, przeliczanie

        try {
            if (kelvinCheckBox.isSelected()) {
                Parameters.setStopCriteria(Integer.parseInt(selectedTemperature.getText()) - 273);
            } else if (fahrenheitCheckBox.isSelected()) {
                Parameters.setStopCriteria((int) ((Float.parseFloat(selectedTemperature.getText()) - 32) * 5 / 9));
            } else {
                Parameters.setStopCriteria(Integer.parseInt(selectedTemperature.getText()));
            }
            Parameters.setCurrentMaterialRatio(rodzajMaterialu.getValue().toString());
            Parameters.setNumberOfThreads(Integer.parseInt(numberOfThreadsBox.getValue().toString()));
            Parameters.setTimeUnit(Integer.parseInt(timeUnitTextField.getText()));
            RowsDivider.divideRowsIntoThreads();
            //przerzucenie stage
            StartWindow.getStage().close();
            MainWindow mainWindow = new MainWindow(new Stage());
            mainWindow.start();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blad");
            alert.setHeaderText("Nie mozna przejsc dalej");
            alert.setContentText("Ktorys z parametrow nie zostal wypelniony. Wroc do programu i uzupełnij wszystkie parametry.");
            alert.showAndWait();
        }
    }

    public void celciusCheckBoxAction() {
        kelvinCheckBox.setSelected(false);
        fahrenheitCheckBox.setSelected(false);
    }

    public void kelvinCheckBoxAction() {
        celciusCheckBox.setSelected(false);
        fahrenheitCheckBox.setSelected(false);
    }

    public void fahrenheitCheckBoxAction() {
        celciusCheckBox.setSelected(false);
        kelvinCheckBox.setSelected(false);
    }
}
