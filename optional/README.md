# Optional

* Allow to manage nullables objects without null usage
* Use only as function return value
  * Not as bean attribute
  * Not as argument function
* Use only to return a single result (return empty List otherwise)
* Never use `get()` without testing the presence of the optional
* If you need to make an `Optional<Optional<T>>`, something’s wrong
* Prefer `ifPresent(Consumer<? super T> consumer)` then `isPresent()` if statement
* Use `orElseThrow(Supplier<? extends X> exceptionSupplier)` instead of `!isPresent()` if statement throwing exception
* `orElse(T other)`   vs   `orElseGet(Supplier<? extends T> other)`
  * When using `orElse(T other)`   T is always executed even if optional is not present
  * When using `orElseGet(Supplier other)`   the supplier is invoked only if optional is not present
* Use `flatMap()` to chain Optionals
* Use `map()` to chain non Optional and non nullable value
  


## Reference

* [Optional presentation](http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html)
* [API documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)