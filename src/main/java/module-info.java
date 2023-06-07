module br.ifsp.edu.lp1p2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
            
                            
    opens br.ifsp.edu.lp1p2 to javafx.fxml;
    exports br.ifsp.edu.lp1p2;
    exports br.ifsp.edu.lp1p2.controller;
    opens br.ifsp.edu.lp1p2.controller to javafx.fxml;
}