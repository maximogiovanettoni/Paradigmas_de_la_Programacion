package ejecutor

import estructuras.*
import tokenizador.tokenizar
import analizador.parsearJson
import operaciones.*

def ejecutar(operacion: String, json: ValorJson, args: Array[String]): Any = {
  operacion match {
    case "add-key" => ejecutarAddKey(json, args)
    case "add-item" => ejecutarAddItem(json, args)
    case "get" => ejecutarGetPath(json, args)
    case "exists_key" => ejecutarExistsKey(json, args)
    case "exists_key_rec" => ejecutarExistsKeyRec(json, args)
    case "edit" => ejecutarEdit(json, args)
    case "delete" => ejecutarDelete(json, args)
    case "merge" => ejecutarMerge(json, args)
    case "flatten" => ejecutarFlatten(json)
    case "depth" => ejecutarDepth(json, args)
    case _ => throw new IllegalArgumentException(s"Operación desconocida: $operacion")
  }
}

def interpretarPath(path: String): List[String] = {
  path match {
    case "." => List()
    case _ =>

      val regex = """\w+|\[\d+\]""".r
      regex.findAllIn(path).toList
  }
}

private def ejecutarAddKey(json: ValorJson, args: Array[String]): ValorJson = {
  val path = interpretarPath(args(0))
  val clave = args(1)
  val valor = parsearJson(tokenizar(args(2)))
  addKey(json, path, clave, valor)
}

private def ejecutarAddItem(json: ValorJson, args: Array[String]): ValorJson = {
  val path = interpretarPath(args(0))
  val item = parsearJson(tokenizar(args(1)))
  addItem(json, path, item)
}

private def ejecutarGetPath(json: ValorJson, args: Array[String]): ValorJson = {
  val path = interpretarPath(args(0))
  getPath(json, path)
}

private def ejecutarExistsKey(json: ValorJson, args: Array[String]): Boolean = {
  existsKey(json, args(0))
}

private def ejecutarExistsKeyRec(json: ValorJson, args: Array[String]): Boolean = {
  val key = args(0)
  exists_key_rec(json, key)
}

private def ejecutarEdit(json: ValorJson, args: Array[String]): ValorJson = {
  val path = interpretarPath(args(0))
  val valor = parsearJson(tokenizar(args(1)))
  edit(json, path, valor)
}

private def ejecutarDelete(json: ValorJson, args: Array[String]): ValorJson = {
  val path = interpretarPath(args(0))
  delete(json, path)
}

private def ejecutarMerge(json: ValorJson, args: Array[String]): ValorJson = {
  val otroJson = parsearJson(tokenizar(args(0)))
  merge(json, otroJson)
}

private def ejecutarFlatten(json: ValorJson): ValorJson = {
  flatten(json)
}

private def ejecutarDepth(json: ValorJson, args: Array[String]): ArregloJson = {
  val nivel = args(0).toInt
  depth(json, nivel)
}

