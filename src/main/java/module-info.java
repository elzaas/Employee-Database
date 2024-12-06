module org.example.hw9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.hw9 to javafx.fxml;
    exports org.example.hw9;
}
