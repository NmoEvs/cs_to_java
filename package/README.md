# Package

* Packages are the dot notation representation of directories storing class files.
* A package name must be unique for the whole classpath (list of all classes known by
the application) to avoid random class loading.
* A good practice is to create package naming in this order
  * inverse company domain name
  * project family
  * project name
  * domain
  * technical
  
  example `com.evs.phoenix.ingest.recorder.persistence.jpa`
* Packages are always defined in lower case
* Use the plural for packages with homogeneous contents and the singular for 
packages with heterogeneous contents 
* The `package` statement applies to the whole file.
* The `import` statement allows to import one class of a given package or all of
  them when using the wildecard `.*`.
* The `import static` statement allows to import static class members without
  qualifications.
* The default visibility of classes, methods, and fields is `protected`, which
  means in Java accessible from every member of the package.

## References

* [Packages](http://docs.oracle.com/javase/tutorial/java/package/packages.html)
* [Managing Source and Class Files](http://docs.oracle.com/javase/tutorial/java/package/managingfiles.html)
* [Static Import](http://docs.oracle.com/javase/8/docs/technotes/guides/language/static-import.html)
* [Determining Accessibility](http://docs.oracle.com/javase/specs/jls/se8/html/jls-6.html#jls-6.6.1)
