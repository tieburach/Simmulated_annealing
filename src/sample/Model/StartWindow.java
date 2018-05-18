package sample.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class StartWindow {
    private static Stage startWindowStage = null;


    public StartWindow(Stage startWindowStage){
        StartWindow.startWindowStage = startWindowStage;
    }

    public void start(){
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("../View/StartWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 500, 600);
        String css = Main.class.getResource("css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        startWindowStage.setTitle("Simulated annealing - start");
        startWindowStage.setScene(scene);
        startWindowStage.show();
    }



    private void setStage(Stage startWindowStage){
        StartWindow.startWindowStage = startWindowStage;
    }

    public static Stage getStage(){
        return startWindowStage;
    }
}
