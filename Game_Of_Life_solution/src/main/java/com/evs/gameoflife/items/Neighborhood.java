package com.evs.gameoflife.items;


import java.util.List;

public class Neighborhood {
  private List<Cell> cells;

  public Neighborhood(List<Cell> cells) {
    this.cells = cells;
  }

  public boolean isWellPopulated() {
    long count = nbrOfAliveNeighbors();
    return count >= 2 && count <= 3;
  }

  public boolean isMatable() {
    return nbrOfAliveNeighbors() == 3;
  }

  private long nbrOfAliveNeighbors() {
    return cells.stream()
                .filter(cell -> cell.health().equals(Health.ALIVE))
                .count();
  }
}
