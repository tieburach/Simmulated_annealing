package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Model.MainWindow;
import sample.Model.StartWindow;
import sample.Model.Parameters;

public class ControllerStartWindow {
    public Button browseButton;
    public Button helpButton;
    public Button submitButton;
    public TextField selectedFileTextField;
    public TextField selectedTemperature;
    public CheckBox celciusCheckBox;
    public CheckBox kelvinCheckBox;
    public CheckBox fahrenheitCheckBox;
    public ComboBox numberOfThreadsBox;


    //wartosci domyslne
    public void initialize(){
        selectedTemperature.setText("10");
        numberOfThreadsBox.setValue("4");
    }

    public void browseButtonAction(ActionEvent actionEvent) {

    }

    public void helpButtonAction(ActionEvent actionEvent) {

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
