package com.evs.gameoflife.items;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.toIntExact;

public class World {
  private Map<Long, Cell> cells;
  private long height;
  private long width;

  public World(long width, long height) throws Exception {
    this(
        width,
        height,
        IntStream.rangeClosed(1, toIntExact(width * height))
                 .mapToObj(idx -> Health.ALIVE)
                 .collect(Collectors.toList())
    );
  }

  public World(long width, long height, List<Health> inputStates) throws Exception {
    if (toIntExact(width * height) != inputStates.size()) {
      throw new Exception("The number of initial states " + inputStates.size() + " doesn't match the dimension: (" + width + "," + height + ").");
    }
    this.width = width;
    this.height = height;
    cells = IntStream.range(0, toIntExact(width * height))
                     .parallel()
                     .mapToObj(idx -> new Cell(idx, inputStates.get(idx)))
                     .collect(Collectors.toMap(Cell::id, Function.identity()));
    cells.entrySet()
         .parallelStream()
         .forEach(elem -> elem.getValue().neighborhood(buildNeighborhood(elem.getKey())));
  }

  public String toString() {
    return cells.entrySet()
                .parallelStream()
                .map(Map.Entry::getValue)
                .map(val -> {
                  StringBuilder ss = new StringBuilder();
                  if (val.id() % width == 0 && val.id() >= width) {
                    ss.append(System.lineSeparator());
                  }
                  ss.append(val.health().toString());
                  return ss.toString();
                })
                .collect(Collectors.joining());
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
    return cells.entrySet()
                .parallelStream()
                .filter(elem -> elem.getValue().health().equals(state))
                .count();
  }

  public void evolve() {
    cells.entrySet()
         .parallelStream()
         .forEach(elem -> elem.getValue().age());
    cells.entrySet()
         .parallelStream()
         .forEach(elem -> elem.getValue().commit());
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
      long mod = coordinate % upperBound;
      return mod >= 0 ? mod : mod + upperBound;
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
