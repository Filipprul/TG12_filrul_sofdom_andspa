package game;

public class TerminalGame {
  private Grid grid;
  public TerminalGame(Grid grid) {
    this.grid = grid;
  }

  public void render() {
    this.grid.zeichneGrid();
  }
}
