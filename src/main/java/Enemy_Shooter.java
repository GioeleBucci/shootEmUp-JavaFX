import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Enemy_Shooter extends Enemy {

  /** Seconds between each shot */
  private int fireRate;
  private int damage = 0; //TODO

  private Group target;

  public Enemy_Shooter(Point2D pos, int hp, Group target) {
    super(pos, hp);
    this.getSprite().setFill(Color.DARKRED);
    this.target = target;

    App.sceneRoot.setOnMouseClicked(event -> {
      System.out.println("pwe pwe");
      shoot();
    });
  }

  private double calculateAngleToTarget() {
    double xDist = this.getLayoutBounds().getCenterX() - target.getLayoutBounds().getCenterX();
    double yDist = this.getLayoutBounds().getCenterY() - target.getLayoutBounds().getCenterY();
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
        this.damage, 1);
    emenyBullet.getSprite().setFill(Color.GREEN);
  }

}