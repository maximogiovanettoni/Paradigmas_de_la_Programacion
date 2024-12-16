package fiuba.paradigmas.tp1.controlador;

import fiuba.paradigmas.tp1.*;
import fiuba.paradigmas.tp1.piezas.Pieza;
import fiuba.paradigmas.tp1.Tablero;
import fiuba.paradigmas.tp1.poderes.*;
import fiuba.paradigmas.tp1.vista.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.*;

public class ControladorJuego extends SceneController {
    @FXML
    private GridPane tableroGrid;

    @FXML
    private Label mostradorTurno;

    @FXML
    private ButtonBar barraPoderes;

    @FXML
    private TextArea logDeAcciones;
    private OutputLoggerVista logger;

    private Set<CoordenadasCartesianas2D> ultimasCoordsPosibles;

    private StackPane[][] posiciones = null;

    private Poder poderPendiente;

    private boolean casilleroSeleccionado = false;
    private static ControladorJuego instance;
    private Juego juego;
    private Jugador j1;
    private Jugador j2;
    private Tablero tablero;
    private Map<CoordenadasCartesianas2D, StackPane> tableroView;
    private CoordenadasCartesianas2D origen;
    private CoordenadasCartesianas2D destino;

    private ListaPoderesControlador poderesJ1;
    private ListaPoderesControlador poderesJ2;

    private TableroView mostradorTablero;




    public void initialize(String jugador1, String jugador2){
        this.posiciones = new StackPane[8][8];
        tableroView = new HashMap<>();
        this.setJugadores(jugador1, jugador2);
        for (Node node: tableroGrid.getChildren()){
            node.setOnMouseClicked(mouseEvent -> {
                try {
                    seleccionarCasillero(GridPane.getRowIndex(node),GridPane.getColumnIndex(node), mouseEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            tableroView.put(new CoordenadasCartesianas2D(GridPane.getRowIndex(node),GridPane.getColumnIndex(node)),(StackPane) node);
        }

        tablero = new Tablero();
        juego = new Juego(j1,j2,tablero);
        ultimasCoordsPosibles = new HashSet<>();
        this.logger = new OutputLoggerVista(logDeAcciones);

        asignarPoderesIniciales(j1,j2);
        poderesJ1 = new ListaPoderesControlador(j1.getPoderes(),barraPoderes,j1,j2);
        poderesJ2 = new ListaPoderesControlador(j2.getPoderes(),barraPoderes,j2,j1);

        mostradorTablero = new TableroView(tableroView,tableroGrid,tablero,posiciones);
        mostrarPiezas();
    }


    private void seleccionarCasillero(int i, int j, MouseEvent e) throws IOException {
        for (CoordenadasCartesianas2D mov : ultimasCoordsPosibles) {
            tableroView.get(mov).getChildren().clear();
        }
        mostrarPiezas();

        CoordenadasCartesianas2D seleccionada = new CoordenadasCartesianas2D(i, j);

        if ((poderPendiente != null) && (tablero.getPieza(seleccionada) != null)) {
            aplicarPoderEnPieza(seleccionada);
            poderPendiente = null;
            mostrarPiezas();
            return;
        }
        if (!casilleroSeleccionado) {
            origen = seleccionada;
            Pieza piezaOrigen = tablero.getPieza(origen);

            if (piezaOrigen != null && piezaOrigen.getColor()==juego.getJugadorActual().getColor()) {
                loggear("Pieza seleccionada: " + origen);
                tableroView.get(origen).getChildren().addFirst(new Rectangle(60,60,Paint.valueOf("grey")));
                ultimasCoordsPosibles = juego.getJugadorActual().calcularMovimientos(piezaOrigen,origen,tablero);
                for (CoordenadasCartesianas2D mov : ultimasCoordsPosibles) {
                    tableroView.get(mov).getChildren().add(new Circle(25, Paint.valueOf("green")));
                }
                casilleroSeleccionado = true;
            }
        } else {
            destino = seleccionada;
            if (juego.mover(origen,destino)) {
                if (juego.getJugadorActual().enJaqueMate(tablero)){
                    loggear("Se ha dado un jaque mate");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fiuba/paradigmas/tp1/fin_juego.fxml"));
                    Parent root = loader.load();
                    String ganador = juego.getJugadorActual().getNombre();
                    FinDeJuegoController finDeJuegoController = loader.getController();
                    finDeJuegoController.setDatos(ganador);
                    this.stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                    this.stage.setScene(new Scene(root));
                    this.stage.setTitle("Termino el juego");
                    this.stage.show();
                }
            }
            casilleroSeleccionado = false;
            mostrarPiezas();
        }
    }

    @FXML
    protected void mostrarPiezas() {
        mostradorTurno.setText(juego.getJugadorActual() == j1 ? juego.getJugadorActual().getNombre() + " " + "(BLANCAS)" : juego.getJugadorActual().getNombre() + " " + "(NEGRAS)");
        mostradorTablero.mostrar();
        if (juego.getJugadorActual() == j1) {
            //System.out.println("LLame mostrarPoderes de j1");
            poderesJ1.mostrarPoderes(this);
        } else {
            //System.out.println("LLame mostrarPoderes de j2");
            poderesJ2.mostrarPoderes(this);
        }
    }

    public void handleButtonTablas(ActionEvent e) throws IOException {
        Optional<ButtonType> resultado = this.showAlert(Alert.AlertType.CONFIRMATION, "Tablas", "El rival te ha ofrecido hacer tablas, queres aceptar?", null);

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fiuba/paradigmas/tp1/fin_tablas.fxml"));
            Parent root = loader.load();

            // Obteniene el controlador de la escena de rendición y pasa los datos
            TablasController tablasController = loader.getController();
            String jugadorPedido = juego.getJugadorActual().getNombre();
            String jugadorAceptado = juego.getJugadorActual() == j1 ? j2.getNombre() : j1.getNombre();
            tablasController.setDatos(jugadorPedido, jugadorAceptado);

            // Configura la nueva escena y la miestra
            this.stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            this.stage.setScene(new Scene(root));
            this.stage.setTitle("Tablas");
            this.stage.show();
        }
    }

    public void handleButtonRendirse(ActionEvent e) throws IOException {
        Optional<ButtonType> resultado = this.showAlert(Alert.AlertType.CONFIRMATION,"Rendirse", "Estas seguro de que te queres rendir?",null);

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fiuba/paradigmas/tp1/fin_rendir.fxml"));
            Parent root = loader.load();

            RendicionController rendicionController = loader.getController();
            String jugadorRendido = juego.getJugadorActual().getNombre();
            String ganador = juego.getJugadorActual() == j1 ? j2.getNombre() : j1.getNombre();
            rendicionController.setDatos(jugadorRendido, ganador);

            this.stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            this.stage.setScene(new Scene(root));
            this.stage.setTitle("Rendición");
            this.stage.show();

        }

    }

    public void loggear(String s){
        this.logger.println(s);
    }

    public Juego getJuego() {return juego;}
    public Tablero getTablero() {return tablero;}
    public void setPoderPendiente(Poder poderPendiente){this.poderPendiente = poderPendiente;}


    public void setJugadores(String jugador1Text, String jugador2Text) {
        this.j1 = new Jugador(jugador1Text, Color.BLANCO);
        this.j2 = new Jugador(jugador2Text, Color.NEGRO);
    }

    private void asignarPoderesIniciales(Jugador j1, Jugador j2){
        j1.getPoderes().add(new Escudo(3));
        j1.getPoderes().add(new Evolucion());
        j1.getPoderes().add(new Vuelo());
        j1.getPoderes().add(new Freeze(3));
        j1.getPoderes().add(new Limpieza());
        j1.getPoderes().add(new RobarPoder());
        j1.getPoderes().add(new DobleJuego());

        j2.getPoderes().add(new Escudo(3));
        j2.getPoderes().add(new Evolucion());
        j2.getPoderes().add(new Vuelo());
        j2.getPoderes().add(new Freeze(3));
        j2.getPoderes().add(new Limpieza());
        j2.getPoderes().add(new RobarPoder());
        j2.getPoderes().add(new DobleJuego());
    }

    private void aplicarPoderEnPieza(CoordenadasCartesianas2D seleccionada){
        if (juego.getJugadorActual().aplicarPoder(poderPendiente,juego,juego.getJugadorActual() == j1 ? j2 : j1,seleccionada)){
            loggear("Poder Aplicado correctamente");
            if (juego.getJugadorActual() == j1) {
                poderesJ1.mostrarPoderes(this);
            } else {
                poderesJ2.mostrarPoderes(this);
            }
        }
    }
}