module team1.testprocessing {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires annotations;
    requires java.logging;

    opens team1.testprocessing to javafx.fxml;
    exports team1.testprocessing;
    opens team1.testprocessing.controllers to javafx.fxml;
    exports team1.testprocessing.controllers;
}