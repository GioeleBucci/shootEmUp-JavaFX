import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;

public class Enemy extends Group{

  @Getter private Rectangle sprite;

  public Enemy(Point2D pos, int hp){
    sprite = new Rectangle(pos.getX(), pos.getY(), 50, 50);
    sprite.setFill(Color.RED);
    this.getChildren().add(sprite);
    App.sceneRoot.getChildren().add(this);
  }

}
