package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.Juego;
import fiuba.paradigmas.tp1.Jugador;
import fiuba.paradigmas.tp1.Tablero;

public class DobleJuego extends PoderGlobal {

    public DobleJuego() {
        super("Doble Juego", "Permite al usuario realizar dos movimientos en el mismo turno");
    }

    public boolean aplicar(Juego juego, Jugador jugadorAplicador, Jugador jugadorOponente, CoordenadasCartesianas2D coordenadas) {
        juego.activarDobleJuego(2);
        jugadorAplicador.eliminarPoder(this);
        return true;
    }
}

