import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;

public class Enemy extends Group implements IDamagable {

  @SuppressWarnings("unused")
  private int maxHealth, currentHealth;

  @Getter
  private Rectangle sprite;

  public Enemy(Point2D pos, int hp) {
    
    this.maxHealth = hp;
    this.currentHealth = hp;

    sprite = new Rectangle(pos.getX(), pos.getY(), 50, 50);
    sprite.setFill(Color.RED);
    this.getChildren().add(sprite);
    App.sceneRoot.getChildren().add(this);

    System.out.println("enemy: " + this.sprite.getBoundsInLocal());
  }

  @Override
  public void takeDamage(int amount) {
    System.out.println("hp: " + this.currentHealth);
    this.currentHealth -= amount;
    if (currentHealth <= 0) {
      die();
    }
  }

  @Override
  public int getCurrentHealth() {
    return this.currentHealth;
  }

  @Override
  public void die() {
    App.sceneRoot.getChildren().remove(this);
  }

}
