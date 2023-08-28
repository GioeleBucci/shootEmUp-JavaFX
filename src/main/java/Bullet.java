import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Bullet extends Group {
  private Circle sprite;
  private float speed = 500;

  public Bullet(double xPos, double yPos, double angle, float lifeTime) {
    sprite = new Circle(xPos, yPos, 10, Color.BLACK);
    this.getChildren().add(sprite);

    TranslateTransition moveTransition = new TranslateTransition(Duration.seconds(lifeTime), sprite);
    moveTransition.setByX(speed * Math.cos(Math.toRadians(angle)));
    moveTransition.setByY(speed * Math.sin(Math.toRadians(angle)));
    moveTransition.setOnFinished(event -> {
      this.destroy();
    });

    App.sceneRoot.getChildren().add(this);
    moveTransition.play();
  }

  private void destroy() {
    App.sceneRoot.getChildren().remove(this);
  }
}
