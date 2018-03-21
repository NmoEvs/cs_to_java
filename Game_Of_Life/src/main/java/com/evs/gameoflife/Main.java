package com.evs.gameoflife;

import com.evs.gameoflife.items.Health;
import com.evs.gameoflife.items.World;

import java.util.Arrays;
import java.util.List;

public class Main {

  long width = 10;
  long height = 10;

  public static void main(String[] args) {
    Main main = new Main();
    main.run();
  }

  private void run() {
    try {
      World world = new World(
          width,
          height,
          generateInitalState()
      );

      System.out.println(world);
      while (true) {
        world.evolve();
        Thread.sleep(100);
        System.out.println(world.toString());
        System.out.flush();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private List<Health> generateInitalState() {
    return Arrays.asList(
        Health.ALIVE, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.ALIVE, Health.ALIVE, Health.DEAD, Health.DEAD, Health.DEAD,
        Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.ALIVE, Health.ALIVE, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD,
        Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD,
        Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD,
        Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD,
        Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD,
        Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD,
        Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD,
        Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD,
        Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.ALIVE, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD
    );
  }
}
