import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Enemy_Shooter extends Enemy {

  /** Seconds between each shot */
  private int fireRate;
  private int damage = 0; //TODO

  private Player target;

  public Enemy_Shooter(Point2D pos, int hp, Player target) {
    super(pos, hp);
    this.getSprite().setFill(Color.DARKRED);
    this.target = target;

    App.sceneRoot.setOnMouseClicked(event -> {
      shoot();
    });
  }

  private double calculateAngleToTarget() {
    double xDist = target.getX() - this.getLayoutBounds().getCenterX();
    double yDist = target.getY() - this.getLayoutBounds().getCenterY();
    System.out.println("x:" + target.getX() + "  y:" + target.getY());
    // calculate the angle between cursor and player
    double angle = Math.toDegrees(Math.atan2(yDist, xDist));
    return angle;
  }

  private void shoot() {
    if (this.target == null) {
      return;
    }
    double angle = calculateAngleToTarget();
    Bullet emenyBullet = new Bullet(this.getLayoutBounds().getCenterX(), this.getLayoutBounds().getCenterY(), angle,
        this.damage, 1, false);
    emenyBullet.getSprite().setFill(Color.GREEN);
  }

}