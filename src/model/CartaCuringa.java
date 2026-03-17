package model;

import controller.Jogo;

public class CartaCuringa extends Carta {

    public CartaCuringa(Cor cor, Valor valor) {
        super(cor, valor);
    }

    @Override
    public void aplicarEfeito(Jogo mesa) {
        switch (this.valor) {
            case CORINGA -> {


            }
            case CORINGA_MAIS_QUATRO -> {

                mesa.avancarTurno();
                mesa.comprarCartas(4);

            }
        }
    }
}