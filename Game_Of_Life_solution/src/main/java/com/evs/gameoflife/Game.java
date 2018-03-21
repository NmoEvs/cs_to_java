package com.evs.gameoflife;

import com.evs.gameoflife.items.Health;
import com.evs.gameoflife.items.World;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.toIntExact;

public class Game {

  public void play(long width, long height) {
    try {
      World world = new World(
          width,
          height,
          generateRandomInput(width * height)
      );
      long day = 1;
      System.out.println("Generation " + day++);
      System.out.println(world);
      while(true) {
        world.evolve();
        Thread.sleep(100);
        System.out.println("Generation " + day++);
        System.out.println(world.toString());
      }
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private List<Health> generateRandomInput(long size) {
    Random rand = new Random();
    return IntStream.range(0, toIntExact(size))
                    .parallel()
                    .mapToObj(idx -> rand.nextBoolean() ? Health.ALIVE : Health.DEAD )
                    .collect(Collectors.toList());
  }
}
