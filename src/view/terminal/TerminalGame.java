package view.terminal;

import core.Grid;

public class TerminalGame {
  private Grid grid;
  public TerminalGame(Grid grid) {
    this.grid = grid;
    this.grid.zeicheGrid();
  }
}
