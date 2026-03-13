package model;

import static model.Cor.*;

public class BaralhoUnoOficial extends Baralho {
    @Override
    public void montarBaralho() {
        Cor[] coresBasicas = {Cor.VERMELHO, Cor.AMARELO, Cor.AZUL, Cor.VERDE};

        for (Cor corAtual : coresBasicas) {
            // Adiciona o ZERO dessa cor (só tem 1 no UNO)
            this.cartas.add(new CartaNumero(corAtual, Valor.ZERO));

            // Adiciona os números de 1 a 9 (tem 2 de cada no UNO)
            for (int i = 1; i <= 9; i++) {
                // Pega o Enum correspondente ao número do 'i'
                Valor valorAtual = Valor.values()[i];

                // Adiciona a primeira cópia
                this.cartas.add(new CartaNumero(corAtual, valorAtual));
                // Adiciona a segunda cópia
                this.cartas.add(new CartaNumero(corAtual, valorAtual));
            }
            //Cartas Especiais
            for (int i=11; i<14; i++) {
                this.cartas.add(new CartaAcao(corAtual, Valor.values()[i]));
                this.cartas.add(new CartaAcao(corAtual, Valor.values()[i]));
            }

            }
        for(int i=0; i<4; i++){
            this.cartas.add(new CartaCuringa(Cor.PRETO, Valor.CORINGA));
            this.cartas.add(new CartaCuringa(Cor.PRETO, Valor.CORINGA_MAIS_QUATRO));
        }
    }
}
