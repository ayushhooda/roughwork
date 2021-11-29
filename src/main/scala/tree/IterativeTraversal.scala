package tree

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object IterativeTraversal {

  def preorder[T](root: Option[BST[T]]): List[T] = {
    val res = new mutable.ListBuffer[Option[T]]()
    val stack = mutable.Stack[Option[BST[T]]]()
    stack.push(root)
    while (stack.nonEmpty) {
      val node = stack.pop()
      res.addOne(node.map(_.data))
      if (node.flatMap(_.right).isDefined) {
        stack.push(node.flatMap(_.right))
      }
      if (node.flatMap(_.left).isDefined) {
        stack.push(node.flatMap(_.left))
      }
    }
    res.flatten.toList
  }

  def inorder[T](root: Option[BST[T]]): List[T] = {
    val res = new mutable.ListBuffer[Option[T]]()
    val stack = mutable.Stack[Option[BST[T]]]()
    var currentNode = root
    var done = false
    while (!done) {
      if (currentNode.isDefined) {
        stack.push(currentNode)
        currentNode = currentNode.flatMap(_.left)
      } else {
        if (stack.isEmpty) {
          done = true
        } else {
          currentNode = stack.pop()
          res.addOne(currentNode.map(_.data))
          currentNode = currentNode.flatMap(_.right)
        }
      }
    }
    res.flatten.toList
  }

  def postorder[T](root: Option[BST[T]]): List[T] = {
    val res = new ListBuffer[Option[T]]()
    val stack = mutable.Stack[Option[BST[T]]]()
    List()
  }

  def levelorder[T](root: Option[BST[T]]): List[T] = {
    List()
  }

}
