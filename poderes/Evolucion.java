package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.*;
import fiuba.paradigmas.tp1.movimientos.Movimiento;
import fiuba.paradigmas.tp1.movimientos.MovimientoAlfil;
import fiuba.paradigmas.tp1.movimientos.MovimientoCaballo;
import fiuba.paradigmas.tp1.movimientos.MovimientoTorre;
import fiuba.paradigmas.tp1.piezas.Pieza;
import fiuba.paradigmas.tp1.Tablero;

import java.util.List;
import java.util.ArrayList;
public class Evolucion extends PoderDePieza {
    private final List<Movimiento> movimientosPosibles;
    public Evolucion() {
        super("Evolucion", "Permite añadir tipos de movimientos a las piezas Torre, Caballo y Alfil.");
        movimientosPosibles = new ArrayList<>();
        movimientosPosibles.add(new MovimientoAlfil());
        movimientosPosibles.add(new MovimientoTorre());
        movimientosPosibles.add(new MovimientoCaballo());
    }


    public boolean aplicar(Juego juego, Jugador jugadorAplicador, Jugador jugadorOponente, CoordenadasCartesianas2D coordenadas) {
        Pieza pieza = juego.getPieza(coordenadas);

        if (pieza == null) {
            return false;
        }
        if (!pieza.esEvolucionable()) {
            return false;
        }
        if (pieza.getColor() != jugadorAplicador.getColor()) {
            return false;
        }
        List<Movimiento> movsPieza = pieza.getMovimientos();
        Movimiento movimientoNuevo = elegirMovimiento();
        if (movsPieza.contains(movimientoNuevo) ) {
            return false;
        }
        pieza.agregarMovimiento(movimientoNuevo);
        jugadorAplicador.eliminarPoder(this);
        return true;
    }

    private Movimiento elegirMovimiento() {
        return movimientosPosibles.get((int) (Math.random() * movimientosPosibles.size()));
    }

    public void desaplicar(Pieza pieza){return;}
}
