package com.evs.gameoflife.items;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

public class NeighborhoodTest {
  @Test
  public void itIsWellPopulatedWhenItHasTwoOrThreeAliveCells() {
    Neighborhood subject = new Neighborhood(
      IntStream.rangeClosed(1, 8)
        .mapToObj((idx) -> new Cell(0, idx <= 2 ? Health.ALIVE : Health.DEAD))
        .collect(Collectors.toList())
    );
    assertTrue(subject.isWellPopulated());
  }

  @Test
  public void itIsMatableWhenItHasExactlyThreeAliveCells() {
    Neighborhood subject = new Neighborhood(
      IntStream.rangeClosed(1, 8)
        .mapToObj((idx) -> new Cell(0, idx <= 3 ? Health.ALIVE : Health.DEAD))
        .collect(Collectors.toList())
    );
    assertTrue(subject.isMatable());
  }
}
