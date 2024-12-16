package operaciones

import estructuras.*


def addKey(json: ValorJson, path: List[String], clave: String, valor: ValorJson): ValorJson = {
  path match {
    case Nil => json match {
      case ObjetoJson(campos) => ObjetoJson(campos + (clave -> valor))
      case _ => throw new IllegalArgumentException("El path especificado no es un objeto JSON.")
    }
    case head :: tail => json match {
      case ObjetoJson(campos) =>
        val subJson = campos.getOrElse(head, ObjetoJson(Map()))
        ObjetoJson(campos + (head -> addKey(subJson, tail, clave, valor)))
      case ArregloJson(items) =>
        val index = indexarArrJSON(head)
        val subJson = obtenerElementoEnIndice(items, index)
        ArregloJson(reemplazarEnIndice(index, addKey(subJson, tail, clave, valor), items))
      case _ => throw new IllegalArgumentException("El path especificado no existe o no es un objeto JSON.")
    }
  }
}
def addItem(json: ValorJson, path: List[String], item: ValorJson): ValorJson = {
  path match {
    case Nil => json match {
      case ArregloJson(items) => ArregloJson(items :+ item)
      case _ => throw new IllegalArgumentException("El path especificado no es una lista JSON.")
    }
    case head :: tail => json match {
      case ArregloJson(items) =>
        val index = indexarArrJSON(head)
        val subJson = obtenerElementoEnIndice(items, index)
        ArregloJson(reemplazarEnIndice(index, addItem(subJson, tail, item), items))
      case ObjetoJson(campos) =>
        val subJson = campos.getOrElse(head, ObjetoJson(Map()))
        ObjetoJson(campos + (head -> addItem(subJson, tail, item)))
      case _ => throw new IllegalArgumentException("El path especificado no existe o no es una lista JSON.")
    }
  }
}

def delete(json: ValorJson, path: List[String]): ValorJson = {
  path match {
    case Nil => NuloJson
    case head :: Nil => json match {
      case ObjetoJson(campos) => ObjetoJson(campos - head)
      case ArregloJson(items) =>
        val index = indexarArrJSON(head)
        ArregloJson(borrarEnIndice(index, items))
    }
    case head :: tail => json match {
      case ObjetoJson(campos) =>
        val subJson = campos.getOrElse(head, ObjetoJson(Map()))
        ObjetoJson(campos + (head -> delete(subJson, tail)))
      case ArregloJson(items) =>
        val index = indexarArrJSON(head)
        val subJson = obtenerElementoEnIndice(items, index)
        ArregloJson(reemplazarEnIndice(index, delete(subJson, tail), items))
      case _ => throw new IllegalArgumentException("El path especificado no existe o no es un objeto JSON.")
    }
  }
}

def merge(json1: ValorJson, json2: ValorJson): ValorJson = {
  (json1, json2) match {
    case (ObjetoJson(campos1), ObjetoJson(campos2)) =>
      val superCampos = campos2 ++ campos1
      ObjetoJson(superCampos)
    case (ArregloJson(items1), ArregloJson(items2)) =>
      val items = items1 ::: items2
      ArregloJson(items)
    case _ => throw new IllegalArgumentException("Los JSONs provistos NO matchean como para ser mergeados")
  }
}

def edit(json: ValorJson, path: List[String], valor: ValorJson): ValorJson = {
  path match {
    case Nil => valor
    case head :: tail => json match {
      case ObjetoJson(campos) =>
        val subJson = campos.getOrElse(head, ObjetoJson(Map()))
        ObjetoJson(campos + (head -> edit(subJson, tail, valor)))
      case ArregloJson(items) =>
        val index = indexarArrJSON(head)
        val subJson = obtenerElementoEnIndice(items, index)
        ArregloJson(reemplazarEnIndice(index, edit(subJson, tail, valor), items))
      case _ => throw new IllegalArgumentException("El path especificado no existe o no es un objeto JSON.")
    }
  }
}

def flatten(json: ValorJson): ValorJson = {
  json match {
    case ArregloJson(arrays) =>
      val elementosAplanados = arrays.collect {
        case ArregloJson(innerArray) => innerArray
        case _ => throw new IllegalArgumentException("El JSON no es un array de arrays.")
      }.flatten
      ArregloJson(elementosAplanados)
    case _ =>
      throw new IllegalArgumentException("El JSON de entrada debe ser un array de arrays.")
  }
}

def getPath(json: ValorJson, path: List[String]): ValorJson = {
  path match {
    case Nil => json
    case head :: tail => json match {
      case ObjetoJson(campos) => getPath(campos.getOrElse(head, NuloJson), tail)
      case ArregloJson(items) =>
        val index = indexarArrJSON(head)
        val subJson = obtenerElementoEnIndice(items, index)
        getPath(subJson, tail)
      case _ => throw new IllegalArgumentException("El path especificado no se corresponde con el JSON.")
    }
  }
}

def depth(json: ValorJson, nivel: Int): ArregloJson = {
  def depthRec(json: ValorJson, nivelActual: Int): List[ValorJson] = {
    nivelActual match{
      case 0 => List(json)
      case _ => json match {
        case ObjetoJson(campos) =>
          campos.values.flatMap(valor => depthRec(valor, nivelActual - 1)).toList
        case ArregloJson(items) =>
          items.flatMap(item => depthRec(item, nivelActual - 1))
        case _ => List()
      }
    }
  }
  ArregloJson(depthRec(json, nivel))
}

def existsKey(json: ValorJson, clave: String): Boolean = {
  json match {
    case ObjetoJson(campos) => campos.contains(clave)
    case _ => throw new IllegalArgumentException("El path especificado no es un objeto JSON.")
  }
}

def exists_key_rec(json: ValorJson, key: String): Boolean = {
  json match {
    case ObjetoJson(campos) =>
      campos.contains(key) || campos.values.exists(v => exists_key_rec(v, key))

    case ArregloJson(items) =>
      items.exists(item => exists_key_rec(item, key))

    case _ => false
  }
}
