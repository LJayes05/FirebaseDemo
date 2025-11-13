package aydin.firebasedemo;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class RegisterController {
    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField showNameTextField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleRegister() {
        String email = emailTextField.getText();
        String password = passwordField.getText();
        String name = showNameTextField.getText();

        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            statusLabel.setText("Please fill all the fields");
            statusLabel.setStyle("-fx-text-fill: #bd8f8f;");
            return;
        }

        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest();
            request.setEmail(email);
            request.setPassword(password);
            request.setDisplayName(name);
            request.setEmailVerified(false);
            request.setDisabled(false);

            UserRecord user = DemoApp.fauth.createUser(request);
            statusLabel.setText("Account Created" + user.getUid());


            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> {
                try {
                    DemoApp.setRoot("primary");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            delay.play();
        }
        catch (FirebaseAuthException e){
            statusLabel.setText("Invalid Email"+ e.getMessage());
            e.printStackTrace();
        }

    }

    @FXML
    private void handleBack() throws IOException {
        DemoApp.setRoot("primary");
    }

}
