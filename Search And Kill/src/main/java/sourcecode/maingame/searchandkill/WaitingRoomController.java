package sourcecode.maingame.searchandkill;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sourcecode.maingame.EssentialClasses.Player;

import java.io.IOException;

public  class WaitingRoomController {
    @FXML
    private Label playerOneName;
    @FXML
    private Label playerTwoName;
    @FXML
    private Label playerThreeName;
    @FXML
    private ImageView p1Icon;
    @FXML
    private ImageView p2Icon;
    @FXML
    private ImageView p3Icon;

    private Parent root;
    private Scene scene;
    private Stage stage;
    
    public  void  setInfos(String infos){

        String[] data = infos.split(",");

        if(data.length <=4){

            playerOneName.setText(data[1]);
            Image icon1 = new Image(getClass().getResourceAsStream(data[2]+"Icon.png"));
            p1Icon.setImage(icon1);

        }
        else if(data.length <= 7){

            playerOneName.setText(data[1]);
            Image icon1 = new Image(getClass().getResourceAsStream(data[2]+"Icon.png"));
            p1Icon.setImage(icon1);

            playerTwoName.setText(data[4]);
            Image icon2 = new Image(getClass().getResourceAsStream(data[5]+"Icon.png"));
            p2Icon.setImage(icon2);

        }
        else if(data.length <= 10){

            playerOneName.setText(data[1]);
            Image icon1 = new Image(getClass().getResourceAsStream(data[2]+"Icon.png"));
            p1Icon.setImage(icon1);

            playerTwoName.setText(data[4]);
            Image icon2 = new Image(getClass().getResourceAsStream(data[5]+"Icon.png"));
            p2Icon.setImage(icon2);

            playerThreeName.setText(data[7]);
            Image icon3 = new Image(getClass().getResourceAsStream(data[8]+"Icon.png"));
            p3Icon.setImage(icon3);

        }

    }
    
}

class WaitingRoomInfoUpdateThread implements Runnable {

    private Thread thread;
    private WaitingRoomController wc;
    private Parent root;
    private Scene scene;
    private Stage stage;

    public WaitingRoomInfoUpdateThread(WaitingRoomController wc,Stage stage) {

        this.wc = wc;
        this.stage =stage;
        this.thread = new Thread(this);
        this.thread.start();

    }

    @Override
    public void run() {

        while (SearchAndKill.updateWRoom) {

            try {

                SearchAndKill.player.getOos().writeObject("getWRoomInfo," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex));
                Object getObj = SearchAndKill.player.getOis().readObject();
                final String infos = (String) getObj;
                String[] data = infos.split(",");

                Platform.runLater(()-> wc.setInfos(infos));

                Thread.sleep(10);

                if(data.length == 10){

                    Sound.stopMusic();
                    SearchAndKill.updateWRoom = false;

                    for(int i =0; i<3; i++){

                        Player player = new Player();
                        player.setName(data[(i*3)+1]);
                        player.setAvatar(data[(i*3)+2]);
                        player.setGun(data[(i*3)+3]);
                        player.setKills(0);
                        player.setDeaths(0);

                        SearchAndKill.matchPlayers.add(player);
                    }


                    Platform.runLater(()->{

                        try {

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("gamePlay.fxml"));
                            root = loader.load();
                            GamePlayController gpc = loader.getController();
                            scene = new Scene(root,1240,680);

                            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                                @Override
                                public  void handle(KeyEvent pressedKey){
                                    if(pressedKey.getCode() == KeyCode.W){
                                        if(!SearchAndKill.moving){
                                            Sound.playMusic(4);
                                        }
                                        SearchAndKill.moving = true;
                                        gpc.moveUp();
                                    }
                                    if(pressedKey.getCode() == KeyCode.S){
                                        if(!SearchAndKill.moving){
                                            Sound.playMusic(4);
                                        }
                                        SearchAndKill.moving = true;
                                        gpc.moveDown();
                                    }
                                    if(pressedKey.getCode() == KeyCode.D){
                                        if(!SearchAndKill.moving){
                                            Sound.playMusic(4);
                                        }
                                        SearchAndKill.moving = true;
                                        gpc.moveRight();
                                    }
                                    if(pressedKey.getCode() == KeyCode.A){
                                        if(!SearchAndKill.moving){
                                            Sound.playMusic(4);
                                        }
                                        SearchAndKill.moving = true;
                                        gpc.moveLeft();
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

                            scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    double x = mouseEvent.getSceneX();
                                    double y = mouseEvent.getSceneY();
                                    gpc.playerAim(x,y);

                                }
                            });

                            scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    gpc.fire();
                                }
                            });

                            stage.setScene(scene);
                            stage.show();

                            new RoundBreakThread(gpc);

                        }
                        catch (IOException e){}

                    });

                }

            }
            catch (Exception e) {}

        }
    }
}