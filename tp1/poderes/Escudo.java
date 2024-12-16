package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.*;
import fiuba.paradigmas.tp1.piezas.Pieza;
import fiuba.paradigmas.tp1.Tablero;

public class Escudo extends PoderDeDuracion {
    public Escudo(int duracion) {
        super("Escudo", "Protege una pieza de ser capturada por " + duracion + " turnos.", duracion);
    }

    public boolean aplicar(Juego juego, Jugador jugadorAplicador, Jugador jugadorOponente, CoordenadasCartesianas2D coordenadas) {
        Pieza pieza = juego.getPieza(coordenadas);
        if (pieza == null) {
            return false;
        }
        if (juego.getPosicionRey(jugadorAplicador.getColor()) == coordenadas || juego.getPosicionRey(jugadorOponente.getColor())==coordenadas){
            return false;
        }
        if (pieza.getColor() != jugadorAplicador.getColor()) {
            return false;
        }
        pieza.agregarPoder(this);

        pieza.setEscudado(true);
        jugadorAplicador.eliminarPoder(this);
        return true;
    }

    public void desaplicar(Pieza piezaActual){
        piezaActual.setEscudado(false);
    }
}
