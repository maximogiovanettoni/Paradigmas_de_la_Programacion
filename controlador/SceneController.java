package fiuba.paradigmas.tp1.controlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public abstract class SceneController {
    protected Stage stage;
    protected Scene scene;
    protected Parent root;


    protected void switchScene(ActionEvent e, String scenePath, double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(scenePath));
        this.root = loader.load();
        SceneController newSceneController = loader.getController();
        this.stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, width, height);  // Set dimensions
        stage.setScene(scene);
        stage.show();
    }

    protected Optional<ButtonType> showAlert(AlertType tipoAlerta, String title, String header, String content) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(title);
        alerta.setHeaderText(header);
        alerta.setContentText(content);
        return alerta.showAndWait();
    }
}
