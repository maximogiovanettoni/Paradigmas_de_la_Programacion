package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.Juego;
import fiuba.paradigmas.tp1.Jugador;
import fiuba.paradigmas.tp1.Tablero;

import java.util.List;

public class RobarPoder extends PoderGlobal {

    public RobarPoder() {
        super("Robar Poder", "Permite robar un poder del jugador oponente.");
    }

    public boolean aplicar(Juego juego, Jugador jugadorAplicador, Jugador jugadorOponente, CoordenadasCartesianas2D coordenadas) {
        if (!jugadorOponente.getPoderes().isEmpty()) {
            Poder poderRobado = elegirPoder(jugadorOponente);
            jugadorOponente.eliminarPoder(poderRobado);
            jugadorAplicador.agregarPoder(poderRobado);
            jugadorAplicador.eliminarPoder(this);
            return true;
        }
        // No se puede robar.
        return false;
    }
    public  Poder elegirPoder(Jugador jugadorOponente) {
        List<Poder> poderesOponente = jugadorOponente.getPoderes();
        return poderesOponente.get((int) (Math.random() * poderesOponente.size()));
    }

}
