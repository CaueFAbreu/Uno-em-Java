package model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Carta> mao;
    private boolean gritouUno;

    public Jogador(String nome) {
        this.nome = nome;
        this.mao = new ArrayList<>();
        this.gritouUno = false;
    }

    public String getNome() { return nome; }
    public List<Carta> getMao() { return mao; }


    public boolean isGritouUno() { return gritouUno; }


    public void comprarCarta(Carta c) {
        this.mao.add(c);
        this.gritouUno = false; // Se comprou, não está mais em situação de UNO
    }


    public void jogarCarta(Carta c) {
        this.mao.remove(c);
    }

    // Método para a regra do UNO
    public void dizerUno() {
        this.gritouUno = true;
    }

    // Facilita verificar se o jogador venceu (mão vazia)
    public boolean temCartas() {
        return !this.mao.isEmpty();
    }
}