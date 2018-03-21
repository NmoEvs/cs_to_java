# Static Field

* A `static` field is shared with all instances of all threads.
* A class constant is a `static` field declared as `final`.
* Static field can be assigned in a static initializer.
* Static fields typically lead to non thread safe classes.
* Static methods can only access static fields.
* Make field static only and only when needed to avoid side effects because of 
 instance sharing

## References

* [Class members](http://docs.oracle.com/javase/tutorial/java/javaOO/classvars.html)
* [`static` Fields](http://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.3.1.1)
* [`final` Fields](http://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.3.1.2)
