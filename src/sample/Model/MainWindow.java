package sample.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class MainWindow {
    private Stage mainWindowStage;

    public MainWindow(Stage mainWindowStage){
        this.mainWindowStage = mainWindowStage;
    }

    public void start(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../View/MainWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 900, 700);
        String css = Main.class.getResource("css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        mainWindowStage.setTitle("Simulated annealing - working");
        mainWindowStage.setScene(scene);
        mainWindowStage.show();
    }

}
