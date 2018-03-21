# Access Modifiers

* Access modifier determines the visibility of an object, a method or a variable
* There are 3 access modifiers: `public`, `protected`, and `private`.
* Modifier is not mandatory, default modifier is `protected` also named "package".
* The modifier `protected` gives access to every other classes of the same package and
to all classes extending this class.
* A good practice is to hide variables by setting them `private` and expose them with public accessors
* A technical internal method must be declared `private` or `protected` if this method must be overridden 
by the children of the class. Only functional methods must be public to avoid to expose internal mechanisms

## References

* [Controlling Access to Members of a Class](http://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html)
* [Determining Accessibility](http://docs.oracle.com/javase/specs/jls/se8/html/jls-6.html#jls-6.6.1)
