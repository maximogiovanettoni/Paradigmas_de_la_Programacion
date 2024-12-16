package fiuba.paradigmas.tp1.movimientos;

import fiuba.paradigmas.tp1.Color;
import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.piezas.Pieza;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

public class MovimientoRey implements Movimiento {
    public Set<CoordenadasCartesianas2D> calcularMovimientos(Map<CoordenadasCartesianas2D, Pieza> tablero, CoordenadasCartesianas2D origen, Color color, boolean volando) {
        Set<CoordenadasCartesianas2D> movimientosValidos = new HashSet<>();

        moverEnLinea(new CoordenadasCartesianas2D(1, 0), tablero, origen, color, movimientosValidos,volando);  // derecha
        moverEnLinea(new CoordenadasCartesianas2D(-1, 0), tablero, origen, color, movimientosValidos,volando); // izquierda
        moverEnLinea(new CoordenadasCartesianas2D(0, 1), tablero, origen, color, movimientosValidos,volando);  // arriba
        moverEnLinea(new CoordenadasCartesianas2D(0, -1), tablero, origen, color, movimientosValidos,volando); // abajo

        moverEnDiagonal(new CoordenadasCartesianas2D(1, 1), tablero, origen, color, movimientosValidos,volando);  // diagonal superior derecha
        moverEnDiagonal(new CoordenadasCartesianas2D(-1, -1), tablero, origen, color, movimientosValidos,volando);// diagonal inferior izquierda
        moverEnDiagonal(new CoordenadasCartesianas2D(1, -1), tablero, origen, color, movimientosValidos,volando); // diagonal inferior derecha
        moverEnDiagonal(new CoordenadasCartesianas2D(-1, 1), tablero, origen, color, movimientosValidos,volando); // diagonal superior izquierda


        Pieza rey = tablero.get(origen);

        if (rey.esEnrocable()) {
            verificarEnroque(tablero, color, movimientosValidos);
        }


        return movimientosValidos;
    }



    private void verificarEnroque(Map<CoordenadasCartesianas2D, Pieza> tablero, Color color, Set<CoordenadasCartesianas2D> movimientosValidos) {

        // Posiciones de las torres
        int x;
        if (color == Color.BLANCO) {
            x = 0;
        } else {
            x = 7;
        }

        CoordenadasCartesianas2D torreReyPos = new CoordenadasCartesianas2D(x, 7);
        CoordenadasCartesianas2D torreReinaPos = new CoordenadasCartesianas2D(x, 0);
        Pieza torreRey = tablero.get(torreReyPos);
        Pieza torreReina = tablero.get(torreReinaPos);

        CoordenadasCartesianas2D torrePosDestinoEnroqueCorto = new CoordenadasCartesianas2D(x, 5);
        CoordenadasCartesianas2D reyPosDestinoEnroqueCorto = new CoordenadasCartesianas2D(x, 6);

        CoordenadasCartesianas2D torrePosDestinoEnroqueLargo = new CoordenadasCartesianas2D(x, 3);
        CoordenadasCartesianas2D reyPosDestinoEnroqueLargo = new CoordenadasCartesianas2D(x, 2);
        // Verificar enroque corto (lado rey)

        if ((torreRey != null) && (torreRey.esEnrocable()) && (tablero.get(torrePosDestinoEnroqueCorto) == null && tablero.get(reyPosDestinoEnroqueCorto) == null)) {
            movimientosValidos.add(torreReyPos);
        }

        // Verificar enroque largo (lado reina)
        if ((torreReina != null) && (torreReina.esEnrocable()) && (tablero.get(torrePosDestinoEnroqueLargo) == null && tablero.get(reyPosDestinoEnroqueLargo) == null)) {
            movimientosValidos.add(torreReinaPos);
        }
    }




    private void moverEnLinea(CoordenadasCartesianas2D dif, Map<CoordenadasCartesianas2D, Pieza> tablero, CoordenadasCartesianas2D origen, Color color, Set<CoordenadasCartesianas2D> movimientosValidos,boolean volando) {
        CoordenadasCartesianas2D destino = origen.Sumar(dif);

        if(!tablero.containsKey(destino)) {
            return;
        }

        if (tablero.get(destino) == null) {
            movimientosValidos.add(destino);
        } else if ((tablero.get(destino).getColor() != color) && (!tablero.get(destino).isEscudado())) {
            movimientosValidos.add(destino);
        } else {
            return;
        }


    }

    private void moverEnDiagonal(CoordenadasCartesianas2D dif, Map<CoordenadasCartesianas2D, Pieza> tablero, CoordenadasCartesianas2D origen, Color color, Set<CoordenadasCartesianas2D> movimientosValidos,boolean volando) {
        CoordenadasCartesianas2D destino = origen.Sumar(dif);

        if(!tablero.containsKey(destino)) {
            return;
        }
        if (tablero.get(destino) == null) {
            movimientosValidos.add(destino);
        } else if ((tablero.get(destino).getColor() != color) && (!tablero.get(destino).isEscudado())) {
            movimientosValidos.add(destino);
        } else {
            return;
        }
    }
}

