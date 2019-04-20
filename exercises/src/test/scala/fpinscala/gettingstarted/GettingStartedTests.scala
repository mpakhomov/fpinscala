package fpinscala.gettingstarted

import org.scalatest.{Matchers, WordSpec}

class GettingStartedTests extends WordSpec with Matchers {

  "PolymorphicFunctions" must {
    "isSorted returns true for sorted array" in {
      import scala.math.Ordering
      val as = Array(0, 1, 2, 3, 4)
      PolymorphicFunctions.isSorted(as, Ordering.Int.gt) shouldBe true
    }
    "isSorted returns false for unsorted array" in {
      val as = Array(0, 1, 2, 3, 4, 1)
      PolymorphicFunctions.isSorted(as, (a: Int, b: Int) => a > b) shouldBe false
    }
    "isSorted returns true for one element array" in {
      val as = Array(0)
      PolymorphicFunctions.isSorted(as, (a: Int, b: Int) => a > b) shouldBe true
    }
    "isSorted returns true for empty array" in {
      val as = Array[Int]()
      PolymorphicFunctions.isSorted(as, (a: Int, b: Int) => a > b) shouldBe true
    }
    "curry works properly" in {
      val plus: (Int, Int) => Int = (a, b) => a + b
      val plusCurried: Int => Int => Int = PolymorphicFunctions.curry(plus)
      val plus2: Int => Int = (a: Int) => plusCurried(2)(a)
      plus2(3) shouldBe 5
    }
  }



}
