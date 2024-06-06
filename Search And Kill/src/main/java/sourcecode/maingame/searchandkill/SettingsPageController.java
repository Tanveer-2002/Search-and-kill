package sourcecode.maingame.searchandkill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public  class SettingsPageController {
    @FXML
    private Label playerName;
    @FXML
    private TextField newName;
    @FXML
    private PasswordField newPassword;
    @FXML
    private TextField confirmNewPassword;
    @FXML
    private PasswordField currentPassword;
    @FXML
    private Label goBackButtonLabel;
    @FXML
    private Label statusLabel;

    private Parent root;
    private Scene scene;
    private Stage stage;

    public void goBack(ActionEvent event) throws IOException {

        Sound.playSoundEffect(1);
        Sound.playMusic(0);

        String pName = SearchAndKill.player.getName();
        int level = SearchAndKill.player.getKills()/10;
        double progress = (double) (SearchAndKill.player.getKills() % 10) /10;
        Image avatarIcon = new Image(getClass().getResourceAsStream(SearchAndKill.player.getAvatar()+"Icon.png"));
        Image avatarDisplay = new Image(getClass().getResourceAsStream(SearchAndKill.player.getAvatar()+".png"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyPage.fxml"));
        root = loader.load();
        LobbyPageController lobbyController = loader.getController();
        lobbyController.setInfos(pName,level, progress,avatarIcon,avatarDisplay);

        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void apply(ActionEvent event) throws  Exception{

        String nName = newName.getText();
        String nPass = newPassword.getText();
        String cNPass = confirmNewPassword.getText();
        String cPass = currentPassword.getText();

        if(cPass.equals(SearchAndKill.player.getPassword())){
            statusLabel.setText("");
            if(nName.length() >= 5){
                if(isValidString(nName)){
                    if(nPass.length() >= 5){
                        if(isValidString(nPass)){
                            Boolean allOk = true;
                            if(!nPass.equals(SearchAndKill.player.getPassword())){

                               if(!nPass.equals(cNPass)) {

                                   Sound.playSoundEffect(2);

                                   allOk = false;
                                   statusLabel.setTextFill(Color.RED);
                                   statusLabel.setText("Doesn't match with the given new password");
                                }

                            }
                            if(allOk){

                                SearchAndKill.player.getOos().writeObject("update,"+SearchAndKill.player.getName()+","+SearchAndKill.player.getPassword()+","+nName+","+nPass);

                                Object getObject =  SearchAndKill.player.getOis().readObject();
                                String getstr = (String) getObject;
                                String[] infos = getstr.split(",");
                                if(infos[0].equals("ok")){

                                    Sound.playSoundEffect(1);

                                    statusLabel.setTextFill(Color.GREEN);
                                    statusLabel.setText("Change applied");
                                    SearchAndKill.player.setName(infos[1]);
                                    SearchAndKill.player.setPassword(infos[2]);
                                    playerName.setText(SearchAndKill.player.getName());
                                    newName.setText(SearchAndKill.player.getName());
                                    newPassword.setText(SearchAndKill.player.getPassword());
                                    SearchAndKill.player.getOos().writeObject("D,"+SearchAndKill.player.getName());
                                }
                                else {

                                    Sound.playSoundEffect(2);

                                    statusLabel.setTextFill(Color.RED);
                                    statusLabel.setText("Unavailable user name");
                                }
                            }

                        }
                        else {

                            Sound.playSoundEffect(2);

                            statusLabel.setTextFill(Color.RED);
                            statusLabel.setText("Password only can contain ALPHABETS and NUMBERS");
                        }

                    }
                    else {

                        Sound.playSoundEffect(2);

                        statusLabel.setTextFill(Color.RED);
                        statusLabel.setText("Password must be contain 5 or more characters");
                    }

                }
                else {

                    Sound.playSoundEffect(2);

                    statusLabel.setTextFill(Color.RED);
                    statusLabel.setText("Username only can contain ALPHABETS and NUMBERS");
                }
            }
            else{

                Sound.playSoundEffect(2);

                statusLabel.setTextFill(Color.RED);
                statusLabel.setText("Username must be contain 5 or more characters");
            }
        }
        else{

            Sound.playSoundEffect(2);

            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Wrong current password");
        }
    }
    public  void setInfos(){
        playerName.setText(SearchAndKill.player.getName());
        newName.setText(SearchAndKill.player.getName());
        newPassword.setText(SearchAndKill.player.getPassword());

    }

    public void inGoBackButton(){
        goBackButtonLabel.setVisible(true);
    }
    public void  outGoBackButton(){
        goBackButtonLabel.setVisible(false);
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
