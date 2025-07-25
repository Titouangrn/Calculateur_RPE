module RPE_calc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.swt;
    requires javafx.media;

    opens Vue to javafx.fxml;

    // Permission aux autres classes d'accéder aux différents packages
    exports Controleur;
    exports Vue;
    exports Modele;
}
