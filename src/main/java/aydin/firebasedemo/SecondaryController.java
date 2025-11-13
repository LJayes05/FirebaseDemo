package aydin.firebasedemo;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {

    @FXML
    private Button registerButton;
    @FXML
    private Button signInButton;

    @FXML
    private void signInForm() throws IOException {
        DemoApp.setRoot("signIn");
    }

    @FXML
    private void registerForm() throws IOException {
        DemoApp.setRoot("register");
    }
}
