package fiuba.paradigmas.tp1.piezas;

import fiuba.paradigmas.tp1.*;
import fiuba.paradigmas.tp1.movimientos.MovimientoCaballo;

import java.util.ArrayList;
import java.util.Arrays;

public class Caballo extends Pieza {

    public Caballo(Color color) {
        super(new ArrayList<>(Arrays.asList(new MovimientoCaballo())), color);
    }

    public boolean esCoronable() {
        return false;
    }

    public boolean esEvolucionable() {
        return true;
    }

    public boolean esEnrocable() {
        return false;
    }

    public void piezaMovida(Tablero tablero, CoordenadasCartesianas2D destino) {
        return;
    }
    public void piezaDesmovida(Tablero tablero, CoordenadasCartesianas2D origen) {return;}


}
