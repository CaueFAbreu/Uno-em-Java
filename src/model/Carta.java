package model;
import controller.Jogo;


public abstract class Carta {
    // Usamos 'protected' para que as classes filhas possam acessar essas variáveis
    protected Cor cor;
    protected Valor valor;

    // Construtor
    public Carta(Cor cor, Valor valor) {
        this.cor = cor;
        this.valor = valor;
    }

    // Getters para checar as regras do jogo depois
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