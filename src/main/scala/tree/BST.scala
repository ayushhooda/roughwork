package tree

import scala.collection.mutable

case class BST[T] (
               data: T,
               left: Option[BST[T]] = None,
               right: Option[BST[T]] = None
               )

object BST extends App {

  // Test object
  val four = Some(BST(4))
  val five = Some(BST(5))
  val six = Some(BST(6))
  val seven = Some(BST(7))
  val two = Some(BST(2, four, five))
  val three = Some(BST(3, six, seven))

  val bstTree = Some(BST[Int](1, two, three))
  print(IterativeTraversal.inorder(bstTree))

}
