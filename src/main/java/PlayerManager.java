import javafx.scene.input.KeyCode;

public class PlayerManager {

  private Player player;

  public PlayerManager(Player player) {
    this.player = player;
  }

  public void Move(KeyCode keyCode) {
    if (keyCode == KeyCode.W) {
      player.moveUp();
    }
    if (keyCode == KeyCode.S) {
      player.moveDown();
    }
    if (keyCode == KeyCode.A) {
      player.moveLeft();
    }
    if (keyCode == KeyCode.D) {
      player.moveRight();
    }
  }

}
