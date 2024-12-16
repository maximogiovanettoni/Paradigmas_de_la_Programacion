package fiuba.paradigmas.tp1;

import fiuba.paradigmas.tp1.piezas.*;

import java.util.*;

public class Tablero {
    private Map<CoordenadasCartesianas2D, Pieza> casilleros;
    static private final Integer dimension = (Integer) 8;

    private Map<Color, CoordenadasCartesianas2D> posicionesReyes;

    public Tablero() {

        this.casilleros = new HashMap<>();
        this.posicionesReyes = new HashMap<>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.casilleros.put(new CoordenadasCartesianas2D(i,j),null);
            }
        }

    }


    public Map<CoordenadasCartesianas2D, Pieza> getCasilleros() {
        return casilleros;
    }

    public Pieza getPieza(CoordenadasCartesianas2D cartesiana) {
        return this.casilleros.get(cartesiana);
    }
    public void setPieza(CoordenadasCartesianas2D cartesiana, Pieza p) {
        this.casilleros.put(cartesiana, p);
    }

    public CoordenadasCartesianas2D getPosicionRey(Color color) {
        return this.posicionesReyes.get(color);
    }
    public void setPosicionRey(Color color, CoordenadasCartesianas2D posicion) {
        this.posicionesReyes.put(color, posicion);
    }

    public void colocarPiezasIniciales() {
        // Metodo para crear tablero con condiciones inciales.

        // Colocar piezas blancas.
        casilleros.put(new CoordenadasCartesianas2D(0, 0), new Torre(Color.BLANCO));
        casilleros.put(new CoordenadasCartesianas2D(0, 1), new Caballo(Color.BLANCO));
        casilleros.put(new CoordenadasCartesianas2D(0, 2), new Alfil(Color.BLANCO));
        casilleros.put(new CoordenadasCartesianas2D(0, 3), new Reina(Color.BLANCO));
        casilleros.put(new CoordenadasCartesianas2D(0, 4), new Rey(Color.BLANCO));
        posicionesReyes.put(Color.BLANCO, new CoordenadasCartesianas2D(0, 4));
        casilleros.put(new CoordenadasCartesianas2D(0, 5), new Alfil(Color.BLANCO));
        casilleros.put(new CoordenadasCartesianas2D(0, 6), new Caballo(Color.BLANCO));
        casilleros.put(new CoordenadasCartesianas2D(0, 7), new Torre(Color.BLANCO));
        // Colocar peones blancos.
        for (int i = 0; i < 8; i++) {
            casilleros.put(new CoordenadasCartesianas2D(1, i), new Peon(Color.BLANCO));
        }

        // Colocar piezas negras.
        casilleros.put(new CoordenadasCartesianas2D(7, 0), new Torre(Color.NEGRO));
        casilleros.put(new CoordenadasCartesianas2D(7, 1), new Caballo(Color.NEGRO));
        casilleros.put(new CoordenadasCartesianas2D(7, 2), new Alfil(Color.NEGRO));
        casilleros.put(new CoordenadasCartesianas2D(7, 3), new Reina(Color.NEGRO));
        casilleros.put(new CoordenadasCartesianas2D(7, 4), new Rey(Color.NEGRO));
        posicionesReyes.put(Color.NEGRO, new CoordenadasCartesianas2D(7, 4));
        casilleros.put(new CoordenadasCartesianas2D(7, 5), new Alfil(Color.NEGRO));
        casilleros.put(new CoordenadasCartesianas2D(7, 6), new Caballo(Color.NEGRO));
        casilleros.put(new CoordenadasCartesianas2D(7, 7), new Torre(Color.NEGRO));
        // Colocar peones negros.
        for (int i = 0; i < 8; i++) {
            casilleros.put(new CoordenadasCartesianas2D(6, i), new Peon(Color.NEGRO));
        }
    }

    // A partir de una pieza y sus coordenadas iniciales, genera una lista de las coordenadas a las que podria moverse.
//    public Set<CoordenadasCartesianas2D> calcularMovimientos(Pieza pieza, CoordenadasCartesianas2D coordenadas) {
//        return pieza.calcularMovimientos(casilleros, coordenadas,volando);
//    }

    public Set<CoordenadasCartesianas2D> calcularMovimientos(Pieza pieza, CoordenadasCartesianas2D coordenadas, boolean volando) {
        return pieza.calcularMovimientos(casilleros, coordenadas, volando);
    }

    public boolean estaEnJaque (Jugador jugador) {
        Color colorJugador = jugador.getColor();
        CoordenadasCartesianas2D posRey = this.getPosicionRey(colorJugador);
        for (Map.Entry<CoordenadasCartesianas2D,Pieza> pieza : this.casilleros.entrySet()){
            if (pieza.getValue() == null || pieza.getValue().getColor()==jugador.getColor()){
                continue;
            }
            Set<CoordenadasCartesianas2D> movsValidos = calcularMovimientos(pieza.getValue(),pieza.getKey(),false);
            if (movsValidos.contains(posRey)) {
                return true;
            }
        }
        return false;

    }


    public boolean mover(CoordenadasCartesianas2D origen, CoordenadasCartesianas2D destino, Jugador jugador,boolean volando){

        Pieza piezaOrigen = casilleros.get(origen);

        if ((piezaOrigen == null) || (piezaOrigen.getColor() != jugador.getColor())) {
            return false;
        }

        Set<CoordenadasCartesianas2D> movimientosValidos = calcularMovimientos(piezaOrigen, origen,volando);

        if (movimientosValidos.contains(destino)) {
            if ((this.getPieza(destino) != null && this.getPieza(destino).esEnrocable()) && (this.getPieza(origen) != null && this.getPieza(origen).esEnrocable()) && (this.getPieza(destino).getColor() == this.getPieza(origen).getColor())) {
                this.realizarEnroque(origen, destino);
                return true;
            }

            Pieza piezaEliminada = casilleros.get(destino);
            casilleros.put(destino, piezaOrigen);
            casilleros.put(origen, null);

            casilleros.get(destino).piezaMovida(this,destino);

            if (estaEnJaque(jugador)){
                casilleros.put(origen,casilleros.get(destino));
                casilleros.put(destino, piezaEliminada);
                casilleros.get(origen).piezaDesmovida(this,origen);
                return false;
            }
        } else{
            return false;
        }

        casilleros.get(destino).piezaMovida(this,destino);
        return true;
    }

    public void realizarEnroque(CoordenadasCartesianas2D origen, CoordenadasCartesianas2D destino) {
        Pieza rey = casilleros.get(origen);
        Pieza torre = casilleros.get(destino);

        // Verificar que la pieza de origen sea el rey y la pieza de destino sea la torre
        if (rey == null || !rey.esEnrocable() || torre == null || !torre.esEnrocable()) {
            return; // Salir si el enroque no es válido
        }

        // Verificar de qué lado es el enroque (corto o largo)
        boolean esEnroqueCorto = destino.getY() == 7;
        boolean esEnroqueLargo = destino.getY() == 0;

        // Definir las nuevas posiciones del rey y la torre
        CoordenadasCartesianas2D nuevaPosRey;
        CoordenadasCartesianas2D nuevaPosTorre;

        if (rey.getColor() == Color.BLANCO) {
            if (esEnroqueCorto) {
                nuevaPosRey = new CoordenadasCartesianas2D(0, 6);  // Enroque corto blanco
                nuevaPosTorre = new CoordenadasCartesianas2D(0, 5);
            } else if (esEnroqueLargo) {
                nuevaPosRey = new CoordenadasCartesianas2D(0, 2);  // Enroque largo blanco
                nuevaPosTorre = new CoordenadasCartesianas2D(0, 3);
            } else {
                return;  // No es un enroque válido
            }
        } else if (rey.getColor() == Color.NEGRO) {
            if (esEnroqueCorto) {
                nuevaPosRey = new CoordenadasCartesianas2D(7, 6);  // Enroque corto negro
                nuevaPosTorre = new CoordenadasCartesianas2D(7, 5);
            } else if (esEnroqueLargo) {
                nuevaPosRey = new CoordenadasCartesianas2D(7, 2);  // Enroque largo negro
                nuevaPosTorre = new CoordenadasCartesianas2D(7, 3);
            } else {
                return;  // No es un enroque válido
            }
        } else {
            return;  // Color inválido
        }
        // Mover el rey a su nueva posición
        casilleros.put(nuevaPosRey, rey);
        casilleros.put(origen, null);  // Eliminar rey de su posición original

        posicionesReyes.put(rey.getColor(),nuevaPosRey);

        // Mover la torre a su nueva posición
        casilleros.put(nuevaPosTorre, torre);
        casilleros.put(destino, null);  // Eliminar torre de su posición original

        // Marcar las piezas como movidas
        rey.piezaMovida(this, nuevaPosRey);
        torre.piezaMovida(this, nuevaPosTorre);

        // Actualizar la posición del rey en el registro de posiciones de reyes
        posicionesReyes.put(rey.getColor(), nuevaPosRey);

    }

    public boolean enJaqueMate(Jugador jugador,boolean volando){
        boolean sigueEnJaque = true;
        for (Map.Entry<CoordenadasCartesianas2D,Pieza> pieza : this.casilleros.entrySet()){
            if (pieza.getValue() == null || pieza.getValue().getColor()!=jugador.getColor()){
                continue;
            }
            Set<CoordenadasCartesianas2D> movimientosValidos = calcularMovimientos(pieza.getValue(), pieza.getKey(),volando);
            for (CoordenadasCartesianas2D destino : movimientosValidos){
                Pieza piezaEliminada = casilleros.get(destino);
                casilleros.put(destino, pieza.getValue());
                casilleros.put(pieza.getKey(), null);
                casilleros.get(destino).piezaMovida(this, destino);
                sigueEnJaque = estaEnJaque(jugador);
                casilleros.put(pieza.getKey(), casilleros.get(destino));
                casilleros.put(destino, piezaEliminada);
                casilleros.get(pieza.getKey()).piezaDesmovida(this,pieza.getKey());
                if (!sigueEnJaque) {
                    return false;
                }

            }

        }
        return true;
    }

    public void disminuirPoderesDuracionPiezas(){
        for (Map.Entry<CoordenadasCartesianas2D,Pieza> pieza : this.casilleros.entrySet()) {
            if (pieza.getValue() == null) {
                continue;
            }
            pieza.getValue().actualizarPoderesPorTurno();
        }
    }

}
