import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputManager {

  private Player player;

  public InputManager(Scene scene, Player player) {
    this.player = player;
    scene.setOnMouseMoved(event -> {
      double mouseX = event.getX();
      double mouseY = event.getY();

      System.out.println("Mouse Position: " + mouseX + ", " + mouseY);
    });
  }

  public void Move(KeyCode keyCode) {
    if (keyCode == KeyCode.W) {
      player.moveUp();
    }
    if (keyCode == KeyCode.S) {
      player.moveDown();
    }
    if (keyCode == KeyCode.A) {
      player.moveLeft();
    }
    if (keyCode == KeyCode.D) {
      player.moveRight();
    }
  }

}
