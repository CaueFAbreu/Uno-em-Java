package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Baralho {
    protected ArrayList<Carta> cartas;

    public Baralho() {
        this.cartas = new ArrayList<>();
        montarBaralho();
    }

    public abstract void montarBaralho();

    public void embaralhar(){
        Collections.shuffle(this.cartas);
    }

    public Carta comprarCarta(){
        Carta c = cartas.getFirst();
        cartas.removeFirst();
        return c;
    }

    public boolean temCartas(){
        return !cartas.isEmpty();
    }

    public void reabastecer(List<Carta> descartadas){
        this.cartas.addAll(descartadas);
        embaralhar();
    }

}
