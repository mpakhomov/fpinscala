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

    "setHead on a non-empty list" in {
      List.setHead(List(1, 2), 42) shouldBe List(42, 2)
      List.setHead(Cons(1, Cons(2, Nil)), 42) shouldBe Cons(42, Cons(2, Nil))
      List.setHead(List(1), 42) shouldBe List(42)
    }

    "throw an exception when calling setHead on empty list" in {
      assertThrows[Throwable] {
        List.setHead(Nil, 42)
      }
    }

  }


}
