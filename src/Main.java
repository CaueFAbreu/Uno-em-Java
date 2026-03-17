import controller.Jogo;
import model.*;
import view.ConsoleUNO;
import view.TradutorBaralho;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleUNO console = new ConsoleUNO();
        Scanner scannerAuxiliar = new Scanner(System.in);

        boolean isConvencional = console.escolherModoDeJogo();
        Baralho baralho = isConvencional ? new BaralhoUnoConvencional() : new BaralhoUnoOficial();
        List<Jogador> jogadores = console.cadastrarJogadores();

        Jogo mesa = new Jogo(jogadores, baralho);
        mesa.iniciarPartida();

        Jogador vencedor = null;

        while (vencedor == null) {
            Jogador jogadorAtual = mesa.getJogadorDaVez();
            Carta cartaTopo = mesa.getCartaTopo();

            System.out.println("\n-------------------------------------------------");
            String textoTopo = isConvencional
                    ? TradutorBaralho.traduzirParaConvencional(cartaTopo)
                    : TradutorBaralho.formatarOficial(cartaTopo);
            System.out.println("CARTA NA MESA: " + textoTopo);

            console.mostrarMaoDoJogador(jogadorAtual);

            boolean jogadaValida = false;

            while (!jogadaValida) {
                String escolha = console.lerJogada();

                if (escolha.equals("U")) {
                    jogadorAtual.dizerUno();
                    System.out.println("📢 " + jogadorAtual.getNome() + " gritou: UNO!!!");

                } else if (escolha.equals("C")) {

                    Carta cartaComprada = mesa.jogadorCompraCarta();
                    String textoCartaComprada = isConvencional
                            ? TradutorBaralho.traduzirParaConvencional(cartaComprada)
                            : TradutorBaralho.formatarOficial(cartaComprada);
                    System.out.println(jogadorAtual.getNome() + " comprou: " + textoCartaComprada);

                    if (mesa.validarJogada(cartaComprada)) {
                        System.out.print("Essa carta é válida! Deseja jogá-la agora? (S/N): ");
                        String resposta = scannerAuxiliar.nextLine().trim().toUpperCase();

                        if (resposta.equals("S")) {

                            if (cartaComprada.getCor() == Cor.PRETO) {
                                System.out.println("Escolha a cor (1-Vermelho, 2-Amarelo, 3-Verde, 4-Azul):");
                                int opCor = scannerAuxiliar.nextInt();
                                scannerAuxiliar.nextLine();
                                switch (opCor) {
                                    case 1 -> cartaComprada.setCor(Cor.VERMELHO);
                                    case 2 -> cartaComprada.setCor(Cor.AMARELO);
                                    case 3 -> cartaComprada.setCor(Cor.VERDE);
                                    case 4 -> cartaComprada.setCor(Cor.AZUL);
                                }
                            }
                            int tamanhoAntes = jogadorAtual.getMao().size();
                            mesa.realizarJogada(cartaComprada);

                            if (tamanhoAntes == 2 && !jogadorAtual.isGritouUno()) {
                                System.out.println("\n⚠️ VOCÊ ESQUECEU DE GRITAR UNO! Penalidade: +2 cartas.");
                                mesa.comprarCartas(jogadorAtual, 2);
                            }
                            jogadorAtual.setGritouUno(false);
                            System.out.println("Carta jogada com sucesso!");
                        } else {

                            mesa.avancarTurno();
                            System.out.println(jogadorAtual.getNome() + " optou por não jogar e passou a vez.");
                        }
                    } else {

                        mesa.avancarTurno();
                        System.out.println("Carta não é válida para jogar agora. Vez passada.");
                    }
                    jogadaValida = true;

                } else {
                    try {
                        int indice = Integer.parseInt(escolha);

                        if (indice >= 0 && indice < jogadorAtual.getMao().size()) {
                            int tamanhoAntes = jogadorAtual.getMao().size();
                            Carta cartaEscolhida = jogadorAtual.getMao().get(indice);

                            if (cartaEscolhida.getCor() == Cor.PRETO) {
                                System.out.println("Escolha a cor (1-Vermelho, 2-Amarelo, 3-Verde, 4-Azul):");
                                int opCor = scannerAuxiliar.nextInt();
                                scannerAuxiliar.nextLine();
                                switch (opCor) {
                                    case 1 -> cartaEscolhida.setCor(Cor.VERMELHO);
                                    case 2 -> cartaEscolhida.setCor(Cor.AMARELO);
                                    case 3 -> cartaEscolhida.setCor(Cor.VERDE);
                                    case 4 -> cartaEscolhida.setCor(Cor.AZUL);
                                }
                            }

                            boolean sucesso = mesa.realizarJogada(cartaEscolhida);

                            if (sucesso) {
                                if (tamanhoAntes == 2 && !jogadorAtual.isGritouUno()) {
                                    System.out.println("\n⚠️ VOCÊ ESQUECEU DE GRITAR UNO! Penalidade: +2 cartas.");
                                    mesa.comprarCartas(jogadorAtual, 2);
                                }
                                jogadorAtual.setGritouUno(false);
                                System.out.println("Jogada realizada com sucesso!");
                                jogadaValida = true;
                            } else {
                                System.out.println("Carta inválida para o topo atual!");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Entrada inválida! Digite o número, 'C' para comprar ou 'U' para gritar UNO.");
                    }
                }
            }
            vencedor = mesa.verificaVencedor();
        }

        System.out.println("\n🏆 VITÓRIA 🏆");
        System.out.println("Parabéns, " + vencedor.getNome() + "! Você venceu a partida!");
    }
}
