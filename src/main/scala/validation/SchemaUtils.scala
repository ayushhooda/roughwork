package validation

import org.apache.spark.sql.{DataFrame, Dataset, Encoder}

import scala.reflect.runtime.universe.TypeTag

object SchemaUtils {

  /**
   * Checks the requirement and returns message if !requirement is true
   * @param requirement - boolean condition/ validation condition
   * @param message - error message
   * @return - Option of error message
   */
  private def require(requirement: Boolean, message: String): Option[String] = {
    if (!requirement) Some(message)
    else None
  }

  /**
   * Provides Dataset of type T
   * @param df - Input Dataframe
   * @tparam T - type T
   * @return - Dataset of type T
   */
  private def prepareDataset[T <: Product with SourceValidation : TypeTag](df: DataFrame): Dataset[T] ={
    implicit val aEncoder: Encoder[T] = org.apache.spark.sql.Encoders.product[T]
    df.as[T]
  }

  /**
   * Prepare Error information at row level
   * @param validationInfo - validation information which was provided at class level
   * @param id - some unique id to keep track of row
   * @return - Error Info if any error has occurred in that row
   */
  private def prepareValidation(validationInfo: List[(Boolean, String)], id: Int): Option[ErrorInfo] = {
    val errorList = validationInfo.flatMap(x => require(x._1, x._2))
    if (errorList.nonEmpty)
      Some(ErrorInfo(id, errorList))
    else None
  }

  /**
   * This method will be consumed by consumer for validation of Dataframe
   * @param df - Input Dataframe
   * @tparam T - Class type
   * @return - Dataset of type Error Info
   */
  def validateDF[T <: Product with SourceValidation : TypeTag](df: DataFrame): Dataset[ErrorInfo] = {
    implicit val encoder: Encoder[ErrorInfo] = org.apache.spark.sql.Encoders.product[ErrorInfo]
    prepareDataset[T](df).flatMap(record => prepareValidation(record.getValidationInfo, record.recordId))
  }

}
