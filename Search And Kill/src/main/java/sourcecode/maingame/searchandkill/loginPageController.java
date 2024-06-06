package sourcecode.maingame.searchandkill;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.*;

public class loginPageController {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginStatus;


    private Parent root;
    private Stage stage;
    private Scene scene;

    public void creatNewAccount(ActionEvent event)throws IOException{

        Sound.playSoundEffect(1);

        root = FXMLLoader.load(getClass().getResource("signUpPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public  void login() throws Exception {
        String name = userName.getText();
        String pass = password.getText();

        if(name.length()>=5 && pass.length()>=5) {

            SearchAndKill.player.getOos().writeObject("login," + name + "," + pass);
            Object loginObject = SearchAndKill.player.getOis().readObject();
            String loginStr = (String) loginObject;
            String[] infos = loginStr.split(",");

            if (infos[0].equals("ok")) {

                Sound.playSoundEffect(1);
                Sound.playMusic(0);

                SearchAndKill.player.setName(infos[1]);
                SearchAndKill.player.setPassword(infos[2]);
                SearchAndKill.player.setKills(Integer.parseInt(infos[3]));
                SearchAndKill.player.setDeaths(Integer.parseInt(infos[4]));
                SearchAndKill.player.setMatchPlayed(Integer.parseInt(infos[5]));
                SearchAndKill.player.setAvatar(infos[6]);
                SearchAndKill.player.setGun(infos[7]);

                String pName = SearchAndKill.player.getName();
                int level = SearchAndKill.player.getKills() / 10;
                double progress = (double) (SearchAndKill.player.getKills() % 10) / 10;
                Image avatarIcon = new Image(getClass().getResourceAsStream(SearchAndKill.player.getAvatar() + "Icon.png"));
                Image avatarDisplay = new Image(getClass().getResourceAsStream(SearchAndKill.player.getAvatar() + ".png"));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyPage.fxml"));
                root = loader.load();
                LobbyPageController lobbyController = loader.getController();
                lobbyController.setInfos(pName, level, progress, avatarIcon, avatarDisplay);

                scene = new Scene(root);
                this.stage = (Stage) loginStatus.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } else if (infos[0].equals("no")) {

                Sound.playSoundEffect(2);

                loginStatus.setTextFill(Color.RED);
                loginStatus.setText("Wrong user name or password");

            } else {

                Sound.playSoundEffect(2);

                loginStatus.setTextFill(Color.RED);
                loginStatus.setText("Already logged in");
            }
        }
        else{

            Sound.playSoundEffect(2);

            loginStatus.setTextFill(Color.RED);
            loginStatus.setText("Wrong user name or password");
        }

    }

    public void exitMethod(ActionEvent event){

        Sound.playSoundEffect(1);

        this.stage = (Stage) loginStatus.getScene().getWindow();
        stage.close();
    }
    
}