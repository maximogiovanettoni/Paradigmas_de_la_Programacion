package codificador

import estructuras.*

def codificar(json: ValorJson,indentacion:String=""): String = {
  json match {
    case ObjetoJson(campos) => "{\n"+codificar_objeto(campos,indentacion+"\t")+"\n"+indentacion+"}"
    case ArregloJson(items) => "[\n"+codificar_array_rec(items,indentacion+"\t")+"\n"+indentacion+"]"
    case CadenaJson(valor) => "\""+valor+"\""
    case NumeroJson(valor) => valor.toString
    case BooleanoJson(valor) => valor.toString
    case NuloJson => "null"
  }
}

def codificar_objeto(dicc:Map[String,ValorJson],indentacion:String=""): String = {
  codificar_objeto_rec(dicc.toList,indentacion)
}

def codificar_objeto_rec(items : List[(String,ValorJson)],indentacion: String=""): String = {
  items match {
    case Nil => ""
    case (k,v)::Nil => indentacion+"\""+k+"\" :"+ codificar(v,indentacion)
    case (k,v)::resto => indentacion+"\""+k+"\" :"+ codificar(v,indentacion)+",\n"+ codificar_objeto_rec(resto,indentacion)
  }
}

def codificar_array_rec(items : List[ValorJson],indentacion: String=""): String ={
  items match{
    case Nil => ""
    case head :: Nil => indentacion + codificar(head,indentacion)
    case head :: resto => indentacion + codificar(head,indentacion) +",\n" +codificar_array_rec(resto,indentacion)
  }
}
