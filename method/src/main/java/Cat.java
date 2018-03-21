public class Cat extends Animal {
  public String sound() {
    return thisAnimal() + " mews.";
  }

  public boolean like(Dog d) {
    return false;
  }

  public Cat breed() {
    return new Cat();
  }
}
