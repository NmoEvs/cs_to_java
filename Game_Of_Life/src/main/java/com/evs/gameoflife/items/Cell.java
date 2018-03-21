package com.evs.gameoflife.items;

public class Cell {
  private Health health = Health.ALIVE;
  private Health nextHealth = health;
  private Neighborhood neighborhood = null;
  private long id;

  public Cell(long id) {
    this(id, Health.ALIVE);
  }

  public Cell(long id, Health health) {
    this.id = id;
    this.health = health;
    this.nextHealth = health;
  }

  public Health health() {
    return health;
  }

  public void neighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }

  public void age() {
    if (health == Health.ALIVE && !this.neighborhood.isWellPopulated()) {
      nextHealth = Health.DEAD;
    } else if (health == Health.DEAD && this.neighborhood.isMatable()) {
      nextHealth = Health.ALIVE;
    }
  }

  public void commit() {
    health = nextHealth;
  }

  public Health nextHealth() {
    return nextHealth;
  }

  public long id() {
    return id;
  }
}
