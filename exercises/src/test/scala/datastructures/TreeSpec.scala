package datastructures

import fpinscala.datastructures.{Tree, Leaf, Branch}
import org.scalatest.{Matchers, WordSpec}


class TreeSpec extends WordSpec with Matchers {

  val treeChars = Branch(
                    Branch(Leaf("a"), Leaf("b")),
                    Branch(Leaf("c"), Leaf("d")))

  val treeCharsDepth3 = Branch(
                          Branch(Leaf("a"), Leaf("b")),
                          Branch(Leaf("c"), Branch(
                                              Leaf("d"), Leaf("e"))))

  val treeNumbers = Branch(
                      Branch(Leaf(1), Leaf(2)),
                      Branch(Leaf(3), Leaf(4)))

  "Tree" when {
    "size function is used" must {
      "count the number of nodes in a tree" in {
        Tree.size(treeChars) shouldEqual  7
        Tree.sizeViaFold(treeChars) shouldEqual  7
      }
    }

    "maximum function is used" must {
      "find the max element in a tree" in {
        Tree.maximum(treeNumbers) shouldEqual  4
        Tree.maximumViaFold(treeNumbers) shouldEqual  4
      }
    }

    "depth function is used" must {
      "find the maximum path length from the root of a tree to any leaf" in {
        Tree.depth(treeChars) shouldBe 2
        Tree.depthViaFold(treeChars) shouldBe 2
        Tree.depth(treeNumbers) shouldBe 2
        Tree.depthViaFold(treeNumbers) shouldBe 2
        Tree.depth(treeCharsDepth3) shouldBe 3
        Tree.depthViaFold(treeCharsDepth3) shouldBe 3
        Tree.depth(Leaf(1)) shouldBe 0
        Tree.depthViaFold(Leaf(1)) shouldBe 0
      }
    }

    "map function is used" must {
      "modify each element in a tree with a given function" in {
        val treeCharsUppercase = Branch(
          Branch(Leaf("A"), Leaf("B")),
          Branch(Leaf("C"), Leaf("D")))
        Tree.map(treeChars)(_.toUpperCase) shouldBe treeCharsUppercase

        val treeNumbers1 = Branch(
                            Branch(Leaf(2), Leaf(3)),
                            Branch(Leaf(4), Leaf(5)))
        Tree.map(treeNumbers)(_ + 1) shouldBe treeNumbers1
        Tree.mapViaFold(treeNumbers)(_ + 1) shouldBe treeNumbers1

        Tree.map(Leaf(1))(identity(_)) shouldBe Leaf(1)
        Tree.mapViaFold(Leaf(1))(identity(_)) shouldBe Leaf(1)
      }
    }
  }

}
