module br.ifsp.edu.lp1p2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens br.ifsp.edu.lp1p2 to javafx.fxml, org.hibernate.orm.core;
    exports br.ifsp.edu.lp1p2;
    exports br.ifsp.edu.lp1p2.controller;
    exports br.ifsp.edu.lp1p2.model;
    exports br.ifsp.edu.lp1p2.controller.item;
    exports br.ifsp.edu.lp1p2.controller.pedido;
    opens br.ifsp.edu.lp1p2.model to javafx.fxml,org.hibernate.orm.core;
    opens br.ifsp.edu.lp1p2.controller to javafx.fxml, org.hibernate.orm.core;
    opens br.ifsp.edu.lp1p2.controller.item to javafx.fxml, org.hibernate.orm.core;
    opens br.ifsp.edu.lp1p2.controller.pedido to javafx.fxml, org.hibernate.orm.core;

}
