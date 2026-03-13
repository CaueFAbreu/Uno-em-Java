package model;

public class BaralhoUnoConvencional extends Baralho{
    @Override
    public void montarBaralho() {
        Cor[] coresBasicas = {Cor.VERMELHO, Cor.AMARELO, Cor.AZUL, Cor.VERDE};
        for(Cor corAtual : coresBasicas){

            for(int i=0; i<14; i++){
                if (i<11){
                    this.cartas.add(new CartaNumero(corAtual, Valor.values()[i]));
                }
                else if (i==11){
                    this.cartas.add(new CartaAcao(corAtual, Valor.PULAR));
                }
                else if(i==12){
                    this.cartas.add(new CartaAcao(corAtual, Valor.INVERTER));
                }
                else {
                    this.cartas.add(new CartaAcao(corAtual, Valor.MAIS_DOIS));
                }
            }

        }
        this.cartas.add(new CartaCuringa(Cor.PRETO, Valor.CORINGA));
        this.cartas.add(new CartaCuringa(Cor.PRETO, Valor.CORINGA_MAIS_QUATRO));
    }
}
