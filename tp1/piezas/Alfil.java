package fiuba.paradigmas.tp1.piezas;

import fiuba.paradigmas.tp1.*;
import fiuba.paradigmas.tp1.movimientos.MovimientoAlfil;

import java.util.ArrayList;
import java.util.Arrays;

public class Alfil extends Pieza {

    public Alfil(Color color) {
        super(new ArrayList<>(Arrays.asList(new MovimientoAlfil())), color);
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

    public void piezaMovida(Tablero tablero, CoordenadasCartesianas2D destino) {return;}
    public void piezaDesmovida(Tablero tablero, CoordenadasCartesianas2D origen) {return;}

}
