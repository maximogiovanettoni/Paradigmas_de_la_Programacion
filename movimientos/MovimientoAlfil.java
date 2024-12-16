package fiuba.paradigmas.tp1.movimientos;

import fiuba.paradigmas.tp1.Color;
import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.piezas.Pieza;

import java.util.*;

public class MovimientoAlfil implements Movimiento {
    public Set<CoordenadasCartesianas2D> calcularMovimientos(Map<CoordenadasCartesianas2D, Pieza> tablero, CoordenadasCartesianas2D origen, Color color, boolean volando) {
        Set<CoordenadasCartesianas2D> movimientosValidos = new HashSet<>();

        moverEnDiagonal(new CoordenadasCartesianas2D(1, 1), tablero, origen, color, movimientosValidos,volando);  // diagonal superior derecha
        moverEnDiagonal(new CoordenadasCartesianas2D(-1, -1), tablero, origen, color, movimientosValidos,volando);// diagonal inferior izquierda
        moverEnDiagonal(new CoordenadasCartesianas2D(1, -1), tablero, origen, color, movimientosValidos,volando); // diagonal inferior derecha
        moverEnDiagonal(new CoordenadasCartesianas2D(-1, 1), tablero, origen, color, movimientosValidos,volando); // diagonal superior izquierda

        return movimientosValidos;
    }

    private void moverEnDiagonal(CoordenadasCartesianas2D dif, Map<CoordenadasCartesianas2D, Pieza> tablero, CoordenadasCartesianas2D origen, Color color, Set<CoordenadasCartesianas2D> movimientosValidos,boolean volando) {
        CoordenadasCartesianas2D destino = origen.Sumar(dif);
        while (tablero.containsKey(destino)) {
            if (tablero.get(destino) == null) {
                movimientosValidos.add(destino);
            } else if ((tablero.get(destino).getColor() != color) && (!tablero.get(destino).isEscudado())) {
                movimientosValidos.add(destino);
                if (!volando){
                    break;
                }
            } else {
                if (!volando){
                    break;
                }
            }
            destino = destino.Sumar(dif);
        }
    }
}

