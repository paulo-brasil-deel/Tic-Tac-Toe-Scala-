case class Computador(nomeImagem:String) extends Jogador(nomeImagem){

def jogarAuto(): Coordenada ={
    val listaPossiveis = verificarPossiveis(List(), Jogo.tab)
    val alatorio = scala.util.Random.nextInt(listaPossiveis.size-1)
    listaPossiveis(alatorio)
  }

  def verificarPossiveis(listaPossiveis:List[Coordenada], tabAtual:Tabuleiro, x:Int=0, y:Int=0): List[Coordenada] ={
    if(x<=2 &y<=2){
      if(x==2){
        if(!tabAtual.tabuleiro(x)(y).ocupada)
          verificarPossiveis(listaPossiveis :+ Coordenada(x, y), tabAtual, 0, y+1)
        else
          verificarPossiveis(listaPossiveis, tabAtual, 0, y+1)
      }else{
        if(!tabAtual.tabuleiro(x)(y).ocupada)
          verificarPossiveis(listaPossiveis :+ Coordenada(x, y), tabAtual, x+1, y)
        else
          verificarPossiveis(listaPossiveis, tabAtual, x+1, y)
      }
    }else{
      listaPossiveis
    }
  }
}
