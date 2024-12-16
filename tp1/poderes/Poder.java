package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.Juego;
import fiuba.paradigmas.tp1.Jugador;
import fiuba.paradigmas.tp1.Tablero;

public abstract class Poder {
    protected String nombre;
    protected String descripcion;

    public Poder(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public abstract boolean aplicar(Juego juego, Jugador jugadorAplicador, Jugador jugadorOponente, CoordenadasCartesianas2D coordenadas);
}
