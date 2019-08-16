package fpinscala.gettingstarted

import fpinscala.datastructures.{Cons, List, Nil}
import org.scalatest.{Matchers, WordSpec}


class ListTests extends WordSpec with Matchers {

  "List" when {

    "tail function is used" must {
      "return a tail of non-empty list" in {
        List.tail(List(1, 2)) shouldBe List(2)
        List.tail(Cons(1, Cons(2, Nil))) shouldBe Cons(2, Nil)
        List.tail(List(1)) shouldBe Nil
      }

      "throw an exception on empty list" in {
        assertThrows[Throwable] {
          List.tail(Nil)
        }
      }
    }

    "setHead function is used" must {
      "change the head of non-empty list" in {
        List.setHead(List(1, 2), 42) shouldBe List(42, 2)
        List.setHead(Cons(1, Cons(2, Nil)), 42) shouldBe Cons(42, Cons(2, Nil))
        List.setHead(List(1), 42) shouldBe List(42)
      }

      "throw an exception on empty list" in {
        assertThrows[Throwable] {
          List.setHead(Nil, 42)
        }
      }
    }

    "drop function is used" must {
      "return a sub-list if 0 <= n <= list.length" in {
        List.drop(List(1, 2, 3, 4), 2) shouldBe List(3, 4)
      }

      "return the same list when n == 0" in {
        List.drop(List(1, 2, 3, 4), 0) shouldBe List(1, 2, 3, 4)
      }

      "return the same list when n <= 0" in {
        List.drop(List(1, 2, 3, 4), -1) shouldBe List(1, 2, 3, 4)
      }

      "return Nil when calling on empty list and n == 0" in {
        List.drop(Nil, 0) shouldBe Nil
      }

      "return Nil when n > list.length" in {
        List.drop(List(1, 2), 3) shouldBe Nil
      }
    }

    "dropWhile function is used" must {
      "drop element while predicate holds" in {
        List.dropWhile(List(1, 2, 3, 4), (x: Int) => x < 3) shouldBe List(3, 4)
      }
    }

    "init function is used" must {
      "return all elements except the last one" in {
        List.init(List(1, 2, 3)) shouldBe List(1, 2)
      }
      "throw an error on empty list" in {
        assertThrows[Throwable](List.init(Nil))
      }
    }

    "foldRight function is used" must {
      "build a list" in {
        List.foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _)) shouldBe List(1, 2, 3)
      }
    }

    "foldLeft function is used" must {
      "calculate the sum of the list" in {
        List.foldLeft(List(1, 2, 3), 0)(_ + _) shouldBe 6
      }
      "calculate the product of the list" in {
        List.foldLeft(List(2, 3, 4), 1)(_ * _) shouldBe 24
      }
    }

    "reverse function is used" must {
      "reverse the list" in {
        List.reverse(List(1, 2, 3)) shouldBe List(3, 2, 1)
      }
    }

    "reverse1 function is used" must {
      "reverse the list" in {
        List.reverse1(List(1, 2, 3)) shouldBe List(3, 2, 1)
      }
    }

    "length function is used" must {
      "compute the length" in {
        List.length(List(1, 2, 3)) shouldBe 3
      }
    }

    "foldRightViaFoldLeft function is used " must {
      "build a list" in {
        List.foldRightViaFoldLeft(List(1, 2, 3), List[Int]())(Cons(_, _))
      }
    }

  }
}
