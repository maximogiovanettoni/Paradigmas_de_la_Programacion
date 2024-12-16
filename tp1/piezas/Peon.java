package fiuba.paradigmas.tp1.piezas;

import fiuba.paradigmas.tp1.*;
import fiuba.paradigmas.tp1.movimientos.MovimientoPeon;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Peon extends Pieza {
    public Peon(Color color) {
        super(new ArrayList<>(Arrays.asList(new MovimientoPeon())), color);
    }

    @Override
    public void piezaMovida(Tablero tablero, CoordenadasCartesianas2D destino) {

        if(this.getColor()==Color.BLANCO && destino.getX()==7){
            return;
        }
        if (this.getColor()==Color.NEGRO && destino.getX()==0){
            coronar(tablero, destino);
        }
    }
    public void piezaDesmovida(Tablero tablero, CoordenadasCartesianas2D origen) {return;}

    public boolean esCoronable() {return true;}

    public boolean esEnrocable() {return false;}

    public boolean esEvolucionable() {return false;}

    public void coronar(Tablero tablero, CoordenadasCartesianas2D coordenadas) {
        List<Pieza> opcionesCoronable = new ArrayList<>();
        opcionesCoronable.add(new Torre(this.getColor()));
        opcionesCoronable.add(new Alfil(this.getColor()));
        opcionesCoronable.add(new Caballo(this.getColor()));
        opcionesCoronable.add(new Reina(this.getColor()));
        Pieza piezaElegida = opcionesCoronable.get((int) (Math.random() * opcionesCoronable.size()));
        tablero.setPieza(coordenadas, piezaElegida);
    }
}
