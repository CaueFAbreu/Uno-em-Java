package view;

import model.Carta;
import model.Jogador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUNO {

    private Scanner scanner;
    private boolean modoConvencional;

    public ConsoleUNO() {
        this.scanner = new Scanner(System.in);
    }

    public boolean escolherModoDeJogo() {
        int escolha = 0;
        while (escolha != 1 && escolha != 2) {
            System.out.println("=======================================");
            System.out.println("          BEM-VINDO AO JOGO UNO        ");
            System.out.println("=======================================");
            System.out.println("Escolha o baralho para esta partida:");
            System.out.println("1 - Baralho UNO Oficial");
            System.out.println("2 - Baralho Convencional (52 Cartas)");
            System.out.print("Sua escolha: ");

            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                escolha = 0;
            }

            if (escolha != 1 && escolha != 2) {
                System.out.println("Opção inválida! Tente novamente.\n");
            }
        }
        modoConvencional = (escolha == 2);
        return modoConvencional;
    }

    public List<Jogador> cadastrarJogadores() {
        List<Jogador> jogadores = new ArrayList<>();
        int qtd = 0;

        while (qtd < 2 || qtd > 10) {
            System.out.print("\nQuantos jogadores vão participar? (Mín 2, Máx 10): ");

            if (scanner.hasNextInt()) {
                qtd = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                qtd = 0;
            }

            if (qtd < 2 || qtd > 10) {
                System.out.println("Quantidade inválida! Tente novamente.");
            }
        }

        for (int i = 1; i <= qtd; i++) {
            System.out.print("Digite o nome do Jogador " + i + ": ");
            String nome = scanner.nextLine();

            if (nome.trim().isEmpty()) {
                nome = "Jogador" + i;
            }
            jogadores.add(new Jogador(nome));
        }
        return jogadores;
    }

    public void mostrarMaoDoJogador(Jogador jogador) {
        System.out.println("\n=======================================");
        System.out.println("VEZ DE: " + jogador.getNome().toUpperCase());
        System.out.println("Sua mão de cartas:");

        List<Carta> mao = jogador.getMao();
        for (int i = 0; i < mao.size(); i++) {
            Carta c = mao.get(i);
            String textoCarta;

            if (modoConvencional) {
                textoCarta = TradutorBaralho.traduzirParaConvencional(c);
            } else {
                textoCarta = TradutorBaralho.formatarOficial(c);
            }
            System.out.println("  [" + i + "] -> " + textoCarta);
        }

        System.out.println("  [C] -> COMPRAR CARTA");
        System.out.println("=======================================");
    }

    public String lerJogada() {
        System.out.print("Digite o número da carta que deseja jogar ou 'C' para comprar: ");
        return scanner.nextLine().trim().toUpperCase();
    }
}