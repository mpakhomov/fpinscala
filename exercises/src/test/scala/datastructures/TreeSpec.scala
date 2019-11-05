package datastructures

import fpinscala.datastructures.{Tree, Leaf, Branch}
import org.scalatest.{Matchers, WordSpec}


class TreeSpec extends WordSpec with Matchers {

  val treeChars = Branch(
                    Branch(Leaf("a"), Leaf("b")),
                    Branch(Leaf("c"), Leaf("d")))

  val treeNumbers = Branch(
    Branch(Leaf(1), Leaf(2)),
    Branch(Leaf(3), Leaf(4)))

  "Tree" when {
    "size function is used" must {
      "count the number of nodes in a tree" in {
        Tree.size(treeChars) shouldEqual  7
      }
    }

    "maximum function is used" must {
      "find the max element in a tree" in {
        Tree.maximum(treeNumbers) shouldEqual  4
      }
    }
  }

}
