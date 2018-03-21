# Method

* Java supports covariant return types which means that you can return a Dog in a method returning an Animal.
* All methods are virtual except ones explicitly declared `final`.
* When `final`, the method cannot be overridden in children classes.
* Operators cannot be overloaded.
* Methods can be overloaded.
* The behavior of a method can be undefined and declared `abstract`.
* A class including `abstract` methods has to be declared `abstract`.
* A method can be a class method when declared `static`.
* Class methods can be hidden by eponym subclass methods.
* Methods can be overridden by eponym subclass methods.

## References

* [Returning a Value from a Method](http://docs.oracle.com/javase/tutorial/java/javaOO/returnvalue.html)
* [Overriding and Hiding Methods](http://docs.oracle.com/javase/tutorial/java/IandI/override.html)
* [Abstract Methods and Classes](http://docs.oracle.com/javase/tutorial/java/IandI/abstract.html)
* [`final` Methods](http://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.4.3.3)
* [`abstract` Methods](http://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.4.3.1)
* [`static` Methods](http://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.4.3.2)
