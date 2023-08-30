import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Group;

public class WaveSpawner extends Group {

  private int amount;
  private int spawned = 0;

  // timer variables
  private long lastCollisionCheckTime = 0;
  private long msBetweenSpawns = 100; // in milliseconds

  public WaveSpawner(int amount, float secBetweenSpawns) {
    this.amount = amount;
    this.msBetweenSpawns = (long) secBetweenSpawns * 1000;

    App.sceneRoot.getChildren().add(this);

    startWave();
  }

  public void startWave() {

    AnimationTimer timer = new AnimationTimer() {

      @Override
      public void handle(long now) {
        long currentTime = System.currentTimeMillis();

        // Check if the specified interval has passed since the last collision check
        if (currentTime - lastCollisionCheckTime >= msBetweenSpawns) {

          lastCollisionCheckTime = currentTime;

          spawnEnemy();
          spawned++;

          // check if all enemies have been spawned
          if (spawned == amount) {
            stop();
            return;
          }

        }
      }
    };
    timer.start();
  }

  public void destroy() {
    App.sceneRoot.getChildren().remove(this);
  }

  private void spawnEnemy() {
    Random random = new Random();

    double boxWidth = 50;
    double boxHeight = 50;

    double x = random.nextDouble() * (App.SCREEN_WIDTH - boxWidth);
    double y = random.nextDouble() * (App.SCREEN_HEIGHT - boxHeight);

    new Enemy(new Point2D(x, y), 1);
  }
  
}