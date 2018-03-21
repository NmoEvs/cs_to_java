# From C++

## Here is a non exhaustive list of differences or similarities between Java and C++

* Java is a compiled AND interpreted language. Java files (.java) are compiled into bytecode (.class) with a development
 kit (JDK) which is interpreted into a virtual machine (JVM) provided by a runtime environment (JRE).
* Java is written once and run everywhere.
* As C++, Java allows procedural, functional and generic programming. Object oriented programming is strongly encouraged
* Java allows reflexion allowing metaprogramming and dynamic code generation at runtime
* Bound check is provided by Java
* Primitive types limits are cross platform
* All types are passed by value. It does not mean that all objects are immutable, it means that primitive types are 
copied and variables referencing objects are copied, not the object itself ! 
See [argument java documentation](http://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html).
* Java does not provide destroy methods but implements an automatic garbage collector. Every non referenced object
is marked as eligible for garbage collection. This collection occurs when system is in a low charge of when needed.
* Resources are not automatically closed if you don't use a "try with resource"
* Java operators are not overridable
* Java does not provide explicit multiple inheritance but Java 8 provides default methods in interfaces which allows
 a kind of multiple inheritance. Nevertheless, multiple inheritance by this way is not a good practice in Java.
* Javadoc provides a native online html documentation
* No goto in Java
* Java does not provide method default attribute values, prefer using method overloading
* Autoboxing converts automatically primitives to linked objects types.
* As in C++ the first element of a list/table is at index 0
    

## References

 [Wikipedia comparison of Java and C++](https://en.wikipedia.org/wiki/Comparison_of_Java_and_C%2B%2B)
