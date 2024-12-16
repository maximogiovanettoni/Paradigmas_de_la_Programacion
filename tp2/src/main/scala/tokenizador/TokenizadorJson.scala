package tokenizador

sealed trait Token
case class TokenCadena(valor: String) extends Token
case class TokenNumero(valor: Double) extends Token
case class TokenBooleano(valor: Boolean) extends Token
case object TokenNull extends Token
case object LlaveIzquierda extends Token
case object LlaveDerecha extends Token
case object CorcheteIzquierda extends Token
case object CorcheteDerecha extends Token
case object DosPuntos extends Token
case object Coma extends Token

def tokenizar(json: String): List[Token] = {
  def tokenizarRec(chars: List[Char], tokens: List[Token]): List[Token] =

    chars match {
      case Nil => tokens.reverse
      case '\n' :: tail => tokenizarRec(tail, tokens)
      case '\t' :: tail => tokenizarRec(tail, tokens)
      case ' ' :: tail => tokenizarRec(tail, tokens)
      case '{' :: tail => tokenizarRec(tail, LlaveIzquierda :: tokens)
      case '}' :: tail => tokenizarRec(tail, LlaveDerecha :: tokens)
      case '[' :: tail => tokenizarRec(tail, CorcheteIzquierda :: tokens)
      case ']' :: tail => tokenizarRec(tail, CorcheteDerecha :: tokens)
      case ':' :: tail => tokenizarRec(tail, DosPuntos :: tokens)
      case ',' :: tail => tokenizarRec(tail, Coma :: tokens)
      case '"' :: tail =>
        val (cadena, resto) = tail.span(_ != '"')
        tokenizarRec(resto.tail, TokenCadena(cadena.mkString) :: tokens)
      case c if Character.isDigit(c.head) || c.head == '-' =>
        val (numero, resto) = chars.span(ch => Character.isDigit(ch) || ch == '.' || ch == '-')
        tokenizarRec(resto, TokenNumero(numero.mkString.toDouble) :: tokens)
      case c if Character.isLetter(c.head) =>
        val (literal, resto) = chars.span(Character.isLetter)
        literal.mkString match {
          case "true" => tokenizarRec(resto, TokenBooleano(true) :: tokens)
          case "false" => tokenizarRec(resto, TokenBooleano(false) :: tokens)
          case "null" => tokenizarRec(resto, TokenNull :: tokens)
          case _ => throw new IllegalArgumentException("Token no reconocido")
        }
      case _ => throw new IllegalArgumentException("Token no reconocido")
    }

  tokenizarRec(json.toList, Nil)

}
