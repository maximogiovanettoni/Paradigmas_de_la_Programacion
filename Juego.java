package fiuba.paradigmas.tp1;

import fiuba.paradigmas.tp1.piezas.Pieza;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Juego {

    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private boolean terminado;
    private int dobleJuego = 0;
    private CoordenadasCartesianas2D ultimoMovido;

    public Juego(Jugador jugador1, Jugador jugador2, Tablero tablero) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = jugador1; // Empiezan las blancas.
        this.tablero = tablero;
        this.terminado = false;
        inicializarJuego();
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void inicializarJuego() {
        tablero.colocarPiezasIniciales();
    }

    public void cambiarTurno() {
        jugadorActual.activarVolando(false);
        this.tablero.disminuirPoderesDuracionPiezas();
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
        if (jugadorActual.enJaqueMate(tablero)){
            this.terminado = true;
        }
    }

    public Map<CoordenadasCartesianas2D, Pieza> getTablero() {
        return this.tablero.getCasilleros();
    }

    public boolean mover(CoordenadasCartesianas2D origen, CoordenadasCartesianas2D destino) {
        if ( dobleJuego >0) {
            dobleJuego --;
            if (dobleJuego==0 && !ultimoMovido.equals(origen)) {
                dobleJuego=1;
                return false;
            }
        }

        if (this.jugadorActual.moverPieza(origen, destino, tablero)){
            this.ultimoMovido = destino;
            if (dobleJuego<=0){
                cambiarTurno();
            }
        }
        return true;
    }


    public Pieza getPieza(CoordenadasCartesianas2D coordenadas) {
        return this.tablero.getPieza(coordenadas);
    }

    public void activarDobleJuego(int dobleJuego) {
        this.dobleJuego = dobleJuego;
    }

    public CoordenadasCartesianas2D getPosicionRey(Color color) {
        return tablero.getPosicionRey(color);
    }
}

