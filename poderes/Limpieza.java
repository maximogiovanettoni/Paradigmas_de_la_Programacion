package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.*;
import fiuba.paradigmas.tp1.piezas.Pieza;
import fiuba.paradigmas.tp1.Tablero;

public class Limpieza extends PoderGlobal {
    public Limpieza() {
        super("Limpieza", "Permite sacar los poderes a una pieza.");
    }


    public boolean aplicar(Juego juego, Jugador jugadorAplicador, Jugador jugadorOponente, CoordenadasCartesianas2D coordenadas) {
        Pieza pieza = juego.getPieza(coordenadas);
        if (pieza == null) {
            return false;
        }

        for (PoderDePieza poder : pieza.getPoderes()){
            poder.desaplicar(pieza);
        }
        pieza.getPoderes().clear();
        jugadorAplicador.eliminarPoder(this);
        return true;
    }
}
