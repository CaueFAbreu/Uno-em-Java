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

    public String getNome() {
        return nome;
    }

    public List<Carta> getMao() {
        return mao;
    }

    public boolean isGritouUno() {
        return gritouUno;
    }

    // ✅ ADICIONADO: setter (resolve erro de acesso private)
    public void setGritouUno(boolean gritouUno) {
        this.gritouUno = gritouUno;
    }

    public void comprarCarta(Carta c) {
        this.mao.add(c);

        // ⚠️ IMPORTANTE:
        // Ao comprar carta, não faz sentido manter UNO ativo
        this.gritouUno = false;
    }

    public void jogarCarta(Carta c) {
        this.mao.remove(c);
    }

    // Método para a regra do UNO
    public void dizerUno() {
        this.gritouUno = true;
    }

    public boolean temCartas() {
        return !this.mao.isEmpty();
    }
}