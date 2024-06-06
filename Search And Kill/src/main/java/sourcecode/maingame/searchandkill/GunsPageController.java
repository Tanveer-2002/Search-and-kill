package sourcecode.maingame.searchandkill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class GunsPageController {

    @FXML
    private Rectangle rect2;
    @FXML
    private Rectangle rect3;
    @FXML
    private Label goBackButtonLabel;

    @FXML
    private Label rd;
    @FXML
    private Label rr;
    @FXML
    private Label md;
    @FXML
    private Label mr;
    private Parent root;
    private Scene scene;
    private Stage stage;

    public void selectRiffel()throws Exception{

        Sound.playSoundEffect(1);

        rect2.setStrokeWidth(5);
        rect2.setStroke(Color.web("#48BCD5"));
        rect3.setStrokeWidth(2);
        rect3.setStroke(Color.web("#ffffff") );

        updateInServer("riffel");
    }
    public void selectMachinGun() throws Exception{

        Sound.playSoundEffect(1);

        rect3.setStrokeWidth(5);
        rect3.setStroke(Color.web("#48BCD5"));
        rect2.setStrokeWidth(2);
        rect2.setStroke(Color.web("#ffffff"));

        updateInServer("MachinGun");

    }

    public void updateInServer(String gunName)throws Exception{
        SearchAndKill.player.getOos().writeObject("editgun,"+SearchAndKill.player.getName()+","+SearchAndKill.player.getPassword()+","+gunName);
        Object gotObject = SearchAndKill.player.getOis().readObject();
        String gotStr = (String) gotObject;
        if(gotStr.equals("ok")){
            SearchAndKill.player.setGun(gunName);
        }
        else{
            System.out.println("Something is wrong");
        }
    }


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

    public void showOrHideRiffleInfo(){
        if(!rr.isVisible()) {
            rr.setVisible(true);
            rd.setVisible(true);
        }
        else{
            rr.setVisible(false);
            rd.setVisible(false);
        }

    }
    public void showOrHideMGunInfo(){
        if(!mr.isVisible()) {
            mr.setVisible(true);
            md.setVisible(true);
        }
        else{
            mr.setVisible(false);
            md.setVisible(false);
        }

    }
    public void inGoBackButton(){
        goBackButtonLabel.setVisible(true);
    }
    public void  outGoBackButton(){
        goBackButtonLabel.setVisible(false);
    }
}
