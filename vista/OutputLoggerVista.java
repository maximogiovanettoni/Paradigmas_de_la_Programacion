package fiuba.paradigmas.tp1.vista;

import javafx.scene.control.TextArea;

public class OutputLoggerVista{
    private TextArea logger;

    public OutputLoggerVista(TextArea logger) {
        this.logger = logger;
    }

    public void println(String s) {
        logger.appendText("\n"+s);
    }
}