# Construction and Destruction

* A constructor is a method which is called to create an instance of a class with e the `new` keyword
* A constructor must be named as the class beginning with an upper letter and without a return type
* A constructor without argument is called a default constructor
* If no constructor is defined, the JDK will add a default one at compile time
* Use constructors with arguments to allow developers to create objects with instance variables initialization
* A class without any public constructor can't be instantiated (to create a static class for example) 
* Constructor chaining can use `this` or `super` for base classes. They have to
  be called on the first line.
* If there is no explicit call to parent constructor, the default one is called
  implicitly.
* There is no static constructor but you can use initializer bloc.
* There isn't any destructor, but `Object` provides the `finalize()` method that
  may be called before the instance being garbage-collected. Be careful, there is no guarantee that this
  method will be called before the application shut down. Try to never us it...
* In a bean container environment (as Spring or EJB) the container provides annotations to call methods
during the object lifecycle like @PostConstruct and @PreDestroy to achieve this goal

## References

* [Providing Constructors for Your Classes](http://docs.oracle.com/javase/tutorial/java/javaOO/constructors.html)
* [The `finalize()` method](https://docs.oracle.com/javase/tutorial/java/IandI/objectclass.html)
* [Using the keyword `super`](https://docs.oracle.com/javase/tutorial/java/IandI/super.html)
