package fiuba.paradigmas.tp1.piezas;
import fiuba.paradigmas.tp1.Color;
import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.Tablero;
import fiuba.paradigmas.tp1.movimientos.Movimiento;
import fiuba.paradigmas.tp1.poderes.PoderDeDuracion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;


public abstract class Pieza {
    private List<Movimiento> movimientos;

    private Color color;

    private List<PoderDeDuracion> poderes;

    private boolean freezado;
    private boolean escudado;

    public Pieza(List<Movimiento> movimientos, Color color) {
        this.movimientos = movimientos;
        this.color = color;
        this.poderes = new ArrayList<>();
        this.freezado = false;
        this.escudado = false;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void agregarMovimiento(Movimiento movimiento) {
        this.movimientos.add(movimiento);
    }

    public Color getColor() {
        return color;
    }

    public Set<CoordenadasCartesianas2D> calcularMovimientos(Map<CoordenadasCartesianas2D, Pieza > tablero, CoordenadasCartesianas2D coordenadas, boolean volando) {
        Set<CoordenadasCartesianas2D> movimientosValidos = new HashSet<>();

        if (this.freezado){
            return movimientosValidos;
        }

        for (Movimiento movimiento : movimientos) {
            movimientosValidos.addAll(movimiento.calcularMovimientos(tablero, coordenadas, color,volando));
        }
        return movimientosValidos;
    }

    public void actualizarPoderesPorTurno(){
        List<PoderDeDuracion> poderesAEliminar = new ArrayList<>();
        for (PoderDeDuracion poder : poderes) {
            if (poder.disminuirDuracion()){
                poder.desaplicar(this);
                poderesAEliminar.add(poder);
            }
        }
        for (PoderDeDuracion eliminado : poderesAEliminar) {
            eliminarPoder(eliminado);
        }
    }

    public List<PoderDeDuracion> getPoderes() {
        return poderes;
    }
    public void agregarPoder(PoderDeDuracion poder) {
        poderes.add(poder);
    }
    public void eliminarPoder(PoderDeDuracion poder) {
        poderes.remove(poder);
    }


    public abstract void piezaMovida(Tablero tablero, CoordenadasCartesianas2D destino); // Funcion para actualizar si una pieza ya fue movida o no. (Para validar el enroque)

    public abstract void piezaDesmovida(Tablero tablero, CoordenadasCartesianas2D origen);

    public abstract boolean esEvolucionable();

    public abstract boolean esEnrocable();

    public void setFreezado(boolean freezado) {this.freezado = freezado;}

    public void setEscudado(boolean escudado) {this.escudado = escudado;}

    public boolean isEscudado() {return this.escudado;}

}
