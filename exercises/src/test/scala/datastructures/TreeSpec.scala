package datastructures

import fpinscala.datastructures.{Tree, Leaf, Branch}
import org.scalatest.{Matchers, WordSpec}


class TreeSpec extends WordSpec with Matchers {

  val tree = Branch(
                    Branch(Leaf("a"), Leaf("b")),
                    Branch(Leaf("c"), Leaf("d")))

  "Tree" when {
    "size function is used" must {
      "count the number of nodes in a tree" in {
        Tree.size(tree) shouldEqual  5
      }
    }
  }

}
