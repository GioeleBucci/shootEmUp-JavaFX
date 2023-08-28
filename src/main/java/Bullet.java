import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Bullet extends Group {
  private Circle sprite;

  public Bullet(double xPos, double yPos, float lifeTime) {
    sprite = new Circle(xPos, yPos, 10, Color.BLACK);
    this.getChildren().add(sprite);
    // Schedule bullet removal
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(lifeTime), event -> {
      this.destroy();
    }));
    timeline.setCycleCount(1);
    App.sceneRoot.getChildren().add(this);
    timeline.play();
  }

  private void destroy() {
    System.out.println("bullet destroyed");
    App.sceneRoot.getChildren().remove(this);
  }
}
