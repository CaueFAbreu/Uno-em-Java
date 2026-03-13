package model;
import controller.Jogo;
public class CartaAcao extends Carta {

    public CartaAcao(Cor cor, Valor valor) {
        super(cor, valor);
    }

    @Override
    public void aplicarEfeito(Jogo mesa) {
        switch(this.valor){
            case INVERTER ->  mesa.inverterSentido();
            case PULAR ->  mesa.avancarTurno();
            case MAIS_DOIS ->  {
                mesa.avancarTurno();
            mesa.comprarCartas(2);
            }
        }
    }
}
