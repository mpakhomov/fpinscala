package fpinscala.datastructures

import scala.annotation.tailrec

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar

  def tail[A](l: List[A]): List[A] = l match {
    case Cons(_, tail) => tail
    case Nil => throw new Error("tail on empty list")
  }

  def setHead[A](l: List[A], h: A): List[A] = l match {
    case Cons(_, tail) => Cons(h, tail)
    case Nil => throw new Error("setHead on empty list")
  }

  def drop[A](l: List[A], n: Int): List[A] = l match {
    case Cons(_, t) =>
      if (n > 1) drop(t, n - 1)
      else if (n == 1) t
      else l
    case Nil => Nil
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h, t) => if (f(h)) dropWhile(t, f) else l
    case Nil => Nil
  }

  def init[A](l: List[A]): List[A] = l match {
    case Cons(_, Nil) => Nil
    case Cons(h, t) => Cons(h, init(t))
    case Nil => sys.error("init on empty list")
  }

  def length[A](l: List[A]): Int =
    List.foldLeft(l, 0)((acc, _) => 1 + acc)

  @tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B =  l match {
    case Nil => z
    case Cons(h, t) => foldLeft(t, f(z, h))(f)
  }
  def reverse[A](l: List[A]): List[A] =
    List.foldLeft(l, List[A]())((acc, a) => Cons(a, acc))

  def reverse1[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(h, t) => append(reverse1(t), List(h))
  }

  def foldRightViaFoldLeft[A, B](as: List[A], z: B)(f: (A, B) => B): B =
    foldLeft(reverse(as),z)((acc, a) => f(a, acc))

  def appendViaFold[A](a1: List[A], a2: List[A]): List[A] =
    foldRight(a1, a2)(Cons(_, _))

  def flatten[A](l: List[List[A]]): List[A] =
    foldRight(l, Nil: List[A])(append)

  // alias for flatten, because in the book the function is called concat, not flatten
  def concat[A](l: List[List[A]]): List[A] = flatten(l)

  def add1(l: List[Int]): List[Int] =
    foldRight(l, Nil: List[Int])((x: Int, acc: List[Int]) => Cons(x + 1, acc))
//    foldRight(l, Nil: List[Int])((h, t) => Cons(h + 1 , t))

  def doubleToString(l: List[Double]): List[String] =
    foldRight(l, Nil: List[String])((x: Double, acc: List[String]) => Cons(x.toString, acc))

  def map[A,B](l: List[A])(f: A => B): List[B] =
    foldRight(l, Nil: List[B])((x: A, acc: List[B]) => Cons(f(x), acc))

  def map_2[A,B](l: List[A])(f: A => B): List[B] = {
    val buf = new collection.mutable.ListBuffer[B]
    @tailrec
    def go(l: List[A]): Unit = l match {
      case Nil => ()
      case Cons(h,t) => buf += f(h); go(t)
    }
    go(l)
    List(buf.toList: _*) // converting from the standard Scala list to the list we've defined here
  }

  /*
   * Write a function filter that removes elements from a list unless they satisfy a given
   * predicate. Use it to remove all odd numbers from a List[Int].
   */
  def filter[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case Nil => as
    case Cons(h, t) => if (f(h)) Cons(h, filter(t)(f)) else filter(t)(f)
  }

  def filter_1[A](as: List[A])(f: A => Boolean): List[A] =
    foldRight(as, Nil: List[A])((h, t) => if (f(h)) Cons(h, t) else t)

  def filter_2[A](as: List[A])(f: A => Boolean): List[A] =
    foldRightViaFoldLeft(as, Nil: List[A])((h, t) => if (f(h)) Cons(h, t) else t)

  def filter_3[A](as: List[A])(f: A => Boolean): List[A] = {
    val buf = new collection.mutable.ListBuffer[A]
    @tailrec
    def loop(as: List[A]): List[A] = as match {
      case Nil => as
      case Cons(h, t) => if (f(h)) buf += h; loop(t)
    }
    loop(as)
    List(buf.toList : _*)
  }

  def filter_4[A](as: List[A])(f: A => Boolean): List[A] =
    flatMap(as)(x => if (f(x)) List(x) else Nil)

  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] =
    flatten(map(as)(f))

  def flatMap_1[A,B](as: List[A])(f: A => List[B]): List[B] =
    foldRight(as, Nil: List[B])((h, t) => append(f(h), t))

  def sumLists(as: List[Int], bs: List[Int]): List[Int] = (as, bs) match {
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, sumLists(t1, t2))
    case _ => Nil
  }

  def zipWith[A, B, C](as: List[A], bs: List[B])(f: (A, B) => C): List[C] = (as, bs) match {
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
    case _ => Nil
  }

  @tailrec
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = sup match {
    case Cons(_, t) => if (startsWith (sup, sub)) true else hasSubsequence(t, sub)
    case Nil => sub == Nil
  }

  @tailrec
  def startsWith[A](sup: List[A], sub: List[A]): Boolean = (sup, sub) match {
    case (Cons(h1, t1), Cons(h2, t2)) => if (h1 != h2) false else startsWith(t1, t2)
    case (Nil, Cons(_, _)) => false
    case _ => true
  }
}