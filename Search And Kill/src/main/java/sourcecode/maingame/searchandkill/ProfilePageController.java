package sourcecode.maingame.searchandkill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class ProfilePageController {

    @FXML
    private Label playerName;
    @FXML
    private Label playerLevel;
    @FXML
    private Label totalKIll;
    @FXML
    private Label totalDeath;
    @FXML
    private Label matchPlayed;
    @FXML
    private Label pp;
    @FXML
    private Label avatarNameLabel;
    @FXML
    private ImageView avatar;
    @FXML
    private Label goBackButtonLabel;
    private Parent root;
    private Scene scene;
    private Stage stage;


    public  void setPlayerInfos(){

        Double playerPerformance = (double)SearchAndKill.player.getKills()-SearchAndKill.player.getDeaths();
        if(SearchAndKill.player.getMatchPlayed()>0){
            playerPerformance /= SearchAndKill.player.getMatchPlayed();
        }

        playerName.setText(SearchAndKill.player.getName());
        playerLevel.setText(Integer.toString(SearchAndKill.player.getKills()/10));
        totalKIll.setText(Integer.toString(SearchAndKill.player.getKills()));
        totalDeath.setText(Integer.toString(SearchAndKill.player.getDeaths()));
        matchPlayed.setText(Integer.toString(SearchAndKill.player.getMatchPlayed()));

        DecimalFormat df = new DecimalFormat("#.##");
        String ppStr = df.format(playerPerformance);

        pp.setText(ppStr);
        avatarNameLabel.setText(SearchAndKill.player.getAvatar());
        avatar.setImage(new Image(getClass().getResourceAsStream(SearchAndKill.player.getAvatar()+".png")));

    }
    public  void setOtherPlayerInfos(String selectedName)throws Exception{

        SearchAndKill.player.getOos().writeObject("getOthersInfo,"+SearchAndKill.player.getName()+","+SearchAndKill.player.getPassword()+","+selectedName);
        Object gotObj = SearchAndKill.player.getOis().readObject();
        String gotStr = (String)gotObj;
        System.out.println(gotStr);
        String []datas = gotStr.split(",");

        Double playerPerformance = (double)(Integer.parseInt(datas[2])-Integer.parseInt(datas[3]));
        if(Integer.parseInt(datas[4])>0){
            playerPerformance /= Integer.parseInt(datas[4]);
        }

        playerName.setText(datas[0]);
        playerLevel.setText(Integer.toString(Integer.parseInt(datas[2])/10));
        totalKIll.setText(datas[2]);
        totalDeath.setText(datas[3]);
        matchPlayed.setText(datas[4]);

        DecimalFormat df = new DecimalFormat("#.##");
        String ppStr = df.format(playerPerformance);

        pp.setText(ppStr);
        avatarNameLabel.setText(datas[5]);
        avatar.setImage(new Image(getClass().getResourceAsStream(datas[5]+".png")));

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

    public void inGoBackButton(){
        goBackButtonLabel.setVisible(true);
    }
    public void  outGoBackButton(){
        goBackButtonLabel.setVisible(false);
    }
}
