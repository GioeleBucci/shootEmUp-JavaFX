import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  public static final int SCREEN_WIDTH = 400;
  public static final int SCREEN_HEIGHT = 600;
  public static final Group sceneRoot = new Group();
  private InputManager playerManager;
  private Player player;

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(sceneRoot, SCREEN_WIDTH, SCREEN_HEIGHT);
    stage.setScene(scene);

    player = new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 5);
    sceneRoot.getChildren().addAll(player);

    // game init
    playerManager = new InputManager(scene,player);

    scene.setOnKeyPressed(event -> {
      playerManager.Move(event.getCode());
      // System.out.println("Key Pressed: " + event.getCode());
    });

    //spawn enemy
    new Enemy(new Point2D(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3), 20);
    //

    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}