module com.prog2.uwugroup {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.prog2.uwugroup to javafx.fxml;
    exports com.prog2.uwugroup;
    exports com.prog2.uwugroup.Packet;
    opens com.prog2.uwugroup.Packet to javafx.fxml;
}