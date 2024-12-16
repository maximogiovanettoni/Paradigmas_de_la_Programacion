package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.*;
import fiuba.paradigmas.tp1.piezas.Pieza;
import fiuba.paradigmas.tp1.Tablero;

public abstract class PoderDeDuracion extends PoderDePieza {
    protected int duracion;

    public PoderDeDuracion(String nombre, String descripcion, int duracion) {
        super(nombre, descripcion);
        this.duracion = duracion;
    }

    public boolean disminuirDuracion() {
        duracion -= 1;
        if (duracion == 0) {
            return true;
        }
        return false;
    }

    public abstract void desaplicar(Pieza piezaActual);

}
