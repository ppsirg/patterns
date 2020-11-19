package puj.patterns.mundo.Pantalla;

import java.awt.*;

public class ExtendedScreen implements AdjustScreenSize {

    public int UpdateSizeVariables() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }
}
