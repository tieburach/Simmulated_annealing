package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Model.StartWindow;


public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        StartWindow startWindow = new StartWindow(primaryStage);
        startWindow.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
