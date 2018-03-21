# Class loader

* There is one class loader per `main()` method
* Class loader load classes first from current application class and then from classpath
  without order guarantee if two classes have same name in same package
* `ClassLoader` class is used to get resources from classpath  

## Reference

[API documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassLoader.html)