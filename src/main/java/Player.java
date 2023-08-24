import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {
  private double x;
  private double y;
  private double speed;

  public Player(int x, int y, double speed){
    super(x,y,50,50);
    this.x = x;
    this.y = y;
    this.speed = 5;
    this.setFill(Color.BLUE);
  }

  public void moveUp() {
    y -= speed;
    updatePosition();
  }

  public void moveDown() {
    y += speed;
    updatePosition();
  }

  public void moveLeft() {
    x -= speed;
    updatePosition();
  }

  public void moveRight() {
    x += speed;
    updatePosition();
  }

  private void updatePosition() {
    this.setX(x);
    this.setY(y);
  }
}
