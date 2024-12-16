package analizador

import estructuras.*
import tokenizador.*


def parsearJson(tokens: List[Token]): ValorJson = {
  def parsearValor(tokens: List[Token]): (ValorJson, List[Token]) = tokens match {
    case LlaveIzquierda :: resto =>
      val (obj, restantes) = parsearObjeto(resto)
      (ObjetoJson(obj), restantes)
    case CorcheteIzquierda :: resto =>
      val (arr, restantes) = parsearArreglo(resto)
      (ArregloJson(arr), restantes)
    case TokenCadena(valor) :: resto => (CadenaJson(valor), resto)
    case TokenNumero(valor) :: resto => (NumeroJson(valor), resto)
    case TokenBooleano(valor) :: resto => (BooleanoJson(valor), resto)
    case TokenNull :: resto => (NuloJson, resto)
    case _ => throw new IllegalArgumentException("Error: Token inesperado")
  }

  def parsearObjeto(tokens: List[Token], acc: Map[String, ValorJson] = Map()): (Map[String, ValorJson], List[Token]) = tokens match {
    case LlaveDerecha :: resto => (acc, resto)
    case TokenCadena(clave) :: DosPuntos :: resto =>
      val (valor, tokensDespuesValor) = parsearValor(resto)
      tokensDespuesValor match {
        case Coma :: tail => parsearObjeto(tail, acc + (clave -> valor))
        case LlaveDerecha :: tail => (acc + (clave -> valor), tail)
        case _ => throw new IllegalArgumentException("Error: Token inesperado en objeto")
      }
    case _ => throw new IllegalArgumentException("Error: Token inesperado en objeto")
  }


  def parsearArreglo(tokens: List[Token], acc: List[ValorJson] = List()): (List[ValorJson], List[Token]) = tokens match {
    case CorcheteDerecha :: resto => (acc, resto)
    case _ =>
      val (valor, tokensDespuesValor) = parsearValor(tokens)
      tokensDespuesValor match {
        case Coma :: tail => parsearArreglo(tail, acc :+ valor)
        case CorcheteDerecha :: tail => (acc :+ valor, tail)
        case _ => throw new IllegalArgumentException("Error: Token inesperado en arreglo")
      }
  }

  val (json, tokensRestantes) = parsearValor(tokens)
  if (tokensRestantes.nonEmpty) throw new IllegalArgumentException("Error: Tokens no procesados")
  json
}