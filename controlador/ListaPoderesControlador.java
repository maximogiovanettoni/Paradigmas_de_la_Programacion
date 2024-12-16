package fiuba.paradigmas.tp1.controlador;

import fiuba.paradigmas.tp1.Jugador;
import fiuba.paradigmas.tp1.poderes.Poder;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

import java.util.List;

public class ListaPoderesControlador {
    private List<Poder> listaPoderesModelo;
    private ButtonBar barraDePoderes;
    private Jugador jugadorPropio;
    private Jugador jugadorOponente;

    public ListaPoderesControlador(List<Poder> listaPoderes, ButtonBar barraDePoderes, Jugador jugadorPropio, Jugador jugadorOponente) {
        this.listaPoderesModelo = listaPoderes;
        this.barraDePoderes = barraDePoderes;
        this.jugadorPropio = jugadorPropio;
        this.jugadorOponente = jugadorOponente;
    }

    public void mostrarPoderes(ControladorJuego controladorJuego) {
        barraDePoderes.getButtons().clear();

        for (Poder poder : listaPoderesModelo) {
            //System.out.println(poder.getClass().getSimpleName());
            Button botonsito = new Button();
            //System.out.println(poder.getClass().getSimpleName()=="Vuelo ");
            if(poder.getClass().getSimpleName().equals("Vuelo")){
                botonsito.setText("Vuelo");

                botonsito.setOnMouseClicked(mouseEvent -> {
                    controladorJuego.loggear("Vuelo seleccionado");
                    poder.aplicar(controladorJuego.getJuego(),jugadorPropio,jugadorOponente,null);
                    controladorJuego.loggear("Poder ha sido correctamente aplicado");
                    mostrarPoderes(controladorJuego);
                });
            } else if (poder.getClass().getSimpleName().equals("Escudo")) {
                botonsito.setText("Escudo");
                botonsito.setOnMouseClicked(mouseEvent -> {
                    controladorJuego.loggear("Escudo seleccionado");
                    controladorJuego.setPoderPendiente(poder);
                    mostrarPoderes(controladorJuego);
                });

            } else if (poder.getClass().getSimpleName().equals("RobarPoder")) {
                botonsito.setText("RobarPoder");
                botonsito.setOnMouseClicked(mouseEvent -> {
                    controladorJuego.loggear("RobarPoder seleccionado");
                    poder.aplicar(controladorJuego.getJuego(),jugadorPropio,jugadorOponente,null);
                    mostrarPoderes(controladorJuego);
                });
            } else if (poder.getClass().getSimpleName().equals("Evolucion")) {
                botonsito.setText("Evolucion");
                botonsito.setOnMouseClicked(mouseEvent -> {
                    controladorJuego.loggear("Evolucion seleccionado");
                    controladorJuego.setPoderPendiente(poder);
                    mostrarPoderes(controladorJuego);
                });
            } else if (poder.getClass().getSimpleName().equals("Limpieza")) {
                botonsito.setText("Limpieza");
                botonsito.setOnMouseClicked(mouseEvent -> {
                    controladorJuego.loggear("Limpieza seleccionado");
                    controladorJuego.setPoderPendiente(poder);
                    mostrarPoderes(controladorJuego);
                });
            } else if (poder.getClass().getSimpleName().equals("Freeze")) {
                botonsito.setText("Freeze");
                botonsito.setOnMouseClicked(mouseEvent -> {
                    controladorJuego.loggear("Freeze seleccionado");
                    controladorJuego.setPoderPendiente(poder);
                    mostrarPoderes(controladorJuego);
                });
            } else if (poder.getClass().getSimpleName().equals("DobleJuego")) {
                botonsito.setText("DobleJuego");
                botonsito.setOnMouseClicked(mouseEvent -> {
                    controladorJuego.loggear("DobleJuego seleccionado");
                    poder.aplicar(controladorJuego.getJuego(),jugadorPropio,jugadorOponente,null);
                    controladorJuego.loggear("Poder ha sido correctamente aplicado");
                    mostrarPoderes(controladorJuego);
                });
            } else{
                continue;
            }
            barraDePoderes.getButtons().add(botonsito);
        }
    }



}
