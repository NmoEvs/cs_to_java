public abstract class Animal {
  public final String thisAnimal() {
    return "The " + this.getClass().getName().toLowerCase();
  }
  public abstract String sound();

  public boolean like(Animal a) {
    return true;
  }

  public abstract Animal breed();
}
