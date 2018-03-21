# Internals and JVM

In the following, we give an overview of how the JVM HotSpot works, its
capabilities, its different garbage collectors, the other possible JVM
languages, and what you can do with the JVM at Runtime.

## The General Workflow

* Java Source Files
* Compilation
* Binary Class Files (bytecode)
* Class Loading and Preparation (loading, verification, linking, initializing)
* Internal Data Structure

  * bytecode
  * Metadata: methods, constants, classes, objects

* JIT compiler and bytecode interpreter

## HotSpot

HotSpot is a stack-based VM. There are two implementations of the
[HotSpot JVM](http://docs.oracle.com/javase/8/docs/technotes/guides/vm/index.html):

* The Client VM tuned for small start-up time and memory footprint.
* The Server VM tuned for maximum execution speed.

They are both composed of the 4 main components:

* The class loader
* The execution engine
  * Just-In-Time compiler
  * Bytecode interpreter
  * [4 Available Garbage Collectors](http://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/collectors.html):

    * Serial Collector
    * Parallel Collector
    * [Mostly Concurrent Collectors](http://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/concurrent.html):

      * Concurrent Mark Sweep (CMS) Collector
      * Garbage-First Garbage Collector

* The runtime data areas

  * Java and System Threads
  * Stacks, Heap, Non-Heap
  * Method, Constants, Exception, Symbol, Variables, Strings
  * etc.

* Set of supporting runtime libraries

### Example of Capabilities

* The adaptive compiler can

  * detect performance bottlenecks (hot spots) and compile them.
  * dynamically inline virtual methods when no override is detected at runtime.
  * run analysis during runtime to determine which optimizations are the best.

* Several types of garbage collectors provide different strategies to deal with
  memory garbage and addressing different use cases.
* The thread-handling capability is designed to scale readily for use in large,
  shared-memory multiprocessor servers.

### Going Further

* [JVM internals](http://blog.jamesdbloom.com/JVMInternals.html)
* [HotSpot Internals](https://wiki.openjdk.java.net/display/HotSpot/Main)
* [Introduction to HotSpot Internals](https://www.youtube.com/watch?v=XjfhsJarQy0)

## The Garbage Collectors

As stated above, there are 4 different collectors that you can select:

  * Serial Collector: Single-Threaded GC, very performant on a single processor
    machine or with a very small data sets (< 100MB).
  * Parallel Collector: minor collections in parallel, suited to multi-processor
    or multi-threaded processors with medium and large sized data sets.
  * Mostly Concurrent Collectors: major concurrent collections, suited to
    the cases of parallel collector but that needs better response time.

    * Concurrent Mark Sweep (CMS) Collector: suited to applications that prefer
      shorter garbage collection pauses and can afford to share processor
      resources with the garbage collection.
    * Garbage-First Garbage Collector: suited to multiprocessor machines with
      large memories. It meets garbage collection pause time goals with high
      probability while achieving high throughput.

The CMS is the current default one. The G1 will be the next default.

The [reference library](https://docs.oracle.com/javase/8/docs/api/java/lang/ref/package-summary.html)
provide reference-object classes that can help the garbage collector and by
doing so improving the application performance.

_**TIP**_: Do not change the default collector if you have no performance troubles.

**_TIP_**: If you have memory leaks, first have a look at events listeners, a referenced object is never collected.

### References

* [Garbage Collectors](http://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/collectors.html)
* [Mostly Concurrent Collectors](http://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/concurrent.html)
* [Java 9 The G1 GC Awakens](http://www.slideshare.net/MonicaBeckwith/java-9-the-g1-gc-awakens)

## JVM languages

The JVM HotSpot allows
[scripting languages](https://docs.oracle.com/javase/8/docs/technotes/guides/scripting/index.html).
For instance, it is shipped with the
[Nashorn Engine](https://docs.oracle.com/javase/8/docs/technotes/guides/scripting/nashorn/index.html)
wich support ECMAScript 5. But there are other options.

Indeed, some programming languages can be compiled to be run on the JVM such as:

* Groovy, a scripting and programming language (interpreted and compiled),
* Scala, an object oriented and functional programming language (compiled),
* Clojure, a functional Lisp dialect (compiled),
* JRuby, an implementation of Ruby (interpreted and compiled),
* Jython, and implementation of Python (interpreted and compiled).

Those options allow to use other programming languages, to leverage the JVM
powers, and to integrate with the whole Java ecosystem.

## Playing with the Runtime

There are several tools and libraries allowing to monitor and manage the JVM
thanks to the
[JMX technologies](https://docs.oracle.com/javase/tutorial/jmx/overview/index.html)
with but not only:

* [Jconsole](http://docs.oracle.com/javase/8/docs/technotes/guides/management/jconsole.html):
  the profiler shipped with the JDK
* [Apache JMeter](http://jmeter.apache.org/): load test and performance
  monitoring
* [JVMTop](https://github.com/patric-r/jvmtop): monitoring of running java
  processes
* [Oracle Tools](https://docs.oracle.com/javase/8/docs/technotes/tools/):
  several tools to monitor, profile, or troubleshoot the JVM
* [VisualVM](https://visualvm.java.net/): memory profiler (also shipped with
  Oracle tools)
* [StageMonitor](http://www.stagemonitor.org/): monitoring of your application
  performance
