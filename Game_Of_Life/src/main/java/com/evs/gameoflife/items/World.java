package com.evs.gameoflife.items;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;

public class World {
  private Map<Long, Cell> cells;
  private long height;
  private long width;

  public World(long width, long height) throws Exception {
    this(
        width,
        height,
        getInputStates(width, height)
    );
  }

  private static List<Health> getInputStates(long width, long height) {
    //TO_OPTIMIZE
    // Use streams
    List<Health> list = new ArrayList<>();
    for (int i = 1; i <= toIntExact(width * height) ; i++) {
      list.add(Health.ALIVE);
    }
    return list;
  }

  public World(long width, long height, List<Health> inputStates) throws Exception {
    if (toIntExact(width * height) != inputStates.size()) {
      throw new Exception("The number of initial states " + inputStates.size() + " doesn't match the dimension: (" + width + "," + height + ").");
    }
    this.width = width;
    this.height = height;

    //TO_OPTIMIZE
    //Use streams to paralleling job
    cells = new HashMap<>();
    for (int i = 0; i < toIntExact(width * height); i++) {
      Cell cell = new Cell(i, inputStates.get(i));
      cells.put(cell.id(),cell);
    }

    //TO_OPTIMIZE
    //Use streams to paralleling job
    for (Iterator<Long> iterator = cells.keySet().iterator(); iterator.hasNext(); ) {
      Long next = iterator.next();
      Cell cell = cells.get(next);
      cell.neighborhood(buildNeighborhood(next));
    }

  }

  public String toString() {
    //TO_OPTIMIZE
    //Use streams to paralleling job
    String out = "";
    for (Iterator<Cell> iterator = cells.values().iterator(); iterator.hasNext(); ) {
      Cell next = iterator.next();
      if (next.id() % width == 0 && next.id() >= width) {
        out += System.lineSeparator();
      }
      out += next.health();
    }
    return out;
  }

  private Neighborhood buildNeighborhood(long id) {
    return new Neighborhood(
        buildNeighborsIds(id).stream()
                             .map(elem -> cells.get(elem))
                             .collect(Collectors.toList())
    );
  }

  private Set<Long> buildNeighborsIds(long id) {
    Set<Long> ids = new HashSet<Long>();
    ids.add(leftId(id));
    ids.add(leftTopId(id));
    ids.add(topId(id));
    ids.add(rightTopId(id));
    ids.add(rightId(id));
    ids.add(rightBottomId(id));
    ids.add(bottomId(id));
    ids.add(leftBottomId(id));
    return ids;
  }

  public long populationSize() {
    return cells.size();
  }

  public long populationSizeOf(Health state) {
    //TO_OPTIMIZE
    //Use streams to paralleling job
    long count = 0;
    for (Iterator iterator = cells.values().iterator(); iterator.hasNext(); ) {
      Cell next = (Cell) iterator.next();
      if (next.health().equals(state)){
        count = count + 1;
      }
    }
    return count;
  }

  public void evolve() {
    //TO_OPTIMIZE
    //Use streams to paralleling job
    for (Iterator iterator = cells.values().iterator(); iterator.hasNext(); ) {
      Cell next = (Cell) iterator.next();
      next.age();
    }
    for (Iterator iterator = cells.values().iterator(); iterator.hasNext(); ) {
      Cell next = (Cell) iterator.next();
      next.commit();
    }
  }

  private class Coordinates {
    private long x;
    private long y;

    public Coordinates(long id) {
      x = modulo(id, width);
      y = id / width;

    }

    public Coordinates(long x, long y) {
      this.x = x;
      this.y = y;
    }

    public long id() {
      return x + width * y;
    }

    public long x() {
      return x;
    }

    public long y() {
      return y;
    }

    public Coordinates left() {
      return new Coordinates(
          before(x, width),
          y
      );
    }

    public Coordinates right() {
      return new Coordinates(
          after(x, width),
          y
      );
    }

    public Coordinates top() {
      return new Coordinates(
          x,
          before(y, height)
      );
    }

    public Coordinates bottom() {
      return new Coordinates(
          x,
          after(y, height)
      );
    }

    public Coordinates leftTop() {
      return new Coordinates(
          before(x, width),
          before(y, height)
      );
    }

    public Coordinates rightTop() {
      return new Coordinates(
          after(x, width),
          before(y, height)
      );
    }

    public Coordinates leftBottom() {
      return new Coordinates(
          before(x, width),
          after(y, height)
      );
    }

    public Coordinates rightBottom() {
      return new Coordinates(
          after(x, width),
          after(y, height)
      );
    }

    private long before(long coordinate, long upperBound) {
      return modulo(coordinate - 1, upperBound);
    }

    private long after(long coordinate, long upperBound) {
      return modulo(coordinate + 1, upperBound);
    }

    private long modulo(long coordinate, long upperBound) {
      //TO_OPTIMIZE
      //Use ternary operator instead of classic if else statement
      if (coordinate % upperBound >= 0){
        return coordinate % upperBound;
      } else {
        return (coordinate % upperBound) + upperBound;
      }
    }
  }

  private long leftId(long id) {
    return new Coordinates(id).left().id();
  }

  private long rightId(long id) {
    return new Coordinates(id).right().id();
  }

  private long topId(long id) {
    return new Coordinates(id).top().id();
  }

  private long bottomId(long id) {
    return new Coordinates(id).bottom().id();
  }

  private long leftTopId(long id) {
    return new Coordinates(id).leftTop().id();
  }

  private long rightTopId(long id) {
    return new Coordinates(id).rightTop().id();
  }

  private long leftBottomId(long id) {
    return new Coordinates(id).leftBottom().id();
  }

  private long rightBottomId(long id) {
    return new Coordinates(id).rightBottom().id();
  }
}
