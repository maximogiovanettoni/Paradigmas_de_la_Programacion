package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.Juego;
import fiuba.paradigmas.tp1.Jugador;
import fiuba.paradigmas.tp1.Tablero;

public class Vuelo extends PoderGlobal {
    public Vuelo() {

        super("Vuelo", "Permite que una pieza pueda pasar por encima de otra en este turno.");
    }



    public boolean aplicar(Juego juego, Jugador jugadorAplicador, Jugador jugadorOponente, CoordenadasCartesianas2D coordenadas) {
        jugadorAplicador.activarVolando(true);
        return true;
    }
}
