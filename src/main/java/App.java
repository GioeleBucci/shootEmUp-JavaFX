import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {    
      Group root = new Group();
      Scene scene = new Scene(root,400,600);
      stage.setScene(scene);
      stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}