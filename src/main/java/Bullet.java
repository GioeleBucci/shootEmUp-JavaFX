import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Bullet extends Group {
  private Circle sprite;
  private float speed = 500;
  private float lifeTime;

  //timer variables
  private long lastCollisionCheckTime = 0;
  private long collisionCheckInterval = 100; // in milliseconds

  public Bullet(double xPos, double yPos, double angle, float lifeTime) {

    this.lifeTime = lifeTime;

    sprite = new Circle(xPos, yPos, 10, Color.BLACK);
    this.getChildren().add(sprite);
    App.sceneRoot.getChildren().add(this);

    instantiate(angle);
  }

  private void instantiate(double angle) {
    
    // move the bullet
    TranslateTransition moveTransition = new TranslateTransition(Duration.seconds(lifeTime), sprite);
    moveTransition.setByX(speed * Math.cos(Math.toRadians(angle)));
    moveTransition.setByY(speed * Math.sin(Math.toRadians(angle)));

    // check for collisions
    AnimationTimer collisionCheckTimer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        long currentTime = System.currentTimeMillis();

        // Check if the specified interval has passed since the last collision check
        if (currentTime - lastCollisionCheckTime >= collisionCheckInterval) {
          lastCollisionCheckTime = currentTime;
          if (isCollision()) {
            destroy();
          }
        }
      }
    };
    collisionCheckTimer.start();

    moveTransition.setOnFinished(event -> {
      collisionCheckTimer.stop();
      this.destroy();
    });

    moveTransition.play();

  }

  private boolean isCollision() {
    for (Node obj : App.sceneRoot.getChildren()) {
      if (this != obj && !(obj instanceof Gun) && !(obj instanceof Player)) { // avoid checking collision on self
        if (this.getBoundsInLocal().intersects(obj.getBoundsInLocal())) {
          // System.out.println("I collided with: " + obj);
          if (obj instanceof IDamagable) {
            ((IDamagable) obj).takeDamage(1);
          }
          return true;
        }
      }
    }
    return false;
  }

  private void destroy() {
    App.sceneRoot.getChildren().remove(this);
  }
}
