package com.evs.gameoflife.items;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class WorldTest {
  @Test
  public void itIsBuiltAccordingDimensions() {
    try {
      World subject = new World(10, 10);
      assertEquals(100, subject.populationSize());
    } catch (Exception e) { fail("Exception shouldn't be thrown: " + e.getMessage()); }
  }

  @Test
  public void itCanBeBuiltFromInitialState() {
    try {
      World subject = new World(3, 3, Arrays.asList(Health.ALIVE, Health.ALIVE, Health.ALIVE, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD));
      assertEquals(3, subject.populationSizeOf(Health.ALIVE));
    } catch (Exception e) { fail("Exception shouldn't be thrown: " + e.getMessage()); }
  }

  @Test
  public void itCanEvolve() {
    try {
      World subject = new World(3, 3, Arrays.asList(Health.ALIVE, Health.ALIVE, Health.ALIVE, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD, Health.DEAD));
      subject.evolve();
      assertEquals(9, subject.populationSizeOf(Health.ALIVE));
    } catch (Exception e) { fail("Exception shouldn't be thrown: " + e.getMessage()); }
  }

  @Test
  public void itBecomesFullofDeadWhenAllIsAlive() {
    try {
      World subject = new World(3, 3);
      subject.evolve();
      assertEquals(9, subject.populationSizeOf(Health.DEAD));
    } catch (Exception e) { fail("Exception shouldn't be thrown: " + e.getMessage()); }
  }

  @Test
  public void itIsDisplayed() {
    try {
      World subject = new World(3, 3);
      String output = "ooo" +
                      System.lineSeparator() +
                      "ooo" +
                      System.lineSeparator() +
                      "ooo";
      assertEquals(output, subject.toString());
    } catch (Exception e) { fail("Exception shouldn't be thrown: " + e.getMessage()); }
  }
}
