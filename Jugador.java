package fiuba.paradigmas.tp1;

import fiuba.paradigmas.tp1.piezas.Pieza;
import fiuba.paradigmas.tp1.poderes.Poder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Jugador {
    private String nombre;
    private List<Poder> poderes;
    private Color Color;
    private boolean volando = false;



    public Jugador(String nombre, Color color) {
        this.nombre = nombre;
        this.poderes = new ArrayList<>();
        this.Color = color;
    }

    public boolean aplicarPoder(Poder poder,Juego juego, Jugador jugadorOponente,CoordenadasCartesianas2D coordenadas) {
        if (this.poderes.contains(poder)){
            if (poder.aplicar(juego,this,jugadorOponente,coordenadas)){
                this.poderes.remove(poder);
                return true;
            }
        }
       return false;
    }

    public boolean enJaqueMate(Tablero tablero){
        return tablero.enJaqueMate(this,volando);
    }

    public boolean moverPieza(CoordenadasCartesianas2D origen, CoordenadasCartesianas2D destino,Tablero tablero){
        return tablero.mover(origen,destino,this,volando);
    }

    public String getNombre() {
        return this.nombre;
    }

    public Color getColor() {
        return Color;
    }

    public List<Poder> getPoderes() {
        return this.poderes;
    }

    public void agregarPoder(Poder poder) {
        this.poderes.add(poder);
    }

    public void eliminarPoder(Poder poder) {
        this.poderes.remove(poder);
    }

    public void activarVolando(boolean volando) {
        this.volando = volando;
    }

    public Set<CoordenadasCartesianas2D> calcularMovimientos(Pieza piezaOrigen, CoordenadasCartesianas2D origen, Tablero tablero) {
        return tablero.calcularMovimientos(piezaOrigen,origen,volando);
    }
}