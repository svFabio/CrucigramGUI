module com.windowsxp.crucigrama.crucigramagui_xp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.windowsxp.crucigrama.crucigramagui_xp to javafx.fxml;
    exports com.windowsxp.crucigrama.crucigramagui_xp;
}