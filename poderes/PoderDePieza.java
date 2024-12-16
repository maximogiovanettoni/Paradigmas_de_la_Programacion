package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.Jugador;
import fiuba.paradigmas.tp1.piezas.Pieza;
import fiuba.paradigmas.tp1.Tablero;

public abstract class PoderDePieza extends Poder {
    public PoderDePieza(String nombre, String descripcion) {
        super(nombre, descripcion);
    }

    public abstract void desaplicar(Pieza piezaActual);
}

