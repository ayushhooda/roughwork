package validation

trait SourceValidation {
  def recordId : Int
  def getValidationInfo: List[(Boolean, String)]
}

case class User(
               id: Integer,
               firstName: String,
               lastName: String,
               city: String
               ) extends SourceValidation {

  override def getValidationInfo: List[(Boolean, String)] = {
    List((firstName.length < 20, "first name length check violated"),
      (lastName.length < 20 , "last name length check violated"))
  }

  override def recordId: Int = id
}

/**
 * case class for holding error info
 * @param recordId - some unique id to keep track of row
 * @param errorDetails - list of errors occurred in the corresponding row
 */
case class ErrorInfo(
                    recordId: Int,
                    errorDetails: List[String]
                    )
