# Nested Classes

* A static nested class is declared inside another class. It has access only to
  the static members of the outer class.
* An inner class is non-static and its instance lives in the instance of an
  outer class. It has an implicit reference to the outer class `OuterClass.this`.
  It can't have any static members.
* A local class is an inner class declared in a block. It can capture local
  variables that are effectively final.
* An anonymous class is an expression creating an instance of anonymous subclass
  of a given class or interface and defining at the same time the interface
  of that anonymous subclass. It can capture any local variable
* Nested classes could lead to performance issues, do not abuse of them.
* Lambdas can replace nested class in some cases (handlers, threads,...)  

## References

* [Nested Class](http://docs.oracle.com/javase/tutorial/java/javaOO/nested.html)
