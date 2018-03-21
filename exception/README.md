# Exception

* Java error management is done with exceptions
* Java has two types of exceptions, checked exceptions and unchecked exceptions.
* Checked exceptions inherits from `Exception`. 
* Unchecked exceptions inherits either from `Error` or `RuntimeException`.
* When a method ca throw a checked exception, it must be declared in the function declaration.
* When a method use a method throwing a checked exception, it must catch it or rethrow it 
(and declare it in its own declaration). 
* Checked exceptions must be used for business errors needing a treatment.
* `RuntimeException` must be use for exceptions occurring in runtime and not necessary manageable.
* Declare your own exceptions inheriting `Exception` or `RuntimeException` instead of throwing those 
base exceptions.
* You can catch multiple exceptions in a single `catch`.
* The `finally` statement is always called. 
* Any jump from the `finally` clause will discard jumps made from the `try`
  clause, either it's a `return` or a thrown exceptions.
* When working with `Closable` classes, prefer a try with resource instead of closing resources 
in hte finally clause.

## References

* [Checked Excpetions](https://en.wikipedia.org/wiki/Exception_handling#Checked_exceptions)
* [The Catch or Specify Requirement](http://docs.oracle.com/javase/tutorial/essential/exceptions/catchOrDeclare.html)
* [Exception](http://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html)
* [RuntimeException](http://docs.oracle.com/javase/8/docs/api/java/lang/RuntimeException.html)
* [Error](http://docs.oracle.com/javase/8/docs/api/java/lang/Error.html)
* [Unchecked Exceptions - The Controversy](http://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html)
* [Return, Exception, and Finally](http://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html#jls-14.17)
