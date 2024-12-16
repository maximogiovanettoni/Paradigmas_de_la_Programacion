package estructuras

sealed trait ValorJson
case class ObjetoJson(campos: Map[String, ValorJson]) extends ValorJson
case class ArregloJson(items: List[ValorJson]) extends ValorJson
case class CadenaJson(valor: String) extends ValorJson
case class NumeroJson(valor: Double) extends ValorJson
case class BooleanoJson(valor: Boolean) extends ValorJson
case object NuloJson extends ValorJson

def indexarArrJSON(indice : String): Int  ={
  val pattern = """\[(\d+)\]""".r
  indice match {
    case pattern(number) => number.toInt
    case _ => throw new IllegalArgumentException("Indice erroneo")
  }
}

def obtenerElementoEnIndice[A](lista: List[A], indice: Int): A = {
  (lista, indice) match {
    case (Nil, _) => throw new IndexOutOfBoundsException("Índice fuera de los límites de la lista")
    case (head :: _, 0) => head
    case (_ :: tail, i) if i > 0 => obtenerElementoEnIndice(tail, i - 1)
    case _ => throw new IllegalArgumentException("Índice inválido") 
  }
}

def reemplazarEnIndice(indice: Int, reemplazo: ValorJson, lista: List[ValorJson]): List[ValorJson] ={
  def reemplazarEnIndiceRec(indiceActual: Int=0, indiceDeseado: Int, reemplazo: ValorJson, lista: List[ValorJson]): List[ValorJson]= lista match{
    case Nil => throw IndexOutOfBoundsException("Indice fuera de limites")
    case head::tail if indiceActual<indiceDeseado => head :: reemplazarEnIndiceRec(indiceActual+1,indiceDeseado, reemplazo,tail)
    case head::tail if indiceActual==indiceDeseado => reemplazo :: tail
    case _ => throw IndexOutOfBoundsException("ERROR")
  }
  reemplazarEnIndiceRec(0,indice,reemplazo,lista)
}

def borrarEnIndice(indice: Int, lista: List[ValorJson]): List[ValorJson] ={
  def reemplazarEnIndiceRec(indiceActual: Int=0, indiceDeseado: Int, lista: List[ValorJson]): List[ValorJson]= lista match{
    case Nil => throw IndexOutOfBoundsException("Indice fuera de limites")
    case head::tail if indiceActual<indiceDeseado => head :: reemplazarEnIndiceRec(indiceActual+1,indiceDeseado,tail)
    case head::tail if indiceActual==indiceDeseado => tail
    case _ => throw IndexOutOfBoundsException("ERROR")
  }
  reemplazarEnIndiceRec(0,indice,lista)
}
