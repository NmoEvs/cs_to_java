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
    //TO_OPTIMIZE
    //Use a stream to call filter and count method
    int count = 0;
    for (int i = 0; i < cells.size(); i++) {
      Cell cell = cells.get(i);
      if (cell.health().equals(Health.ALIVE)) {
        count = count + 1;
      }
    }
    return count;
  }
}
