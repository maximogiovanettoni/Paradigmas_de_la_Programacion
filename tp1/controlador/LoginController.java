package fiuba.paradigmas.tp1.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends SceneController {

    @FXML
    private TextField jugador1;

    @FXML
    private TextField jugador2;



    @FXML
    private void handleLoginButtonAction(ActionEvent e) throws IOException {
        String jugador1Text = jugador1.getText();
        String jugador2Text = jugador2.getText();

        if (jugador1Text.isEmpty() || jugador2Text.isEmpty()) {
            this.showAlert(AlertType.WARNING,"Campos vacíos", null,"Por favor rellene todos los campos");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fiuba/paradigmas/tp1/escena.fxml"));
            Parent root = loader.load();
            ControladorJuego controladorJuego = loader.getController();
            controladorJuego.initialize(jugador1Text, jugador2Text);
            this.stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            this.stage.setScene(new Scene(root));
            this.stage.show();
        }
    }
}