package com.evs.gameoflife.items;

public enum Health {
  ALIVE, DEAD;

  public String toString() {
    switch (this) {
      case ALIVE:
        return "o";
      case DEAD:
        return "_";
    }
    return "?";
  }
}