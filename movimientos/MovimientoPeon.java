package fiuba.paradigmas.tp1.movimientos;

import fiuba.paradigmas.tp1.Color;
import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.piezas.Pieza;

import java.util.*;

public class MovimientoPeon implements Movimiento {

    public Set<CoordenadasCartesianas2D> calcularMovimientos(Map<CoordenadasCartesianas2D, Pieza> tablero, CoordenadasCartesianas2D origen, Color color, boolean volando) {
        Set<CoordenadasCartesianas2D> movimientosValidos = new HashSet<>();

        // Dirección de movimiento, depende del color (blanco hacia arriba, negro hacia abajo)
        int direccion = (color == Color.BLANCO) ? 1 : -1;

        // Movimiento hacia adelante (una casilla)
        CoordenadasCartesianas2D adelante = origen.Sumar(new CoordenadasCartesianas2D(direccion,0));
        if (tablero.containsKey(adelante) && tablero.get(adelante) == null) {

            movimientosValidos.add(adelante);

            // Primer movimiento: dos casillas hacia adelante
            if ((color == Color.BLANCO && origen.getX() == 1) || (color == Color.NEGRO && origen.getX() == 6)) {
                CoordenadasCartesianas2D dobleAdelante = new CoordenadasCartesianas2D(origen.getX()+ 2 * direccion, origen.getY());
                if (tablero.containsKey(dobleAdelante) && tablero.get(dobleAdelante) == null) {
                    movimientosValidos.add(dobleAdelante);
                }
            }
        }
        // Captura en diagonal
        CoordenadasCartesianas2D diagonalIzquierda = new CoordenadasCartesianas2D(origen.getX() + direccion, origen.getY() -1);
        CoordenadasCartesianas2D diagonalDerecha = new CoordenadasCartesianas2D(origen.getX() + direccion, origen.getY() + 1);

        if (tablero.containsKey(diagonalIzquierda) && tablero.get(diagonalIzquierda) != null && tablero.get(diagonalIzquierda).getColor() != color && (!tablero.get(diagonalIzquierda).isEscudado())){
            movimientosValidos.add(diagonalIzquierda);
        }

        if (tablero.containsKey(diagonalDerecha) && tablero.get(diagonalDerecha) != null && tablero.get(diagonalDerecha).getColor() != color && (!tablero.get(diagonalDerecha).isEscudado())){
            movimientosValidos.add(diagonalDerecha);
        }
        return movimientosValidos;
    }
}