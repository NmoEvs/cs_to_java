# Math

* Overflows aren't not checked and don't throw exception.
* Division by zero produce NaN for floating point number and throw
  `ArithmeticException` for integer.
* The `Math` class provides several `...Exact()` methods checking overflow
  and throwing exceptions when occurring.
* The modifier `strictfp` can be applied to class, interface, and methods and
  guarantees that all floating point expressions must be those predicted by
  IEEE 754 arithmetic.
* The `StrictMath` class provides a portable, standard, and more strict
  arithmetic.

## References

* [`Math` class](http://docs.oracle.com/javase/8/docs/api/java/lang/Math.html)
* [FP-strict expressions](http://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.4)
* [`StrictMath` class](http://docs.oracle.com/javase/8/docs/api/java/lang/StrictMath.html)
