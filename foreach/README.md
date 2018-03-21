# For-each Loop

* As C# and C++, Java has an iteration construct.
* It allows to iterate through collection implementing the interface
  `java.lang.Iterable`.
* Instead of iterating in a loop or in a `for` statement, you can use the `foreach()` function 
  with a lambda expression
  
  
```java
List<String> myList = new ArrayList<>();
myList.add("One");
myList.add("Two");
myList.add("Three");

// Simple for loop
for (int i = 0; i < myList.size(); i++) {
    System.out.println(myList.get(i));
}

// For each loop
for (String s : myList) {
    System.out.println(s);
}

// Iteration
Iterator<String> e = myList.iterator();
while (e.hasNext()) {
    System.out.println(e.next());
}

// foreach() function
myList.forEach(s -> System.out.println(s));
myList.forEach(System.out::println);

// foreach() on a Stream
myList.stream().forEach(s -> System.out.println(s));
myList.stream().forEach(System.out::println);

// foreach() using parallel threads on Streams
myList.parallelStream().forEach(s -> System.out.println(s));
myList.parallelStream().forEach(System.out::println);

```

> **Warning** Never add or remove an element of the collection while looping on it! 

Never this:
```java
for (car : cars) {
 	if (car.something()) {
 		cars.remove(car)
 	}
 }
```

Prefer use iterator:
```java
Iterator<Car> iterator = cars.iterator();
while (iterator.hasNext()) {
    Car car = iterator.next();
    if (car.something()) {
        iterator.remove();
    }
}
```

Or use streams:
```java
List<Car> toRemove = cars.stream.filter(Car::something()).collect(Collectors.toList());
cars.removeAll(toRemove);
```

Or better since Java 8:
```java
cars.removeIf(Car::something());
```

## References

* [For statement](http://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html)
* [Interface `Iterable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html)
