package fpinscala.gettingstarted

import org.scalatest.{FunSuite, Matchers}

class GettingStartedTests extends FunSuite with Matchers {

    test("PolymorphicFunctions.isSorted returns true for sorted array"){
      import scala.math.Ordering
      val as = Array(0, 1, 2, 3, 4)
      PolymorphicFunctions.isSorted(as, Ordering.Int.gt) shouldBe true
    }
    test("PolymorphicFunctions.isSorted returns false for unsorted array") {
      val as = Array(0, 1, 2, 3, 4, 1)
      PolymorphicFunctions.isSorted(as, (a: Int, b: Int) => a > b) shouldBe false
    }
    test("PolymorphicFunctions.isSorted returns true for one element array") {
      val as = Array(0)
      PolymorphicFunctions.isSorted(as, (a: Int, b: Int) => a > b) shouldBe true
    }
    test("PolymorphicFunctions.isSorted returns true for empty array") {
      val as = Array[Int]()
      PolymorphicFunctions.isSorted(as, (a: Int, b: Int) => a > b) shouldBe true
    }
    test("PolymorphicFunctions.curry") {
      val plus: (Int, Int) => Int = (a, b) => a + b
      val plusCurried: Int => Int => Int = PolymorphicFunctions.curry(plus)
      val plus2: Int => Int = (a: Int) => plusCurried(2)(a)
      plus2(3) shouldBe 5
    }
    test("PolymorphicFunctions.uncurry") {
      val plus: (Int, Int) => Int = (a, b) => a + b
      val plusCurried: Int => Int => Int = plus.curried
      val plusUncurried: (Int, Int) => Int = PolymorphicFunctions.uncurry(plusCurried)
      plusUncurried(1, 2) shouldBe 3
    }

}
