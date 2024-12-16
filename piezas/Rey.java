package fiuba.paradigmas.tp1.piezas;

import fiuba.paradigmas.tp1.Color;
import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.Tablero;
import fiuba.paradigmas.tp1.movimientos.MovimientoRey;

import java.util.ArrayList;
import java.util.Arrays;

public class Rey extends Pieza {

    private boolean enrocable;
    private boolean anteriorEnrocable;

    public Rey(Color color) {
        super(new ArrayList<>(Arrays.asList(new MovimientoRey())), color);
        enrocable = true;
    }

    public void piezaMovida(Tablero tablero, CoordenadasCartesianas2D destino) {
        tablero.setPosicionRey(this.getColor(),destino);
        this.anteriorEnrocable = this.enrocable;
        this.enrocable = false;
    }

    public void piezaDesmovida(Tablero tablero, CoordenadasCartesianas2D origen) {
        tablero.setPosicionRey(this.getColor(),origen);
        this.enrocable = this.anteriorEnrocable;
    }

    public boolean esCoronable() {
        return false;
    }

    public boolean esEnrocable() {
        return this.enrocable;
    }

    public boolean esEvolucionable() {
        return false;
    }


}
