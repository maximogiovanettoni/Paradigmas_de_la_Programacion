package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.*;
import fiuba.paradigmas.tp1.piezas.Pieza;
import fiuba.paradigmas.tp1.Tablero;

public class Freeze extends PoderDeDuracion {
    public Freeze(int duracion) {
        super("Freeze", "Permite inmovilizar una pieza rival por " + duracion + "turnos.", duracion);
    }

    public boolean aplicar(Juego juego, Jugador jugadorAplicador, Jugador jugadorOponente, CoordenadasCartesianas2D coordenadas) {
        Pieza pieza = juego.getPieza(coordenadas);
        if (pieza == null) {
            return false;
        }
        if (juego.getPosicionRey(jugadorAplicador.getColor()) == coordenadas || juego.getPosicionRey(jugadorOponente.getColor())==coordenadas){
            return false;
        }
        if (pieza.getColor() == jugadorAplicador.getColor()) {
            return false;
        }
        pieza.setFreezado(true);
        pieza.agregarPoder(this);
        jugadorAplicador.eliminarPoder(this);
        return true;
    }

    public void desaplicar(Pieza piezaActual){
        piezaActual.setFreezado(false);
    }
}
