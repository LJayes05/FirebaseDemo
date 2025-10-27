package aydin.firebasedemo;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class WelcomeController {

    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    @FXML
    void handleRegister(ActionEvent event) {
        String email = emailTextField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Status: Please enter email and password.");
            return;
        }

        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password);

            UserRecord userRecord = DemoApp.fauth.createUser(request);
            statusLabel.setText("Status: User registered with UID: " + userRecord.getUid());

        } catch (FirebaseAuthException ex) {
            statusLabel.setText("Status: Registration failed! " + ex.getMessage());
        }
    }

    @FXML
    void handleSignIn(ActionEvent event) {
        String email = emailTextField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please enter email and password.");
            return;
        }
        try {
            UserRecord userRecord = DemoApp.fauth.getUserByEmail(email);

            statusLabel.setText("Sign In Successful! Proceeding to data access.");
            // Switch to the data access screen
            DemoApp.setRoot("primary");

        } catch (FirebaseAuthException ex) {
            statusLabel.setText("Sign In failed! User not found or incorrect credentials.");
        } catch (IOException ex) {
            statusLabel.setText("Failed to switch scene.");
        }
    }

    @FXML
    void switchToSecondary(ActionEvent event) {
        try{
            DemoApp.setRoot("secondary");

        }
        catch (IOException ex){
            statusLabel.setText("Failed to switch scene.");
        }

    }
}