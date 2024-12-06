module com.gherex.institucion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires itextpdf;


    opens com.gherex.institucion to javafx.fxml;
    exports com.gherex.institucion;
}