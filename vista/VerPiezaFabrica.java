package fiuba.paradigmas.tp1.vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import fiuba.paradigmas.tp1.piezas.*;
import fiuba.paradigmas.tp1.Color;

import java.util.Map;

import static fiuba.paradigmas.tp1.Color.BLANCO;
import static fiuba.paradigmas.tp1.Color.NEGRO;


public class VerPiezaFabrica {
    final static Map<Class, String> piezasNegras = Map.ofEntries(
            Map.entry(Caballo.class, "/fiuba/paradigmas/tp1/caballo_negro.png"),
            Map.entry(Alfil.class, "/fiuba/paradigmas/tp1/alfil_negro.png"),
            Map.entry(Torre.class, "/fiuba/paradigmas/tp1/torre_negro.png"),
            Map.entry(Rey.class, "/fiuba/paradigmas/tp1/rey_negro.png"),
            Map.entry(Reina.class, "/fiuba/paradigmas/tp1/reina_negro.png"),
            Map.entry(Peon.class, "/fiuba/paradigmas/tp1/peon_negro.png")

    );

    final static Map<Class, String> piezasBlancas = Map.ofEntries(
            Map.entry(Caballo.class, "/fiuba/paradigmas/tp1/caballo_blanco.png"),
            Map.entry(Alfil.class, "/fiuba/paradigmas/tp1/alfil_blanco.png"),
            Map.entry(Torre.class, "/fiuba/paradigmas/tp1/torre_blanco.png"),
            Map.entry(Rey.class, "/fiuba/paradigmas/tp1/rey_blanco.png"),
            Map.entry(Reina.class, "/fiuba/paradigmas/tp1/reina_blanco.png"),
            Map.entry(Peon.class, "/fiuba/paradigmas/tp1/peon_blanco.png")
    );

    final static Map<Color, Map<Class, String>> piezas = Map.ofEntries(
            Map.entry(BLANCO, piezasBlancas),
            Map.entry(NEGRO, piezasNegras)
    );

    public ImageView createPiezaImageView(Pieza pieza, double width, double height) {
        String path = piezas.get(pieza.getColor()).get(pieza.getClass());
        Image piezaImage = new Image(getClass().getResource(path).toString());
        ImageView piezaImageView = new ImageView();
        piezaImageView.setImage(piezaImage);
        piezaImageView.setFitWidth(width);
        piezaImageView.setFitHeight(height);

        piezaImageView.setPreserveRatio(true);

        return piezaImageView;
    }
}