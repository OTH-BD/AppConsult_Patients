module net.othmane.hd.appg_consult_patients {
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.controls;

    opens net.othmane.hd.appg_consult_patients to javafx.fxml;
    exports net.othmane.hd.appg_consult_patients;
    exports net.othmane.hd.appg_consult_patients.models;
    exports net.othmane.hd.appg_consult_patients.controllers to javafx.fxml;
    opens net.othmane.hd.appg_consult_patients.controllers to javafx.fxml;
}