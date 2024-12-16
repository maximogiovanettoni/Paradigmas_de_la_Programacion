package fiuba.paradigmas.tp1.movimientos;

import fiuba.paradigmas.tp1.Color;
import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.piezas.Pieza;

import java.util.*;

public class MovimientoCaballo implements Movimiento {
    public Set<CoordenadasCartesianas2D> calcularMovimientos(Map<CoordenadasCartesianas2D, Pieza> tablero, CoordenadasCartesianas2D origen, Color color, boolean volando) {
        Set<CoordenadasCartesianas2D> movimientosValidos = new HashSet<>();

        // Movimientos en "L"
        List<CoordenadasCartesianas2D> direcciones = new ArrayList<>();
        direcciones.add(new CoordenadasCartesianas2D(2, 1));
        direcciones.add(new CoordenadasCartesianas2D(2, -1));
        direcciones.add(new CoordenadasCartesianas2D(-2, 1));
        direcciones.add(new CoordenadasCartesianas2D(-2, -1));
        direcciones.add(new CoordenadasCartesianas2D(1, 2));
        direcciones.add(new CoordenadasCartesianas2D(1, -2));
        direcciones.add(new CoordenadasCartesianas2D(-1, 2));
        direcciones.add(new CoordenadasCartesianas2D(-1, -2));

        for (CoordenadasCartesianas2D direccion : direcciones) {
            CoordenadasCartesianas2D destino = origen.Sumar(direccion);

            if (tablero.containsKey(destino) && (tablero.get(destino) == null || (tablero.containsKey(destino) && tablero.get(destino).getColor() != color && (!tablero.get(destino).isEscudado())))) {
                movimientosValidos.add(destino);
            }
        }
        return movimientosValidos;


    }
}
