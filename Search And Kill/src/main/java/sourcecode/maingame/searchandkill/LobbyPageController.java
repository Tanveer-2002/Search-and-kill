package sourcecode.maingame.searchandkill;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LobbyPageController implements Initializable {


    @FXML
    private Button playButton;
    @FXML
    private ImageView playButtonIcon;
    @FXML
    private Label playButtonLabel;
    @FXML
    private Button globalChatButton;
    @FXML
    private ImageView globalChatButtonIcon;
    @FXML
    private Label globalChatButtonLabel;
    @FXML
    private Button customMatchButton;
    @FXML
    private ImageView customMatchButtonIcon;
    @FXML
    private Label customMatchButtonLabel;
    @FXML
    private Label settingsButtonLabel;
    @FXML
    private Button profileButton;
    @FXML
    private ImageView profileButtonIcon;
    @FXML
    private Label profileButtonLabel;
    @FXML
    private Button tutorialButton;
    @FXML
    private ImageView tutorialButtonIcon;
    @FXML
    private Label tutorialButtonLabel;
    @FXML
    private Button weaponsButton;
    @FXML
    private ImageView weaponsButtonIcon;
    @FXML
    private Label weaponsButtonLabel;
    @FXML
    private Button avatarsButton;
    @FXML
    private ImageView avatarsButtonIcon;
    @FXML
    private Label avatarsButtonLabel;
    @FXML
    private Label name;
    @FXML
    private  Label level;
    @FXML
    private ProgressBar levelBar;
    @FXML
    private ImageView avatarDisplay;
    @FXML
    private ImageView avatarIcon;
    @FXML
    private  ImageView machineGun;
    @FXML
    private Label onlinePlayesLabel;
    @FXML
    private ListView<String>onlineplayesListView;
    @FXML
    private Button onlinePlayersButton;
    @FXML
    private  Button seeProfile;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void logout(ActionEvent event) throws Exception {

        Sound.stopMusic();
        Sound.playSoundEffect(1);

        SearchAndKill.player.getOos().writeObject("logout,"+SearchAndKill.player.getName()+","+SearchAndKill.player.getPassword());
        Object logoutObject = SearchAndKill.player.getOis().readObject();
        String logoutStr = (String)logoutObject;
        if(logoutStr.equals("ok")){
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
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        levelBar.setStyle("-fx-accent: #C2F1A1;");
    }

    public void setInfos(String playerName, int playerLevel, double  levelProgress, Image avatarIcon,Image avatarDisplay){
        name.setText(playerName);
        level.setText("Level: "+playerLevel);
        levelBar.setProgress(levelProgress);
        this.avatarIcon.setImage(avatarIcon);
        this.avatarDisplay.setImage(avatarDisplay);
        if(SearchAndKill.player.getGun().equals("MachinGun")){
            machineGun.setVisible(true);
        }
        else{
            machineGun.setVisible(false);
        }


    }

    public void globalChat(ActionEvent event)throws IOException {

        Sound.stopMusic();
        Sound.playSoundEffect(1);

        SearchAndKill.showChat = true;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("globalChatPage.fxml"));
        root = loader.load();
        scene = new Scene(root);
        GlobalChatPageController gblcC = loader.getController();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.ENTER){
                    try {
                        gblcC.sendMessage();
                    }
                    catch (Exception e){}
                }

            }
        });
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


        new ChatThread(gblcC);

    }

    public void profile(ActionEvent event)throws  IOException{

        Sound.stopMusic();
        Sound.playSoundEffect(1);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("playerProfilePage.fxml"));
        root = loader.load();
        ProfilePageController profilePageController = loader.getController();
        try{
        profilePageController.setOtherPlayerInfos(SearchAndKill.player.getName());
        }
        catch(Exception e){}
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void play(ActionEvent event) throws Exception{

        Sound.stopMusic();
        Sound.playSoundEffect(1);
        Sound.playMusic(3);

        SearchAndKill.player.getOos().writeObject("joinRndMatch,"+SearchAndKill.player.getName()+","+SearchAndKill.player.getPassword());
        Object gotObj = SearchAndKill.player.getOis().readObject();
        String gotStr = (String)gotObj;
        System.out.println(gotStr);

        String[] infos = gotStr.split(",");

        SearchAndKill.matchIndex = Integer.parseInt(infos[0]);
        SearchAndKill.playerIndex = Integer.parseInt(infos[1]);
        SearchAndKill.mapIndex = Integer.parseInt(infos[2]);
        SearchAndKill.player.setStartX((int)Double.parseDouble(infos[3]));
        SearchAndKill.player.setStartY((int)Double.parseDouble(infos[4]));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("waitingRoomPage.fxml"));
        root = loader.load();
        WaitingRoomController wc = loader.getController();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        SearchAndKill.updateWRoom = true;
        new WaitingRoomInfoUpdateThread(wc,stage);
    }

    public void tutorial(ActionEvent event ) throws IOException{

        Sound.stopMusic();
        Sound.playSoundEffect(1);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("tutorial.fxml"));
        root = loader.load();
        TutorialPageController tController = loader.getController();
        tController.setInstructions("Press and hold 'w' key for move up");
        scene = new Scene(root);
        
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double x = mouseEvent.getSceneX();
                double y = mouseEvent.getSceneY();
                tController.playerAim(x,y);

            }
        });

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tController.fire();
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public  void handle(KeyEvent pressedKey){
                if(pressedKey.getCode() == KeyCode.W){
                    if(!SearchAndKill.moving){
                        Sound.playMusic(4);
                    }
                    SearchAndKill.moving = true;
                    tController.moveUp();
                }
                if(pressedKey.getCode() == KeyCode.S){
                    if(!SearchAndKill.moving){
                        Sound.playMusic(4);
                    }
                    SearchAndKill.moving = true;
                    tController.moveDown();
                }
                if(pressedKey.getCode() == KeyCode.D){
                    if(!SearchAndKill.moving){
                        Sound.playMusic(4);
                    }
                    SearchAndKill.moving = true;
                    tController.moveRight();
                }
                if(pressedKey.getCode() == KeyCode.A){
                    if(!SearchAndKill.moving){
                        Sound.playMusic(4);
                    }
                    SearchAndKill.moving = true;
                    tController.moveLeft();
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent releasedKey) {
                if(releasedKey.getCode() == KeyCode.W) {
                    Sound.stopMusic();
                    SearchAndKill.moving = false;
                }
                if(releasedKey.getCode() == KeyCode.S) {
                    Sound.stopMusic();
                    SearchAndKill.moving = false;
                }
                if(releasedKey.getCode() == KeyCode.D) {
                    Sound.stopMusic();
                    SearchAndKill.moving = false;
                }
                if(releasedKey.getCode() == KeyCode.A) {
                    Sound.stopMusic();
                    SearchAndKill.moving = false;
                }
            }
        });
        



        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    public void settings(ActionEvent event)throws  IOException{

        Sound.stopMusic();
        Sound.playSoundEffect(1);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("settingsPage.fxml"));
        root = loader.load();
        SettingsPageController settingsPageController = loader.getController();
        settingsPageController.setInfos();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void avatars(ActionEvent event)throws  Exception{

        Sound.stopMusic();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("avatarsPage.fxml"));
        root = loader.load();
        scene = new Scene(root);
        AvatarsPageController avatarsPageController = loader.getController();
        switch(SearchAndKill.player.getAvatar()){
            case "Mogambo":{
                avatarsPageController.selectMogambo();
            }
            break;
            case "Captain":{
                avatarsPageController.selectCaptain();
            }
            break;
            case"Rambo":{
                avatarsPageController.selectRambo();
            }
            break;
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void guns(ActionEvent event)throws  Exception{

        Sound.stopMusic();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gunsPage.fxml"));
        root = loader.load();
        scene = new Scene(root);
        GunsPageController gunsPageController = loader.getController();
        switch (SearchAndKill.player.getGun()){

            case"riffel":{
                gunsPageController.selectRiffel();
            }
            break;
            case "MachinGun":{
                gunsPageController.selectMachinGun();
            }
            break;
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void showOrHideOnlinePlayerList(){
        if(!onlineplayesListView.isVisible()){
            onlinePlayersButton.setRotate(180);
            onlineplayesListView.setVisible(true);
            onlinePlayesLabel.setVisible(true);
            seeProfile.setVisible(true);

            onlineplayesListView.setLayoutX(0);
            onlinePlayesLabel.setLayoutX(13);
            onlinePlayersButton.setLayoutX(150);
            seeProfile.setLayoutX(0);


            try {
                SearchAndKill.player.getOos().writeObject("getallplayersName," + SearchAndKill.player.getName() + "," + SearchAndKill.player.getPassword());
                Object gotObj = SearchAndKill.player.getOis().readObject();
                String gotStr = (String) gotObj;

                String []names = gotStr.split(",");
                for(int i = 0 ; i<names.length; i++){
                    onlineplayesListView.getItems().add(names[i]);
                }
            }
            catch (Exception e){}

        }
        else{

            onlinePlayersButton.setRotate(0);
            onlineplayesListView.setLayoutX(-150);
            onlinePlayesLabel.setLayoutX(-150);
            onlinePlayersButton.setLayoutX(0);
            seeProfile.setLayoutX(-150);

            onlineplayesListView.getItems().clear();
            onlineplayesListView.setVisible(false);
            onlinePlayesLabel.setVisible(false);
            seeProfile.setVisible(false);

        }
    }

    public void goToProfile(ActionEvent event)throws  Exception{

        String selectedName = onlineplayesListView.getSelectionModel().getSelectedItem();
        if(selectedName != null){

            Sound.stopMusic();
            Sound.playSoundEffect(1);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("playerProfilePage.fxml"));
            root = loader.load();
            ProfilePageController profilePageController = loader.getController();
            profilePageController.setOtherPlayerInfos(selectedName);
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }


    public  void getBiggerPlay(){
        playButton.setLayoutX(playButton.getLayoutX()-12.5);
        playButton.setLayoutY(playButton.getLayoutY()-12.5);
        playButton.setPrefHeight(75);
        playButton.setPrefWidth(75);
        playButtonLabel.setVisible(true);
        playButtonIcon.setFitWidth(54);
        playButtonIcon.setFitHeight(60);

    }
    public  void getSmallerPlay(){
        playButton.setLayoutX(playButton.getLayoutX()+12.5);
        playButton.setLayoutY(playButton.getLayoutY()+12.5);
        playButton.setPrefHeight(50);
        playButton.setPrefWidth(50);
        playButtonLabel.setVisible(false);
        playButtonIcon.setFitWidth(36);
        playButtonIcon.setFitHeight(40);
    }
    public  void getBiggerGChat(){
        globalChatButton.setLayoutX(globalChatButton.getLayoutX()-12.5);
        globalChatButton.setLayoutY(globalChatButton.getLayoutY()-12.5);
        globalChatButton.setPrefHeight(75);
        globalChatButton.setPrefWidth(75);
        globalChatButtonLabel.setVisible(true);
        globalChatButtonIcon.setFitWidth(54);
        globalChatButtonIcon.setFitHeight(60);

    }
    public  void getSmallerGChat(){
        globalChatButton.setLayoutX(globalChatButton.getLayoutX()+12.5);
        globalChatButton.setLayoutY(globalChatButton.getLayoutY()+12.5);
        globalChatButton.setPrefHeight(50);
        globalChatButton.setPrefWidth(50);
        globalChatButtonLabel.setVisible(false);
        globalChatButtonIcon.setFitWidth(36);
        globalChatButtonIcon.setFitHeight(40);
    }
    public  void getBiggerCMatch(){
        customMatchButton.setLayoutX(337.5);
        customMatchButton.setLayoutY(417.5);
        customMatchButton.setPrefHeight(75);
        customMatchButton.setPrefWidth(75);
        customMatchButtonLabel.setVisible(true);
        customMatchButtonIcon.setFitWidth(54);
        customMatchButtonIcon.setFitHeight(60);

    }
    public  void getSmallerCMatch(){
        customMatchButton.setLayoutX(350);
        customMatchButton.setLayoutY(430);
        customMatchButton.setPrefHeight(50);
        customMatchButton.setPrefWidth(50);
        customMatchButtonLabel.setVisible(false);
        customMatchButtonIcon.setFitWidth(36);
        customMatchButtonIcon.setFitHeight(40);
    }
    public  void getBiggerProfile(){
        profileButton.setLayoutX(profileButton.getLayoutX()-12.5);
        profileButton.setLayoutY(profileButton.getLayoutY()-12.5);
        profileButton.setPrefHeight(75);
        profileButton.setPrefWidth(75);
        profileButtonLabel.setVisible(true);
        profileButtonIcon.setFitWidth(56);
        profileButtonIcon.setFitHeight(60);

    }
    public  void getSmallerProfile(){
        profileButton.setLayoutX(profileButton.getLayoutX()+12.5);
        profileButton.setLayoutY(profileButton.getLayoutY()+12.5);
        profileButton.setPrefHeight(50);
        profileButton.setPrefWidth(50);
        profileButtonLabel.setVisible(false);
        profileButtonIcon.setFitWidth(36);
        profileButtonIcon.setFitHeight(40);
    }
    public  void getBiggerTutorial(){
        tutorialButton.setLayoutX(tutorialButton.getLayoutX()-12.5);
        tutorialButton.setLayoutY(tutorialButton.getLayoutY()-12.5);
        tutorialButton.setPrefHeight(75);
        tutorialButton.setPrefWidth(75);
        tutorialButtonLabel.setVisible(true);
        tutorialButtonIcon.setFitWidth(54);
        tutorialButtonIcon.setFitHeight(60);

    }
    public  void getSmallerTutorial(){
        tutorialButton.setLayoutX(tutorialButton.getLayoutX()+12.5);
        tutorialButton.setLayoutY(tutorialButton.getLayoutY()+12.5);
        tutorialButton.setPrefHeight(50);
        tutorialButton.setPrefWidth(50);
        tutorialButtonLabel.setVisible(false);
        tutorialButtonIcon.setFitWidth(36);
        tutorialButtonIcon.setFitHeight(40);
    }

    public void getBiggerWeapons(){
        weaponsButton.setLayoutX(817.5);
        weaponsButton.setLayoutY(262.5);
        weaponsButton.setPrefHeight(75);
        weaponsButton.setPrefWidth(75);
        weaponsButtonLabel.setVisible(true);
        weaponsButtonIcon.setFitWidth(54);
        weaponsButtonIcon.setFitHeight(60);

    }
    public  void getSmallerWeapons(){
        weaponsButton.setLayoutX(830);
        weaponsButton.setLayoutY(275);
        weaponsButton.setPrefHeight(50);
        weaponsButton.setPrefWidth(50);
        weaponsButtonLabel.setVisible(false);
        weaponsButtonIcon.setFitWidth(36);
        weaponsButtonIcon.setFitHeight(40);
    }
    public void getBiggerAvatars(){
        avatarsButton.setLayoutX(817.5);
        avatarsButton.setLayoutY(172.5);
        avatarsButton.setPrefHeight(75);
        avatarsButton.setPrefWidth(75);
        avatarsButtonLabel.setVisible(true);
        avatarsButtonIcon.setFitWidth(54);
        avatarsButtonIcon.setFitHeight(60);

    }
    public  void getSmallerAvatars(){
        avatarsButton.setLayoutX(830);
        avatarsButton.setLayoutY(185);
        avatarsButton.setPrefHeight(50);
        avatarsButton.setPrefWidth(50);
        avatarsButtonLabel.setVisible(false);
        avatarsButtonIcon.setFitWidth(36);
        avatarsButtonIcon.setFitHeight(40);
    }
    public  void showSettingsLabel(){

        settingsButtonLabel.setVisible(true);

    }
    public  void hideSettingsLabel(){

        settingsButtonLabel.setVisible(false);
    }

}