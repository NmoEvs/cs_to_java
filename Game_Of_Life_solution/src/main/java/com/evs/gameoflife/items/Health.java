package com.evs.gameoflife.items;

import java.util.ResourceBundle;

public enum Health {
  ALIVE, DEAD;
  private static final ResourceBundle labels = ResourceBundle.getBundle("com.evs.gameoflife.health");
  public String toString() {
    switch (this) {
      case ALIVE: return labels.getString("alive");
      case DEAD: return labels.getString("dead");
    }
    return "?";
  }
}
