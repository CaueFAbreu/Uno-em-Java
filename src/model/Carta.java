package model;
import controller.Jogo;


public abstract class Carta {

    protected Cor cor;
    protected Valor valor;


    public Carta(Cor cor, Valor valor) {
        this.cor = cor;
        this.valor = valor;
    }


    public Cor getCor() {
        return cor;
    }

    public Valor getValor() {
        return valor;
    }

    public abstract void aplicarEfeito(Jogo mesa);

    public void setCor(Cor cor) {
        this.cor = cor; }

    public String toString() {
        return valor + " " + cor;
    }
}