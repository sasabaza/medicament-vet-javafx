module Medicament.Veterinaire.JavaFX {
    requires javafx.controls;
    requires javafx.web;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires org.apache.pdfbox;
    requires jdk.jsobject;
    requires java.net.http;

    exports fr.medicamentvet.entities to com.fasterxml.jackson.databind;
    exports fr.medicamentvet.utils to com.fasterxml.jackson.databind;
    exports fr.medicamentvet.gui;
    exports fr.medicamentvet.gui.menu;
    exports fr.medicamentvet.gui.autocomplete;
    exports fr.medicamentvet.gui.simple;
    exports fr.medicamentvet.gui.tabpane;
    exports fr.medicamentvet.gui.windows;
    exports fr.medicamentvet.gui.windows.searchform;
    exports fr.medicamentvet;

    opens fr.medicamentvet.entities to com.fasterxml.jackson.databind;
    opens fr.medicamentvet.gui.tabpane to javafx.web;
    opens fr.medicamentvet.gui.windows to javafx.web;
}