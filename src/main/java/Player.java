import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lombok.Getter;

public class Player extends Group implements IDamagable {
  @Getter
  private float x;
  @Getter
  private float y;
  @Getter
  private float speed;
  private int maxHealth = 3;
  private int currentHealth = maxHealth;
  public Gun weapon;
  @Getter
  private Rectangle sprite;

  public Player(int x, int y, float speed) {
    this.x = x;
    this.y = y;
    this.speed = speed;

    sprite = new Rectangle(x, y, 50, 50);
    sprite.setFill(Color.BLUE);
    weapon = new Gun(this);

    this.getChildren().addAll(sprite, weapon);
  }

  public void moveUp() {
    updatePosition(0, -speed);
  }

  public void moveDown() {
    updatePosition(0, speed);
  }

  public void moveLeft() {
    updatePosition(-speed, 0);
  }

  public void moveRight() {
    updatePosition(speed, 0);
  }

  private void updatePosition(float x, float y) {
    this.x += x;
    this.y += y;
    this.setTranslateX(this.getTranslateX() + x);
    this.setTranslateY(this.getTranslateY() + y);
  }

  @Override
  public void takeDamage(int amount) {

    damageFlickerAnim();

    currentHealth -= amount;
    System.out.println("You took " + amount + " damage");
    if (currentHealth <= 0) {
      die();
    }
  }

  private void damageFlickerAnim() {
    Paint originalColor = sprite.getFill();
    Paint flickerColor = Color.GREY;

    Timeline timeline = new Timeline(
        new KeyFrame(Duration.millis(200), event -> {
          if (sprite.getFill() == originalColor) {
            sprite.setFill(flickerColor);
          } else {
            sprite.setFill(originalColor);
          }
        }));

    timeline.setCycleCount(3);
    timeline.play();
    timeline.setOnFinished(event -> sprite.setFill(originalColor)); // Restore original color after animation
  }

  @Override
  public int getCurrentHealth() {
    return this.currentHealth;
  }

  @Override
  public void die() {
    System.out.println("you die!");
    this.sprite.setFill(Color.BLACK);
    javafx.application.Platform.exit(); // terminate the program
  }
}
