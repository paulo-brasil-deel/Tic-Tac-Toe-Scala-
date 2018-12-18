import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom
import dom.{document, html}

object Menu {
  var nomeTema = "simples"
  var fundoTema = ""

  @JSExportTopLevel("escolherTema")
  def escolherTema(id:String): Unit ={
    nomeTema = id

    nomeTema match {
      case "simples" =>
        document.getElementById("temaEscolhidoLabel").innerHTML = "Simples"
        fundoTema = "#ffffff"
      case "natal" =>
        document.getElementById("temaEscolhidoLabel").innerHTML = "Natal"
        fundoTema = "#f49797"
      case "jathon" =>
        document.getElementById("temaEscolhidoLabel").innerHTML = "Java vs Phyton"
        fundoTema = "#52b7ad"
      case "catDog" =>
        document.getElementById("temaEscolhidoLabel").innerHTML =  "Gato vs Cachorro"
        fundoTema = "#af8379"
      case "dinoOro" =>
        document.getElementById("temaEscolhidoLabel").innerHTML = "Dinossauro vs Meteoro"
        fundoTema = "#e2e2e2"
    }
  }

  @JSExportTopLevel("iniciarPartida")
  def iniciarJogo(): Unit ={
    if(document.getElementById("switch-shadow").asInstanceOf[html.Input].checked){
      Jogo.duasPessoas()
    }else{
      Jogo.umaPessoa()
    }
    document.getElementById("menu").asInstanceOf[html.Div].style.display = "none"
    document.getElementById("tela").asInstanceOf[html.Div].style.display = "block"
  }

  @JSExportTopLevel("voltarMenu")
  def voltarMenu(): Unit ={
    document.getElementById("menu").asInstanceOf[html.Div].style.display = "block"
    document.getElementById("tela").asInstanceOf[html.Div].style.display = "none"
  }

  @JSExportTopLevel("iniciarMenu")
  def iniciarMenu(): Unit ={
    document.getElementById("menu").asInstanceOf[html.Div].style.display = "block"
    document.getElementById("tela").asInstanceOf[html.Div].style.display = "none"
  }
}
