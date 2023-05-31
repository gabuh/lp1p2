module br.ifsp.edu.lp1p {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    opens br.ifsp.edu.lp1p2 to javafx.fxml;
    exports br.ifsp.edu.lp1p2;
    exports br.ifsp.edu.lp1p2.gui.controller;
    opens br.ifsp.edu.lp1p2.gui.controller to javafx.fxml;
}