# String

* The operator `==` tests object references and not string values.
* The `str1.equals(str2)` compares the value of the 2 strings.
* Strings are immutable.
* The operator `+` concatenate two strings.
* The method `String.substring()` uses inclusive lower bound and exclusive upper
  bound.
* The `toString()` method on an object returns the object instance address as a String. It must
  be overridden to provide a readable representation of an object.
* Java is case sensitive. A `equalsIgnoreCase()` function allows to compare two strings without taking
care of the case.
* When comparing a string to a constant, always use equals function on constant instead of String to avoid
 null pointer exception if the String is null : `"HELLO".equals(myString)` instead of `myString.equals("HELLO")`

## References

* [method `String.equals`](http://docs.oracle.com/javase/8/docs/api/java/lang/String.html#equals-java.lang.Object-)
* [method `String.equalsIgnoreCase`](http://docs.oracle.com/javase/8/docs/api/java/lang/String.html#equalsIgnoreCase-java.lang.String-)
* [method `String.substring`](http://docs.oracle.com/javase/8/docs/api/java/lang/String.html#substring-int-int-)
* [Reference equality operator](http://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.21.3)
* [String Concatenation Operator +](http://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.18.1)
* [Strings in Java](https://docs.oracle.com/javase/tutorial/java/data/strings.html)
