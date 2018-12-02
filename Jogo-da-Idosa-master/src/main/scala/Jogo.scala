import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom
import dom.{document, html}

object Jogo {
  var tabuleiro = Array.ofDim[Casa](3, 3)
  val nomeTema = "novo"

  val jogador1: Jogador = Jogador("Diego", "x")
  val jogador2: Jogador = Jogador("Eduarda", "o")
  var jogadorAtual: Jogador = jogador1

  @JSExportTopLevel("iniciarJogo")
  def iniciarJogo(): Unit = {
    tabuleiro = Array(Array(Casa(), Casa(), Casa()),
      Array(Casa(), Casa(), Casa()),
      Array(Casa(), Casa(), Casa()))
    document.getElementById("jogador1").innerHTML = jogador1.nome;
    document.getElementById("jogador2").innerHTML = jogador2.nome;
    document.getElementById(elementId = "infoJogador2").asInstanceOf[html.Button].setAttribute("class", "infoJogadorTransparente")

  }

  @JSExportTopLevel("getClass")
  def getButtonClass(classeBotao: String): Unit = {
    val e = document.getElementById("console")
    e.innerHTML = classeBotao
  }

  @JSExportTopLevel("jogar")
  def jogar(posGrid: String): Unit = {
    val x: Int = posGrid.head.toInt - 48
    val y: Int = posGrid.tail.toInt

    if (checarJogadaValida(tabuleiro, x, y)) {
      val casa = document.getElementById(elementId = posGrid)
      val nomeJogador = document.getElementById(elementId = "jogadorAtual")

      atualizarTabuleiro(tabuleiro, x, y)
      casa.innerHTML = "<img src=\"../resources/"+ nomeTema + "/" + jogadorAtual.nomeImg+ ".png\"/>"

      if (checarVitoria(tabuleiro, x, y, jogadorAtual)) {
        terminarJogo("Parabens, " + jogadorAtual.nome)
      } else if (checarEmpate(tabuleiro)) {
        terminarJogo("Empate!")
      } else {
        if (jogadorAtual.equals(jogador1)) {
          document.getElementById(elementId = "infoJogador1").asInstanceOf[html.Div].setAttribute("class", "infoJogadorTransparente")
          document.getElementById(elementId = "infoJogador2").asInstanceOf[html.Div].setAttribute("class", "infoJogador")
          jogadorAtual = jogador2
        } else {
          document.getElementById(elementId = "infoJogador2").asInstanceOf[html.Div].setAttribute("class", "infoJogadorTransparente")
          document.getElementById(elementId = "infoJogador1").asInstanceOf[html.Div].setAttribute("class", "infoJogador")
          jogadorAtual = jogador1
        }
        if (checarIA()) {
          jogarIA()
        }
      }
    }else{
      println("Jogada Errada")
    }
  }

  //lembrar de passar a matriz
  def checarJogadaValida(tabuleiro: Array[Array[Casa]], x: Int, y: Int): Boolean = {
    if (!tabuleiro(x)(y).ocupada)
      true
    else
      false
  }

  def atualizarTabuleiro(array: Array[Array[Casa]], x: Int, y: Int): Unit = {
    tabuleiro(x)(y) = Casa(jogadorAtual, true)
  }

  def checarVitoria(tabAtual: Array[Array[Casa]], x: Int, y: Int, jogador: Jogador): Boolean = {

    def verificarVertical(x: Int): Boolean = {
      if (tabAtual(x)(y).ocupada && tabAtual(x)(y).jogador.equals(jogador)) {
        if (x == 2) true else verificarVertical(x + 1)
      } else {
        false
      }
    }

    def verificarHorizontal(y: Int): Boolean = {
      if (tabAtual(x)(y).ocupada && tabAtual(x)(y).jogador.equals(jogador))
        if (y == 2) true else verificarHorizontal(y + 1)
      else
        false
    }

    def verificarDiagonal(): Boolean = {

      def verifDiagonalAscendente(x:Int, y:Int): Boolean ={
        if(x+y==2 && tabAtual(x)(y).ocupada && tabAtual(x)(y).jogador.equals(jogador))
          if(x==0) true else verifDiagonalAscendente(x-1, y+1)
        else
          false
      }

      def verifDiagonalDescendente(x:Int, y:Int): Boolean ={
        if(x==y && tabAtual(x)(y).ocupada && tabAtual(x)(y).jogador.equals(jogador))
          if(x==2) true else verifDiagonalDescendente(x+1, y+1)
        else
          false
      }

      if(verifDiagonalAscendente(2,0))
        true
      else if(verifDiagonalDescendente(0,0))
        true
      else
        false
    }

    if (verificarVertical(0)) {
      true
    } else if (verificarHorizontal(0)){
      true
    }else if (verificarDiagonal()){
      true
    }else {
      false
    }
  }

  def checarEmpate(tabAtual:Array[Array[Casa]], x:Int=0, y:Int=0): Boolean = {
    if(x>2){
      true
    }else{
      if(tabAtual(x)(y).ocupada){
        if(y==2)
          checarEmpate(tabAtual, x+1, 0)
        else
          checarEmpate(tabAtual, x, y+1)
      }else{
        false
      }
    }
  }

  def checarIA(): Boolean = false

  def jogarIA(): Unit ={}

  def terminarJogo(texto:String) = println(texto)
}