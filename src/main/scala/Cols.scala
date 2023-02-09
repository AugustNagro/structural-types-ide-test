import scala.deriving.Mirror

case class SqlNameMap(scalaName: String, dbName: String)

class Cols(nameMaps: Array[SqlNameMap]) extends Selectable:
  def selectDynamic(scalaName: String): String =
    nameMaps.find(_.scalaName == scalaName).get.dbName

type ColsRef[T, Mels, Mets] = (Mels, Mets) match
  case (EmptyTuple, EmptyTuple) => T
  case ((met *: mets), (mel *: mels)) => Mirror.Product {}


inline def cols[T <: Product](using m: Mirror.ProductOf[T]): ColsRef[T, m.MirroredElemLabels, m.MirroredElemTypes] =
  // todo
  // Cols(Array(SqlNameMap("firstName", "first_name"))).asInstanceOf[ColsRef[T, m.MirroredElemTypes]]
  ???
