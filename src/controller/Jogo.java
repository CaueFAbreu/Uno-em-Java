package controller;

import model.Baralho;
import model.Carta;
import model.Cor;
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

    public void iniciarPartida(){
        baralho.embaralhar();
        for (Jogador j : this.jogadores){
            for(int i=0; i<7; i++){
                // Agora usamos o método do Jogador
                j.comprarCarta(baralho.comprarCarta());
            }
        }
        this.pilhaDescarte.add(baralho.comprarCarta());
    }

    public void avancarTurno(){
        int totalJogadores = this.jogadores.size();
        if(this.sentidoHorario){
            this.jogadorAtual = (jogadorAtual + 1)% totalJogadores;
        } else {
            this.jogadorAtual = (this.jogadorAtual - 1 + totalJogadores) % totalJogadores;
        }
    }

    public Jogador getJogadorDaVez(){
        return jogadores.get(jogadorAtual);
    }

    public boolean validarJogada(Carta c) {
        Carta topoDaPilha = getCartaTopo();

        return (topoDaPilha.getCor() == c.getCor() ||
                topoDaPilha.getValor() == c.getValor() ||
                c.getValor() == Valor.CORINGA ||
                c.getValor() == Valor.CORINGA_MAIS_QUATRO);
    }

    public boolean realizarJogada(Carta c){
        if(validarJogada(c)){
            // O Jogador agora é responsável por remover sua própria carta
            getJogadorDaVez().jogarCarta(c);
            pilhaDescarte.add(c);
            c.aplicarEfeito(this);
            avancarTurno();
            return true;
        } else return false;
    }

    public void inverterSentido(){
        sentidoHorario = sentidoHorario ^ true;
    }

    public void comprarCartas(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            verificarEReciclarBaralho();
            // Pedimos ao jogador para comprar
            getJogadorDaVez().comprarCarta(baralho.comprarCarta());
        }
    }

    public void jogadorCompraCarta(){
        verificarEReciclarBaralho();
        getJogadorDaVez().comprarCarta(baralho.comprarCarta());
        avancarTurno();
    }

    private void verificarEReciclarBaralho(){
        if (!baralho.temCartas()){
            Carta topo = this.pilhaDescarte.getLast();
            this.pilhaDescarte.removeLast();
            baralho.reabastecer(this.pilhaDescarte);
            this.pilhaDescarte.clear();
            this.pilhaDescarte.add(topo);
            System.out.println("Baralho atualizado! Pilha de cartas reciclada.");
        }
    }

    public Jogador verificaVencedor(){
        for (Jogador j : this.jogadores){

            if (!j.temCartas()) return j;
        }
        return null;
    }
}