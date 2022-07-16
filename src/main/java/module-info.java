module com.prog2.uwugroup {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.prog2.uwugroup to javafx.fxml;
    exports com.prog2.uwugroup;
    exports com.prog2.uwugroup.Paket;
    opens com.prog2.uwugroup.Paket to javafx.fxml;
}