# Primitive

* Primitives are not object and so 
  * cannot be instantiated
  * cannot be null
  * begin with a lower case
* You can use `==` to compare two primitives values
* You can't put them in a collection.
* New primitives cannot be defined (value type does not exist in Java).
* For each primitive, there is a boxed version, i.e. a corresponding class.
* Autoboxing and unboxing allows implicit conversion.
* There is not unsigned numerical primitives.
  * Note that non primitive corresponding classes offer method to deal with unsigned
    * https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html      
    *  https://stackoverflow.com/questions/9854166/declaring-an-unsigned-int-in-java


## References

* [Primitive Types and Values](http://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.2)
* [Autoboxing](http://docs.oracle.com/javase/8/docs/technotes/guides/language/autoboxing.html)
