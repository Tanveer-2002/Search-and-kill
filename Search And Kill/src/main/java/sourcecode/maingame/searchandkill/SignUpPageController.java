package sourcecode.maingame.searchandkill;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class SignUpPageController {


    @FXML
    private Label signUpStatus;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private TextField confirmPassword;

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void signUp(ActionEvent event) {

        String name = userName.getText();
        String pass = password.getText();
        String confirmpass = confirmPassword.getText();

        if (name.length() >= 5) {
            if (pass.length() >= 5) {
                if (isValidString(name)) {
                    if (isValidString(pass)) {
                        if (pass.equals(confirmpass)) {
                            try {
                                SearchAndKill.player.getOos().writeObject("signup," + name + "," + pass);
                                Object signUpObject = SearchAndKill.player.getOis().readObject();
                                String signUpStr = (String) signUpObject;
                                if (signUpStr.equals("ok")) {

                                    Sound.playSoundEffect(1);

                                    goBackToLogIn(event);

                                }
                                else {

                                    Sound.playSoundEffect(2);

                                    signUpStatus.setTextFill(Color.RED);
                                    signUpStatus.setText("Unavailable user name");

                                }
                            }
                            catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                        }
                        else {

                            Sound.playSoundEffect(2);

                            signUpStatus.setTextFill(Color.RED);
                            signUpStatus.setText("Doesn't match with the password");
                        }
                    }
                    else {

                        Sound.playSoundEffect(2);

                        signUpStatus.setTextFill(Color.RED);
                        signUpStatus.setText("Password only can contain ALPHABETS and NUMBERS");
                    }

                }
                else {

                    Sound.playSoundEffect(2);

                    signUpStatus.setTextFill(Color.RED);
                    signUpStatus.setText("Username only can contain ALPHABETS and NUMBERS");
                }

            }
            else {

                Sound.playSoundEffect(2);

                signUpStatus.setTextFill(Color.RED);
                signUpStatus.setText("Password must be contain 5 or more characters");
            }

        }
        else {

            Sound.playSoundEffect(2);

            signUpStatus.setTextFill(Color.RED);
            signUpStatus.setText("Username must be contain 5 or more characters");

        }
    }



    public void goBackToLogIn(ActionEvent event) throws IOException{

        Sound.playSoundEffect(1);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("logInPage.fxml"));
        root = loader.load();
        scene = new Scene(root);
        loginPageController lpc = loader.getController();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.ENTER){
                    try {
                        lpc.login();
                    }
                    catch (Exception e){}
                }

            }
        });
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public boolean isValidString(String str){

        for(int i =0; i<str.length();i++){
            if(!((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') ||(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') || (str.charAt(i) >= '0' && str.charAt(i) <= '9'))){
                return  false;
            }
        }
        return  true;

    }

}
