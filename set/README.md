# Set

* You get elements with an iterator. There is no `get` method.
* You can't create a set of primitive.
* `Set` is an interface, there is multiple implementations providing thread-safe, thread-non safe,... sets.
 The most used one is `HashSet<T>`, check the
 [API documentation](http://docs.oracle.com/javase/8/docs/api/java/util/Set.html) to choose your implementation
 when you have a specific usage.
* A `Set` can't contains null objects or twice the same instance.
* If you add an instance to a Set that yet contains this instance, nothing happens 

## Reference

[API documentation](http://docs.oracle.com/javase/8/docs/api/java/util/Set.html)