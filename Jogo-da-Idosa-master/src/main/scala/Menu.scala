import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom
import dom.{document, html}

object Menu {
  var nomeJogador1 = "Jogador 1"
  var nomeJogador2 = "Jogador 2"

  var nomeTema = "classico"

  @JSExportTopLevel("selecionarUmJogador")
  def selecionarUmJogador(): Unit ={
    val botaoDoisJog = document.getElementById(elementId = "doisJogBotao").asInstanceOf[html.Button]
    botaoDoisJog.setAttribute("class", "botaoOpaco")

    val botaoUmJog = document.getElementById(elementId = "umJogBotao").asInstanceOf[html.Button]
    botaoUmJog.setAttribute("class", "botaoTransparente")

    val entrada2 = document.getElementById("entradaJogador2").asInstanceOf[html.Input]
    entrada2.style.display = "none"
  }

  @JSExportTopLevel("selecionarDoisJogadores")
  def selecionarDoisJogadores(): Unit ={
    val botaoDoisJog = document.getElementById(elementId = "doisJogBotao").asInstanceOf[html.Button]
    botaoDoisJog.setAttribute("class", "botaoTransparente")

    val botaoUmJog = document.getElementById(elementId = "umJogBotao").asInstanceOf[html.Button]
    botaoUmJog.setAttribute("class", "botaoOpaco")

    val entrada2 = document.getElementById("entradaJogador2").asInstanceOf[html.Input]
    entrada2.style.display = "block"
  }

  @JSExportTopLevel("escolherTema")
  def escolherTema(id:String): Unit ={
    nomeTema = id
  }

  def iniciarJogo(): Unit ={

  }
}
