package fiuba.paradigmas.tp1.piezas;

import fiuba.paradigmas.tp1.*;
import fiuba.paradigmas.tp1.movimientos.MovimientoAlfil;
import fiuba.paradigmas.tp1.movimientos.MovimientoTorre;

import java.util.ArrayList;
import java.util.Arrays;

public class Reina extends Pieza {

    public Reina(Color color) {
        super(new ArrayList<>(Arrays.asList(new MovimientoAlfil(), new MovimientoTorre())), color);
    }

    public boolean esCoronable() {
        return false;
    }

    public void piezaMovida(Tablero tablero, CoordenadasCartesianas2D destino) {
        return;
    }
    public void piezaDesmovida(Tablero tablero, CoordenadasCartesianas2D origen) {return;}

    public boolean esEvolucionable() {
        return false;
    }

    public boolean esEnrocable() {
        return false;
    }

}
