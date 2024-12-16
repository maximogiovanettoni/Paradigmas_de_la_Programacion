package fiuba.paradigmas.tp1.controlador;

import fiuba.paradigmas.tp1.MainClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RendicionController extends SceneController {
    @FXML
    public Label mensajeRendir;
    @FXML
    public Button buttonAceptar;

    public void setDatos(String jugadorRendido, String ganador) {
        this.mensajeRendir.setText("Como el jugador " + jugadorRendido + " se rindió, el ganador es: " + ganador);
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