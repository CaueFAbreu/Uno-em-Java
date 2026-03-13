package view;

import model.Carta;

public class TradutorBaralho {

    public static String traduzirParaConvencional(Carta carta) {
        String naipe = "";

        switch (carta.getCor()) {
            case VERMELHO -> naipe = "Copas ♥";
            case AMARELO  -> naipe = "Ouros ♦";
            case VERDE    -> naipe = "Paus ♣";
            case AZUL     -> naipe = "Espadas ♠";
            case PRETO    -> naipe = "";
        }

        String figuraOuNumero = "";

        switch (carta.getValor()) {
            case PULAR -> figuraOuNumero = "Valete (J)";
            case INVERTER -> figuraOuNumero = "Dama (Q)";
            case MAIS_DOIS -> figuraOuNumero = "Rei (K)";
            case CORINGA -> { return "Joker Preto (Coringa)"; }
            case CORINGA_MAIS_QUATRO -> { return "Joker Vermelho (Coringa +4)"; }
            case UM -> figuraOuNumero = "Ás (A)";
            default -> figuraOuNumero = carta.getValor().toString();
        }

        if (naipe.isEmpty()) {
            return figuraOuNumero;
        }

        return figuraOuNumero + " de " + naipe +
                " (Equivale a: " + carta.getValor() + " " + carta.getCor() + ")";
    }

    public static String formatarOficial(Carta carta) {
        return "[" + carta.getValor() + " " + carta.getCor() + "]";
    }
}