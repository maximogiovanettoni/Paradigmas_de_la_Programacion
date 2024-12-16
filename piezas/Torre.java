package fiuba.paradigmas.tp1.piezas;

import fiuba.paradigmas.tp1.Color;
import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.Tablero;
import fiuba.paradigmas.tp1.movimientos.MovimientoTorre;

import java.util.ArrayList;
import java.util.Arrays;

public class Torre extends Pieza {
    private boolean enrocable;
    private boolean anteriorEnrocable;
    public Torre(Color color) {
        super(new ArrayList<>((Arrays.asList(new MovimientoTorre()))),color);
        enrocable = true;
    }

    public void piezaMovida(Tablero tablero, CoordenadasCartesianas2D destino) {
        this.anteriorEnrocable = enrocable;
        this.enrocable = false;
    }
    public void piezaDesmovida(Tablero tablero, CoordenadasCartesianas2D origen) {
        this.enrocable = anteriorEnrocable;
    }

    public boolean esEnrocable() {
        return enrocable;
    }

    public boolean esEvolucionable() {
        return true;
    }

    public boolean esCoronable() {
        return false;
    }


}
