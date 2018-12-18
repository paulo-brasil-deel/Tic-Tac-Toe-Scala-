
import scala.scalajs.js.annotation.JSExportTopLevel

object Jogo {
  val tab:Tabuleiro = new Tabuleiro
  val tema:Temas = Temas()

  var isUmJogador = true

  val jogador1: Jogador = new Jogador("x")
  var jogador2 = new Jogador("o")
  var jogadorAtual: Jogador = jogador1

  @JSExportTopLevel("iniciarJogo")
  def iniciarJogo(): Unit = {
    tema.iniciarTemas()
   }


  //Prepara os dados para a jogada
  @JSExportTopLevel("jogar")
  def converterIDParaCoordenada(posGrid:String): Unit ={
    //encontrando as coordenadas a partir do ID da casa
    val x: Int = posGrid.head.toInt - 48
    val y: Int = posGrid.tail.toInt
    jogar(Coordenada(x, y))
  }

  def jogar(posGrid: Coordenada): Unit = {
    if (checarJogadaValida(posGrid)) {
      atualizarTabuleiro(posGrid)
      tema.atualizarCasa(""+posGrid.x+posGrid.y, jogadorAtual.nomeImg)

      if (gerenciarFimDeJogo(posGrid.x, posGrid.y)){
        if (jogadorAtual.equals(jogador1)) {
          tema.jogar1()
          trocarJogador()

          if (isUmJogador)
            jogarComputador()

        } else {
          tema.jogar2()
          trocarJogador()
        }
      }
    }
  }

  def checarJogadaValida(xy:Coordenada): Boolean = {
    if (!tab.tabuleiro(xy.x)(xy.y).ocupada) true else false
  }

  //Salva a Jogada no tabuleiro atual do jogo
  def atualizarTabuleiro(xy:Coordenada): Unit = {
    tab.tabuleiro(xy.x)(xy.y) = Casa(jogadorAtual, ocupada = true)
  }

  //Retorna um Option com uma lista de 3 coordenadas, justamente as casas vencedoras...
  def checarVitoria(x: Int, y: Int, jogador: Jogador = jogadorAtual): List[String] = {

    def verificarVertical(x: Int): List[String] = {
      if (tab.tabuleiro(x)(y).ocupada && tab.tabuleiro(x)(y).jogador.equals(jogador)) {
        if (x == 2) List(x.toString+y.toString)
        else{
          val proximo = verificarVertical(x + 1)
          if(proximo.nonEmpty) List(x.toString + y.toString):::proximo else Nil
        }
      } else Nil
    }

    def verificarHorizontal(y: Int): List[String] = {
      if (tab.tabuleiro(x)(y).ocupada && tab.tabuleiro(x)(y).jogador.equals(jogador)) {
        if (y == 2) List(x.toString + y.toString)
        else {
          val proximo = verificarHorizontal(y + 1)
          if (proximo.nonEmpty) List(x.toString + y.toString):::proximo else Nil
        }
      }else Nil
    }

    def verificarDiagonal(): List[String] = {

      def verifDiagonalAscendente(x:Int, y:Int): List[String] ={
        if(x+y==2 && tab.tabuleiro(x)(y).ocupada && tab.tabuleiro(x)(y).jogador.equals(jogador)){
          if (x == 0) List(x.toString + y.toString)
          else {
            val proximo = verifDiagonalAscendente(x-1, y+1)
            if (proximo.nonEmpty) List(x.toString + y.toString):::proximo else Nil
          }
        }else Nil
      }

      def verifDiagonalDescendente(x:Int, y:Int): List[String] ={
        if(x==y && tab.tabuleiro(x)(y).ocupada && tab.tabuleiro(x)(y).jogador.equals(jogador)){
          if (x == 2) List(x.toString + y.toString)
          else {
            val proximo = verifDiagonalDescendente(x+1, y+1)
            if (proximo.nonEmpty) List(x.toString + y.toString):::proximo else Nil
          }
        }else Nil
      }

      var resultadoDiag = verifDiagonalAscendente(2,0)
      if(resultadoDiag!=Nil) resultadoDiag
      else {
        resultadoDiag = verifDiagonalDescendente(0,0)
        if(resultadoDiag!=Nil) resultadoDiag else Nil
      }
    }

    var resultado = verificarVertical(0)

    if (resultado!=Nil) resultado
    else{
      resultado = verificarHorizontal(0)
      if (resultado!=Nil) resultado
      else{
        resultado = verificarDiagonal()
        if (resultado!=Nil) resultado
        else Nil
      }
    }
  }

  def checarEmpate(x:Int=0, y:Int=0): Boolean = {
    if(x>2) true
    else{
      if(tab.tabuleiro(x)(y).ocupada){
        if(y==2) checarEmpate(x+1)
        else checarEmpate(x, y+1)
      }else false
    }
  }

  //Proibe o jogador de fazer jogadas depois do fim do jogo
  def travarTabuleiro(x:Int=0, y:Int=0): Unit = {
    if(x<=2){
      if(!tab.tabuleiro(x)(y).ocupada)
        tab.tabuleiro(x)(y) = Casa(tab.tabuleiro(x)(y).jogador, ocupada = true)

      if(y==2) travarTabuleiro(x + 1)
        else travarTabuleiro(x, y + 1)
    }
  }

  //retorna false se o jogo para, retorna true se o jogo continua
  def gerenciarFimDeJogo(x:Int, y:Int) : Boolean = {
    val sequenciaVitoria = checarVitoria(x, y)

    if (sequenciaVitoria!=Nil) {
      tema.mostrarSequenciaVitoria(sequenciaVitoria)
      travarTabuleiro()
      tema.terminarJogo(jogadorAtual.nomeImg)
      false
    }else {
      if (checarEmpate()) {
        tema.terminarJogoEmpate()
        false
      }else true
    }
  }

  def jogarComputador(): Unit ={
    val jogadaIA = jogador2.asInstanceOf[Computador].jogarAuto()
    jogar(jogadaIA)
  }

  def trocarJogador(): Unit ={
    if(jogadorAtual==jogador1) jogadorAtual=jogador2
    else jogadorAtual=jogador1
  }

  def umaPessoa(): Unit ={
    jogador2 = Computador("o")
    isUmJogador = true
  }

  def duasPessoas(): Unit ={
    jogador2 = new Jogador("o")
    isUmJogador = false
  }
}