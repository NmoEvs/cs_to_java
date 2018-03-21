# Interface

* An interface is a contract that must be followed by classes implementing it.
* An interface can't be instantiated.
* Java supports multiple interface implementations.
* An interface is declared with the keyword `interface`.
* A class implementing an interface with the keyword `implements`.
* An interface can extends another interface.
* There is no convention on the naming of an interface except it must be in camel case.
* It can contain abstract, default, and static methods.
* It can contain contants.
* Since Java8, an interface can contain default method implementation to propose new features
without breaking retro compatibility. Those methods must be leaded with the `default` keyword 
* Every member is public by default. The `public` modifier can be omitted.
* A class must override all methods defines in all interfaces defined in the `implements` declaration
  except default method (allowed but not mandatory for default methods).
* When creating an object implementing an interface, a good practice is to declare the variable
as the interface type and creating the object of the implementing class as `List myList = new ArrayList()`
where `List` is an interface and `ArrayList` a class implementing `List` interface

## References

* [Interface](http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)
* [Default and static methods](http://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)
