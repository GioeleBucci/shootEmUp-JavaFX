import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import lombok.Getter;

public class Bullet extends Group {
  
  public static Color FIRENDLY_BULLET = Color.YELLOW;
  public static Color ENEMY_BULLET = Color.DARKRED;

  @Getter private Circle sprite;
  private float speed = 500;
  private float lifeTime;
  private int damage;
  private boolean isFriendly;

  // timer variables
  private long lastCollisionCheckTime = 0;
  private long collisionCheckInterval = 100; // in milliseconds

  public Bullet(double xPos, double yPos, double angle, int damage, float lifeTime, boolean isFriendly) {

    this.lifeTime = lifeTime;
    this.damage = damage;
    this.isFriendly = isFriendly;

    sprite = new Circle(xPos, yPos, 10, isFriendly ? FIRENDLY_BULLET : ENEMY_BULLET);
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
      if (this != obj && !(obj instanceof Gun)) { // avoid checking collision on self or gun
        // check if friendly bullet collides with player or enemy bullet collides with
        // enemy
        if ((this.isFriendly && !(obj instanceof Player)) || (!this.isFriendly && !(obj instanceof Enemy))) {
          if (this.getBoundsInLocal().intersects(obj.getBoundsInLocal())) {
            // System.out.println("I collided with: " + obj);
            if (obj instanceof IDamagable) {
              ((IDamagable) obj).takeDamage(this.damage);
            }
            return true;
          }
        }
      }
    }
    return false;
  }

  private void destroy() {
    App.sceneRoot.getChildren().remove(this);
  }
}
