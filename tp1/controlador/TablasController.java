package fiuba.paradigmas.tp1.controlador;

import fiuba.paradigmas.tp1.MainClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TablasController {
    @FXML
    public javafx.scene.control.Label mensajeTabla;
    @FXML
    public Button buttonAceptar;

    public void setDatos(String jugadorPedido, String jugadorAceptado) {
        this.mensajeTabla.setText("Como el jugador " + jugadorPedido + " pidió tablas y el jugador " + jugadorAceptado + " aceptó, el juego termina en tablas.");
    }

    public void aceptar(ActionEvent e) throws IOException {
        Stage stage = (Stage) buttonAceptar.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainClass.class.getResource("/fiuba/paradigmas/tp1/loginAjedrez.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("POWERCHESS!");
        stage.setScene(scene);
        stage.show();
    }
}