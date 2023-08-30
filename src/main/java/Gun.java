import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;

public class Gun extends Group {

  @Getter
  private Rectangle sprite;
  public double angle = 0;

  public Gun(Player player) {
    sprite = new Rectangle(60, 20, Color.GREY);
    this.getChildren().add(sprite);
    // move the handle where the player is
    this.setTranslateX(player.getX() + player.getSprite().getWidth() / 2 - this.sprite.getWidth() / 2);
    this.setTranslateY(player.getY() + player.getSprite().getHeight() / 2 - this.sprite.getHeight() / 2);
    this.sprite.setTranslateX(this.sprite.getTranslateX());
    this.sprite.setTranslateY(this.sprite.getTranslateY());
  }

  public void shoot() {
    new Bullet(this.getLocalToSceneTransform().getTx(), this.getLocalToSceneTransform().getTy(), this.angle, 1, 1,
        true);
  }
}
