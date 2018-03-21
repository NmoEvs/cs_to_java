# List

* You get an element thanks to the method `List.get`. There is no `[]` operator.
* You can't create a list of primitive.
* `List` is an interface, there is multiple implementations providing thread-safe, thread-non safe, sorted, 
linked,... lists. The most used one is `ArrayList<T>`, check the
 [API documentation](http://docs.oracle.com/javase/8/docs/api/java/util/List.html) to choose your implementation
 when you have a specific usage.
* A `List` can contains null objects.
* A `List` can contains the same object multiple times
* When you need a distinct operation on a `List`, move it to a `Set` which can't contains null objects
and only one instance of the same object

## Reference

* [The method `List.get`](http://docs.oracle.com/javase/8/docs/api/java/util/List.html#get-int-)
* [The `Collection` interface](http://docs.oracle.com/javase/tutorial/collections/interfaces/collection.html)
* [The method `Arrays.asList`](http://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#asList-T...-)
