public class Dog extends Animal {
  public String sound() {
    return thisAnimal() + " barks.";
  }

  public boolean like(Cat c) {
    return false;
  }

  public Dog breed() {
    return new Dog();
  }
}
