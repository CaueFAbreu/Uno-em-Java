package controller;

import model.Baralho;
import model.Carta;
import model.CartaCuringa;
import model.Jogador;
import model.Valor;

import java.util.ArrayList;
import java.util.List;

public class Jogo {

    private List<Jogador> jogadores;
    private Baralho baralho;
    private List<Carta> pilhaDescarte;
    private int jogadorAtual;
    private boolean sentidoHorario;

    public Jogo(List<Jogador> jogadores, Baralho baralho) {
        this.jogadores = jogadores;
        this.baralho = baralho;
        this.pilhaDescarte = new ArrayList<>();
        this.jogadorAtual = 0;
        this.sentidoHorario = true;
    }

    public Carta getCartaTopo() {
        return this.pilhaDescarte.get(this.pilhaDescarte.size() - 1);
    }

    public int getTotalJogadores() {
        return this.jogadores.size();
    }

    public void iniciarPartida() {
        baralho.embaralhar();

        for (Jogador j : this.jogadores) {
            for (int i = 0; i < 7; i++) {
                j.comprarCarta(baralho.comprarCarta());
            }
        }

        Carta primeiraCartaMesa;
        do {
            primeiraCartaMesa = baralho.comprarCarta();
        } while (primeiraCartaMesa instanceof CartaCuringa);

        this.pilhaDescarte.add(primeiraCartaMesa);

        System.out.println("\n=== A PARTIDA VAI COMEÇAR! ===");
        if (primeiraCartaMesa.getValor() == Valor.PULAR ||
            primeiraCartaMesa.getValor() == Valor.INVERTER ||
            primeiraCartaMesa.getValor() == Valor.MAIS_DOIS) {
            System.out.println("⚠️  A primeira carta é uma carta de ação! Efeito aplicado ao primeiro jogador.");
            primeiraCartaMesa.aplicarEfeito(this);
        }
    }

    public void avancarTurno() {
        int totalJogadores = this.jogadores.size();

        if (this.sentidoHorario) {
            this.jogadorAtual = (jogadorAtual + 1) % totalJogadores;
        } else {
            this.jogadorAtual = (this.jogadorAtual - 1 + totalJogadores) % totalJogadores;
        }
    }

    public Jogador getJogadorDaVez() {
        return jogadores.get(jogadorAtual);
    }

    public boolean validarJogada(Carta c) {
        Carta topoDaPilha = getCartaTopo();

        return (topoDaPilha.getCor() == c.getCor() ||
                topoDaPilha.getValor() == c.getValor() ||
                c.getValor() == Valor.CORINGA ||
                c.getValor() == Valor.CORINGA_MAIS_QUATRO);
    }

    public boolean realizarJogada(Carta c) {
        if (validarJogada(c)) {
            getJogadorDaVez().jogarCarta(c);
            pilhaDescarte.add(c);
            c.aplicarEfeito(this);
            avancarTurno();
            return true;
        }
        return false;
    }

    public void inverterSentido() {
        sentidoHorario = !sentidoHorario;
    }

    public void comprarCartas(Jogador jogador, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            verificarEReciclarBaralho();
            jogador.comprarCarta(baralho.comprarCarta());
        }
    }

    public void comprarCartas(int quantidade) {
        comprarCartas(getJogadorDaVez(), quantidade);
    }

    public Carta jogadorCompraCarta() {
        verificarEReciclarBaralho();
        Carta cartaComprada = baralho.comprarCarta();
        getJogadorDaVez().comprarCarta(cartaComprada);
        return cartaComprada;
    }

    private void verificarEReciclarBaralho() {
        if (!baralho.temCartas()) {
            Carta topo = this.pilhaDescarte.get(this.pilhaDescarte.size() - 1);
            this.pilhaDescarte.remove(this.pilhaDescarte.size() - 1);

            baralho.reabastecer(this.pilhaDescarte);

            this.pilhaDescarte.clear();
            this.pilhaDescarte.add(topo);

            System.out.println("Baralho atualizado! Pilha de cartas reciclada.");
        }
    }

    public Jogador verificaVencedor() {
        for (Jogador j : this.jogadores) {
            if (!j.temCartas()) return j;
        }
        return null;
    }
}
