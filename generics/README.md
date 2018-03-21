# Generics

* Java implements generics with type erasure. This allows backward
  compatibility.
* The prototype of a method can be declared with a wildcard type, i.e. an
  unknown parameter type.
* In Java, the derivation constraint is the only constraint that could be
  applied on a generic type.
* Generics don't support primitive as parameter type in Java.
* Generics are not transitive for inheritance between parameter types.
* Static fields cannot be of any generic type as they are shared with all
  instances.
* All the instances of a generic type have the same run-time class whatever
  their respective parameter types are.
* Java supports generic method.
* Array of a generic type can't be created.

## References

* [Generics](http://docs.oracle.com/javase/tutorial/extra/generics/intro.html)
* [Generics (updated)](https://docs.oracle.com/javase/tutorial/java/generics/index.html)
* [Type Erasure](http://docs.oracle.com/javase/tutorial/java/generics/erasure.html)
* [Specification of Type Erasure](http://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.6)
* [Generic Method](http://docs.oracle.com/javase/tutorial/extra/generics/methods.html)
