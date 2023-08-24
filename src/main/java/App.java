import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  public static final int SCREEN_WIDTH = 400;
  public static final int SCREEN_HEIGHT = 600;

  private PlayerManager playerManager;
  private Player player;

  @Override
  public void start(Stage stage) {
    Group root = new Group();
    Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    stage.setScene(scene);

    player = new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 5);
    root.getChildren().add(player);

    // game init
    playerManager = new PlayerManager(player);

    scene.setOnKeyPressed(event -> {
      playerManager.Move(event.getCode());
      //System.out.println("Key Pressed: " + event.getCode());
    });
    //

    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}