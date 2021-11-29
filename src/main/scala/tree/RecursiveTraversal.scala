package tree

object RecursiveTraversal {

  /**
   * traverse a BST in preorder
   * @param root - root of tree
   * @tparam T - type of data in node
   * @return - List of data in preorder
   */
  def preorder[T](root: Option[BST[T]]): List[T] = {
    var list = List[T]()
    def innerFunc(root: Option[BST[T]]): List[T] = {
      root match {
        case None => list
        case Some(node) =>
          list = list :+ node.data
          innerFunc(node.left)
          innerFunc(node.right)
      }
    }
    innerFunc(root)
  }

  /**
   * traverse a BST in inorder
   * @param root - root of tree
   * @tparam T - type of data in node
   * @return - List of data in inorder
   */
  def inorder[T](root: Option[BST[T]]): List[T] = {
    var list = List[T]()
    def innerFunc(root: Option[BST[T]]): List[T] = {
      root match {
        case None => list
        case Some(node) =>
          innerFunc(node.left)
          list = list :+ node.data
          innerFunc(node.right)
      }
    }
    innerFunc(root)
  }

  /**
   * traverse a BST in preorder
   * @param root - root of tree
   * @tparam T - type of data in node
   * @return - List of data in preorder
   */
  def postorder[T](root: Option[BST[T]]): List[T] = {
    var list = List[T]()
    def innerFunc(root: Option[BST[T]]): List[T] = {
      root match {
        case None => list
        case Some(node) =>
          innerFunc(node.left)
          innerFunc(node.right)
          list = list :+ node.data
          list
      }
    }
    innerFunc(root)
  }
}
