package fiuba.paradigmas.tp1.poderes;

import fiuba.paradigmas.tp1.piezas.Pieza;

public abstract class PoderDeAccion extends Poder {
    public PoderDeAccion(String nombre, String descripcion) {
        super(nombre, descripcion);
    }

    public abstract void aplicar(Pieza pieza);
}