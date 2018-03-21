package com.evs.gameoflife;

import com.evs.gameoflife.items.Neighborhood;

import java.util.Arrays;

class NeighborhoodFake extends Neighborhood {
  private boolean matable;
  private boolean wellPopulated;

  public NeighborhoodFake(boolean matable, boolean wellPopulated) {
    super(Arrays.asList());
    this.matable = matable;
    this.wellPopulated = wellPopulated;
  }

  @Override
  public boolean isMatable() { return this.matable; }
  public boolean isWellPopulated() { return this.wellPopulated; }
}
