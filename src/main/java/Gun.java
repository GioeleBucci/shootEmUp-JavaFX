import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Gun extends Rectangle {
  public Gun(Player player) {
    super(60, 10, Color.GREY);
    this.setX(player.x + player.getSprite().getWidth() / 2);
    this.setY(player.y + player.getSprite().getHeight() / 2);
  }
  
}
