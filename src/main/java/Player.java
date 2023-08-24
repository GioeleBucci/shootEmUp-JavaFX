import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Group {
  public float x;
  public float y;
  private float speed;
  private Gun weapon;
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

  public Rectangle getSprite(){
    return this.sprite;
  }

  public void moveUp() {
    updatePosition(0,-speed);
  }

  public void moveDown() {
    updatePosition(0,speed);
  }

  public void moveLeft() {
    updatePosition(-speed,0);
  }

  public void moveRight() {
    updatePosition(speed,0);
  }

  private void updatePosition(float x, float y) {
    this.setTranslateX(this.getTranslateX() + x);
    this.setTranslateY(this.getTranslateY() + y);
  }
}
