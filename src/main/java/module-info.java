module com.prog.uwugroup {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.prog2.uwugroup to javafx.fxml;
    exports com.prog2.uwugroup;
}