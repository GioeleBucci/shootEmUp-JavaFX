import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Enemy_Shooter extends Enemy {

  /** Milliseconds between each shot */
  private long fireRate = 3000;
  private int damage = 0; // TODO change damage to 1

  // timer variables
  private long last = 0;

  private Player target;

  public Enemy_Shooter(Point2D pos, int hp, Player target) {
    super(pos, hp);
    this.getSprite().setFill(Color.DARKRED);
    this.target = target;

    init();
  }

  private void init() {
    AnimationTimer anim = new AnimationTimer() {
      @Override
      public void handle(long now) {
        long current = System.currentTimeMillis();
        if (current - last >= fireRate){
          last = current;
          shoot();
        }
      }
    };
    anim.start();
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
  }

}