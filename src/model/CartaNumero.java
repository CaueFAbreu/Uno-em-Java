package model;
import controller.Jogo;
public class CartaNumero extends Carta{

    public CartaNumero(Cor cor, Valor valor) {
        super(cor, valor);
    }

    @Override
    public void aplicarEfeito(Jogo mesa) {
        System.out.println("Efeito aplicado! (Em fase de desenvolvimento"); //TODO arrumar os efeitos
    }
}
