import org.scalajs.dom.{document, html}

case class Temas(nomeTema:String = Menu.nomeTema){

  def iniciarTemas(): Unit ={
    document.getElementById("console").asInstanceOf[html.Div]
      .style.backgroundImage = "url(../resources/"+ nomeTema + "/fundo.png)"

    document.getElementById("infoJogador1").asInstanceOf[html.Div]
      .style.backgroundImage = "url(../resources/"+ nomeTema + "/x.png)"

    document.getElementById("infoJogador2").asInstanceOf[html.Div]
      .style.backgroundImage = "url(../resources/"+ nomeTema + "/o.png)"

    document.getElementById("infoJogador2").asInstanceOf[html.Button]
      .setAttribute("class", "infoJogadorTransparente")

    document.getElementById("tela").asInstanceOf[html.Div].style.backgroundColor = Menu.fundoTema
  }

  def jogar1(): Unit ={
    document.getElementById("infoJogador1").asInstanceOf[html.Div]
      .setAttribute("class", "infoJogadorTransparente")

    document.getElementById("infoJogador2").asInstanceOf[html.Div]
      .setAttribute("class", "infoJogador")
  }

  def jogar2(): Unit ={
    document.getElementById("infoJogador2").asInstanceOf[html.Div]
      .setAttribute("class", "infoJogadorTransparente")

    document.getElementById("infoJogador1").asInstanceOf[html.Div]
      .setAttribute("class", "infoJogador")
  }

  def atualizarCasa(posGrid:String, nomeImg:String): Unit ={
    document.getElementById(posGrid)
      .innerHTML = "<img src=\"../resources/"+ nomeTema + "/" + nomeImg+ ".png\"/>"
  }

  def terminarJogo(nomeImg:String) :Unit = {
    document.getElementById("fimJogoTela").asInstanceOf[html.Div].style.display = "block"

    document.getElementById("infoJogadorVencedor").asInstanceOf[html.Div]
      .style.backgroundImage = "url(../resources/"+ nomeTema + "/"+ nomeImg +".png)"

    document.getElementById("barraLateralTela").asInstanceOf[html.Div].style.display = "none"
  }

  def terminarJogoEmpate(): Unit ={
    document.getElementById("fimJogoEmpateTela").asInstanceOf[html.Div].style.display = "block"
    document.getElementById("barraLateralTela").asInstanceOf[html.Div].style.display = "none"
  }

  def mostrarSequenciaVitoria(sequencia: List[String]): Unit ={
    atualizarCasaTransparente(sequencia.head)
    atualizarCasaTransparente(sequencia.tail.head)
    atualizarCasaTransparente(sequencia.last)
  }

  def atualizarCasaTransparente(xy:String): Unit ={
    document.getElementById(xy).asInstanceOf[html.Button].setAttribute("class", "casaTransparente")
  }
}
