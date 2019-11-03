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
        List.foldRightViaFoldLeft(List(1, 2, 3), List[Int]())(Cons(_, _)) shouldBe List(1, 2, 3)
      }
    }

    "appendViaFold function is used " must {
      "append a list" in {
        List.appendViaFold(List(1, 2, 3), List[Int](4, 5, 6)) shouldBe List[Int](1, 2, 3, 4, 5, 6)
      }
    }

    "flatten function is used " must {
      "flatten a list" in {
        List.flatten(List(List(1), List(2), List(3))) shouldBe List[Int](1, 2, 3)
        List.concat(List(List(1), List(2), List(3))) shouldBe List[Int](1, 2, 3)
      }
    }

    "add1 function is used " must {
      "transform a list of integers by adding 1 to each element" in {
        List.add1(List(1, 2, 3)) shouldBe List[Int](2, 3, 4)
      }
    }

    "doubleToString function is used " must {
      "turn each value in a List[Double] into a String" in {
        List.doubleToString(List(1.0, 2.0, 3.0)) shouldBe List[String]("1.0", "2.0", "3.0")
      }
    }

    "map function is used " must {
      "transform a list" in {
        List.map(List(1, 2, 3))(x => (x * 2).toString()) shouldBe List[String]("2", "4", "6")
        List.map_2(List(1, 2, 3))(x => (x * 2).toString()) shouldBe List[String]("2", "4", "6")
      }
    }

    "filter function is used " must {
      "remove all odd numbers from list of ints" in {
        List.filter(List(1, 2, 3, 4))(_ % 2 == 0) shouldBe List[Int](2, 4)
        List.filter_1(List(1, 2, 3, 4))(_ % 2 == 0) shouldBe List[Int](2, 4)
        List.filter_2(List(1, 2, 3, 4))(_ % 2 == 0) shouldBe List[Int](2, 4)
        List.filter_3(List(1, 2, 3, 4))(_ % 2 == 0) shouldBe List[Int](2, 4)
      }
    }

    "flatMap function is used " must {
      "transform a list" in {
        List.flatMap(List(1, 2, 3))(i => List(i, i)) shouldBe List[Int](1, 1, 2, 2, 3, 3)
        List.flatMap_1(List(1, 2, 3))(i => List(i, i)) shouldBe List[Int](1, 1, 2, 2, 3, 3)
      }
    }

  }
}
