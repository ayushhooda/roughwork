package validation

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * This is Consumer side code
 */
object DFSchemaValidator extends App {

  // create spark session
  val spark: SparkSession = SparkSession.builder().appName("SchemaValidator").master("local[*]").getOrCreate()

  // read dataframe
  import spark.implicits._
  val df: DataFrame = Seq((1, "Ayush", "Hooda", "Delhi"), (2, "eeeeeee", "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", "Delhi"))
    .toDF("id", "firstName", "lastName", "city")

  // call validation method
  val res = SchemaUtils.validateDF[User](df)

  res.show(false)

}
