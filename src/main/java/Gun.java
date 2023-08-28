import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import lombok.Getter;

public class Gun extends Group {

  @Getter
  private Rectangle sprite;

  public Gun(Player player) {
    sprite = new Rectangle(60, 10, Color.GREY);
    this.getChildren().add(sprite);
    // move the handle where the player is
    this.setTranslateX(player.getX() + player.getSprite().getWidth() / 2);
    this.setTranslateY(player.getY() + player.getSprite().getHeight() / 2);
    this.sprite.setTranslateX(this.sprite.getTranslateX());
    this.sprite.setTranslateY(this.sprite.getTranslateY());
  }
}
