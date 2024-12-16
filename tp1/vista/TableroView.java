package fiuba.paradigmas.tp1.vista;

import fiuba.paradigmas.tp1.CoordenadasCartesianas2D;
import fiuba.paradigmas.tp1.piezas.*;
import fiuba.paradigmas.tp1.Tablero;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Map;
import java.util.Optional;

public class TableroView {
        private Map<CoordenadasCartesianas2D, StackPane> tableroView;
        private GridPane tableroGrid;
        private Tablero tablero;
        private StackPane[][] posiciones;

        public TableroView(Map<CoordenadasCartesianas2D, StackPane> tableroView, GridPane tableroGrid, Tablero tablero,StackPane[][] posiciones) {
            this.tableroView = tableroView;
            this.tableroGrid = tableroGrid;
            this.tablero = tablero;
            this.posiciones = posiciones;
        }

        public void mostrar() {
            for (StackPane cell : tableroView.values()) {
                cell.getChildren().clear();
            }
            for (Node stackPane : tableroGrid.getChildren()) {
                Integer row = GridPane.getRowIndex(stackPane);
                Integer column = GridPane.getColumnIndex(stackPane);
                int i = row == null ? 0 : row;
                int j = column == null ? 0 : column;

                this.posiciones[i][j] = (StackPane) stackPane;
                Pieza pieza = tablero.getPieza(new CoordenadasCartesianas2D(i, j));
                if (pieza != null) {

                    ImageView imageView = new VerPiezaFabrica().createPiezaImageView(pieza, 50, 50);
                    this.posiciones[i][j].getChildren().add(imageView);
                }

            }
        }
    }

