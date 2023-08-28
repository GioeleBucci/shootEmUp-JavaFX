import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Rotate;

import java.lang.Math;

public class InputManager {

  private Player player;

  public InputManager(Scene scene, Player player) {
    this.player = player;
    Rotate rotation = new Rotate(0);
    player.weapon.getTransforms().add(rotation);

    /*
     * MOVE GUN
     * The gun must be oriented in the direction of the mouse cursor.
     */
    scene.setOnMouseMoved(event -> {
      // get cursor distance from player
      double xDist = event.getX() - player.getX();
      double yDist = event.getY() - player.getY();
      // calculate the angle between cursor and player
      double angle = Math.toDegrees(Math.atan2(yDist, xDist));
      System.out.println(angle);
      rotation.setAngle(angle);
    });

    scene.setOnMouseClicked(evemt -> {
      player.weapon.shoot();
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
