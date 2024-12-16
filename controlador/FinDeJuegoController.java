package fiuba.paradigmas.tp1.controlador;

import fiuba.paradigmas.tp1.MainClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FinDeJuegoController extends SceneController{
    @FXML
    public Label mensajeFinDeJuego;
    @FXML
    public Button buttonAceptar;

    public void setDatos(String ganador) {
        this.mensajeFinDeJuego.setText("¡El jugador "  + ganador + " ha ganado (nadie juega mejor que vos)!!!!!!!!");
    }

    public void aceptar(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainClass.class.getResource("/fiuba/paradigmas/tp1/loginAjedrez.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        this.stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.show();
        this.stage.setTitle("POWERCHESS!");
    }
}
