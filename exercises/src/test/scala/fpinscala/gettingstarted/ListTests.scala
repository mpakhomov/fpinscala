package fpinscala.gettingstarted

import fpinscala.datastructures.{Cons, List, Nil}
import org.scalatest.{Matchers, WordSpec}


class ListTests extends WordSpec with Matchers {

  "List" should {

    "return a tail of non-empty list" in {
      List.tail(List(1, 2)) shouldBe List(2)
      List.tail(Cons(1, Cons(2, Nil))) shouldBe Cons(2, Nil)
      List.tail(List(1)) shouldBe Nil
    }

    "throw an exception when calling tail on empty list" in {
      assertThrows[Throwable] {
        List.tail(Nil)
      }
    }

  }


}
