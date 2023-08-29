import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Rotate;

import java.lang.Math;

public class InputManager {

  boolean isUp, isDown, isLeft, isRight; // if the player is moving in the current direction
  @SuppressWarnings("unused")
  private Player player;

  // timer variables
  private long prevTime = 0;
  private long frameDuration = 33;

  public InputManager(Scene scene, Player player) {
    this.player = player;
    Rotate rotation = new Rotate(0);
    player.weapon.getTransforms().add(rotation);

    /* ------------------------ PLAYER ----------------------- */
    scene.setOnKeyPressed(event -> {
      KeyCode key = event.getCode();
      isUp = (key == KeyCode.W);
      isLeft = (key == KeyCode.A);
      isDown = (key == KeyCode.S);
      isRight = (key == KeyCode.D);
      // System.out.println("Key Pressed: " + event.getCode());
    });

    scene.setOnKeyReleased(event -> {
      KeyCode key = event.getCode();
      if (key == KeyCode.W)
        isUp = false;
      if (key == KeyCode.A)
        isLeft = false;
      if (key == KeyCode.S)
        isDown = false;
      if (key == KeyCode.D)
        isRight = false;
      // System.out.println("Key Pressed: " + event.getCode());
    });

    AnimationTimer animPlayer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - prevTime >= frameDuration) {
          prevTime = currentTime;
          if (isUp)
            player.moveUp();
          if (isDown)
            player.moveDown();
          if (isLeft)
            player.moveLeft();
          if (isRight)
            player.moveRight();
        }
      }
    };
    animPlayer.start();

    /*
     * ------------------------ GUN --------------------------
     * The gun must be oriented in the direction of the mouse cursor.
     */
    scene.setOnMouseMoved(event -> {
      // get cursor distance from player
      double xDist = event.getX() - player.getX();
      double yDist = event.getY() - player.getY();
      // calculate the angle between cursor and player
      double angle = Math.toDegrees(Math.atan2(yDist, xDist));
      // System.out.println(angle);
      rotation.setAngle(angle);
      player.weapon.angle = angle;
    });

    scene.setOnMouseClicked(evemt -> {
      player.weapon.shoot();
    });
  }

}
