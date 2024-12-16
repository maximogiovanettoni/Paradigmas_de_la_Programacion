package junqtional

import estructuras.ValorJson
import tokenizador.tokenizar
import analizador.parsearJson
import ejecutor.ejecutar
import codificador.codificar

import scala.io.Source

object Junqtional {

  def main(args: Array[String]): Unit = {

    if (args.isEmpty) {
      Console.err.println("Error: Se debe proporcionar una operación y sus argumentos.")
      return
    }

    val entradaJSON = Source.stdin.getLines()mkString("\n")
    val json = parsearJson(tokenizar(entradaJSON))

    val operacion = args(0)
    val parametros = args.drop(1)

    try {
      val resultado = ejecutar(operacion, json, parametros)
      mostrarResultado(resultado)
    } catch {
      case e: IllegalArgumentException => println(s"Error: ${e.getMessage}")
      case _: Throwable => Console.err.println("Error: Error inesperado.")
    }
  }

  def mostrarResultado(resultado: Any): Unit = {
    resultado match {
      case json: ValorJson => Console.out.println(codificar(json))
      case booleano: Boolean => Console.out.println(booleano)
      case _ => Console.err.println("Error: Resultado inesperado.")
    }
  }
}