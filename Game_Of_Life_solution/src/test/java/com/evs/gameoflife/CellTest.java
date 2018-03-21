package com.evs.gameoflife;

import com.evs.gameoflife.items.Cell;
import com.evs.gameoflife.items.Health;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CellTest {
  @Test
  public void itIsAliveOrDead() {
    Cell subject = new Cell(0);
    assertThat(
      subject.health(),
      anyOf(
        equalTo(Health.ALIVE),
        equalTo(Health.DEAD)
      ));
  }

  @Test
  public void itsCurrentStateDoesntChangeWhenAging() {
    Cell subject = new Cell(0, Health.ALIVE);

    subject.neighborhood(new NeighborhoodFake(false, false));
    subject.age();
    assertEquals( Health.ALIVE, subject.health());
  }

  @Test
  public void itsCurrentStateChangesWhenCommitting() {
    Cell subject = new Cell(0, Health.ALIVE);

    subject.neighborhood(new NeighborhoodFake(false, false));
    subject.age();
    subject.commit();
    assertEquals(Health.DEAD, subject.health());
  }

  @Test
  public void itDiesByOverPopulation() {
    Cell subject = new Cell(0, Health.ALIVE);

    subject.neighborhood(new NeighborhoodFake(false, false));
    subject.age();
    assertEquals(Health.DEAD, subject.nextHealth());
  }

  @Test
  public void itDiesByUnderPopulation() {
    Cell subject = new Cell(0, Health.ALIVE);

    subject.neighborhood(new NeighborhoodFake(false, false));
    subject.age();
    assertEquals(Health.DEAD, subject.nextHealth());
  }

  @Test
  public void itLivesWhenMatableAndDead() {
    Cell subject = new Cell(0, Health.DEAD);

    subject.neighborhood(new NeighborhoodFake(true, true));
    subject.age();
    assertEquals(Health.ALIVE, subject.nextHealth());
  }

  @Test
  public void itLivesWhenWellPopulatedAndAlive() {
    Cell subject = new Cell(0, Health.ALIVE);

    subject.neighborhood(new NeighborhoodFake(false, true));
    subject.age();
    assertEquals(Health.ALIVE, subject.nextHealth());
  }
}
