package sourcecode.maingame.searchandkill;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sourcecode.maingame.EssentialClasses.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public  class GamePlayController {


    @FXML
    private Label roundNoLabel;
    @FXML
    private Label roundStartingTimerLabel;
    @FXML
    private Rectangle announcementBox;

    @FXML
    private ImageView player0;
    @FXML
    private ImageView player1;
    @FXML
    private ImageView player2;

    @FXML
    private ImageView player0Gun;
    @FXML
    private ImageView player1Gun;
    @FXML
    private ImageView player2Gun;

    @FXML
    private ImageView player0GunEffect;
    @FXML
    private ImageView player1GunEffect;
    @FXML
    private ImageView player2GunEffect;

    @FXML
    private Label player0Name;
    @FXML
    private Label player1Name;
    @FXML
    private Label player2Name;

    @FXML
    private ProgressBar player0Life;
    @FXML
    private ProgressBar player1Life;
    @FXML
    private ProgressBar player2Life;


    @FXML
    private Rectangle instructionBox;
    @FXML
    private Polygon wPoli;
    @FXML
    private Polygon sPoli;
    @FXML
    private Polygon dPoli;
    @FXML
    private Polygon aPoli;
    @FXML
    private Label wLabel;
    @FXML
    private Label sLabel;
    @FXML
    private Label dLabel;
    @FXML
    private Label aLabel;
    @FXML
    private Label aimLabel1;
    @FXML
    private Label aimLabel2;
    @FXML
    private Label fireLabel1;
    @FXML
    private Label fireLabel2;

    @FXML
    private Rectangle matchResultBox;
    @FXML
    private Label matchResultLabel;
    @FXML
    private Rectangle matchResultFieldBox;
    @FXML
    private Rectangle player0Box;
    @FXML
    private Rectangle player1Box;
    @FXML
    private Rectangle player2Box;
    @FXML
    private Line rpLine;
    @FXML
    private Line nkLine;
    @FXML
    private Line kdLine;
    @FXML
    private Label matchResultFieldLabel;
    @FXML
    private Label player0NameLabel;
    @FXML
    private Label player1NameLabel;
    @FXML
    private Label player2NameLabel;
    @FXML
    private Label player0Kill;
    @FXML
    private Label player1Kill;
    @FXML
    private Label player2Kill;
    @FXML
    private Label player0Death;
    @FXML
    private Label player1Death;
    @FXML
    private Label player2Death;
    @FXML
    private ImageView rankImageOne;
    @FXML
    private ImageView rankImageTwo;
    @FXML
    private ImageView rankImageThree;

    @FXML
    private ImageView bloodEffect0;
    @FXML
    private ImageView bloodEffect1;
    @FXML
    private ImageView bloodEffect2;
    @FXML
    private Button okButton;

    @FXML
    private Label matchTimer;
    @FXML
    private Label matchRoundNumberLabel;

    @FXML
    private ImageView life3;
    @FXML
    private ImageView life2;
    @FXML
    private ImageView life1;

    @FXML
    private Label deathCountLabel;
    @FXML
    private Label killCountLabel;
    @FXML
    private ImageView fog;
    @FXML
    private  ImageView backGround;
    @FXML
    private ImageView markImage;

    private int speed = 5;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Label[] playersName = new Label[3];
    private ImageView[] playersImg = new ImageView[3];

    private ImageView[] playersGun = new ImageView[3];

    private ImageView[] playersGunEffect = new ImageView[3];
    private ProgressBar[] playersLife = new ProgressBar[3];

    private Label[] matchResultPlayersName = new Label[3];
    private Label[] matchResultPlayersKill = new Label[3];
    private Label[] matchResultPlayersDeath = new Label[3];

    private ImageView[] bloodEffect = new ImageView[3];

    private int roundNo = 1;


    private int[][] mapStructure0 ={{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    
    private int[][] mapStructure1 ={{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1},
                                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 ,0, 1},
                                    {1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    
    private int[][] mapStructure2 ={{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 1, 0 ,0 ,0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 ,0 ,1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0 ,0, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
                                    
    private int[][] mapStructure = mapStructure0;

    @FXML
    public void initialize(){
        playersImg[0] = player0;
        playersImg[1] = player1;
        playersImg[2] = player2;

        playersGun[0] = player0Gun;
        playersGun[1] = player1Gun;
        playersGun[2] = player2Gun;

        playersGunEffect[0] = player0GunEffect;
        playersGunEffect[1] = player1GunEffect;
        playersGunEffect[2] = player2GunEffect;

        playersName[0] = player0Name;
        playersName[1] = player1Name;
        playersName[2] = player2Name;

        playersLife[0] = player0Life;
        playersLife[1] = player1Life;
        playersLife[2] = player2Life;

        matchResultPlayersName[0] = player0NameLabel;
        matchResultPlayersName[1] = player1NameLabel;
        matchResultPlayersName[2] = player2NameLabel;

        matchResultPlayersKill[0] = player0Kill;
        matchResultPlayersKill[1] = player1Kill;
        matchResultPlayersKill[2] = player2Kill;

        matchResultPlayersDeath[0] = player0Death;
        matchResultPlayersDeath[1] = player1Death;
        matchResultPlayersDeath[2] = player2Death;

        bloodEffect[0] = bloodEffect0;
        bloodEffect[1] = bloodEffect1;
        bloodEffect[2] = bloodEffect2;

        for(int i = 0; i<playersLife.length; i++){
            if(i == SearchAndKill.playerIndex){
                playersLife[i].setStyle("-fx-accent: #008000;");
            }
            else {
                playersLife[i].setStyle("-fx-accent: #ff0000");
            }
        }

        if(SearchAndKill.mapIndex == 0){
            mapStructure = mapStructure0;
        }
        else if(SearchAndKill.mapIndex == 1){
            mapStructure = mapStructure1;
        }
        else{
            mapStructure = mapStructure2;
        }
        setNameAvatarGun();

    }

    public void moveUp() {

        playersImg[SearchAndKill.playerIndex].setRotate(0.0);
        playersGun[SearchAndKill.playerIndex].setRotate(0.0);
        playersGunEffect[SearchAndKill.playerIndex].setRotate(0.0);


        double px = playersImg[SearchAndKill.playerIndex].getLayoutX();
        double py = playersImg[SearchAndKill.playerIndex].getLayoutY();

        double newPx = px;
        double newPy = py - speed;

        int lBlockR = (int) (newPx / 40);
        int lBlockC = (int) (newPy / 40);
        int rBlockR = (int) ((newPx + 26) / 40);
        int rBlockC = (int) (newPy / 40);

        if (mapStructure[lBlockC][lBlockR] == 0 && mapStructure[rBlockC][rBlockR] == 0) {

            playersImg[SearchAndKill.playerIndex].setLayoutY(newPy);
            playersGun[SearchAndKill.playerIndex].setLayoutY(newPy-13);
            playersGunEffect[SearchAndKill.playerIndex].setLayoutY(newPy-23);
            playersName[SearchAndKill.playerIndex].setLayoutY(newPy-52);
            playersLife[SearchAndKill.playerIndex].setLayoutY(newPy-33);
            bloodEffect[SearchAndKill.playerIndex].setLayoutY(newPy);
            fog.setLayoutY(newPy-683);

            SearchAndKill.matchPlayers.get(SearchAndKill.playerIndex).setAngel((int)playersImg[SearchAndKill.playerIndex].getRotate());

            try {
                String request = "updateposition," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex) + "," + Integer.toString((int)newPx) + "," + Integer.toString((int)newPy) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getRotate())+","+Integer.toString(0);
                SearchAndKill.player.getOos().writeObject(request);
                Thread.sleep(2);
            }
            catch (Exception e) {
            }

        }

    }

    public void moveDown(){


        playersImg[SearchAndKill.playerIndex].setRotate(180.0);
        playersGun[SearchAndKill.playerIndex].setRotate(180.0);

        double px = playersImg[SearchAndKill.playerIndex].getLayoutX();
        double py = playersImg[SearchAndKill.playerIndex].getLayoutY();

        double newPx = px;
        double newPy = py + speed;

        int lBlockR = (int) ((newPx + 26) / 40);
        int lBlockC = (int) ((newPy + 35) / 40);
        int rBlockR = (int) (newPx / 40);
        int rBlockC = (int) ((newPy + 35) / 40);

        if (mapStructure[lBlockC][lBlockR] == 0 && mapStructure[rBlockC][rBlockR] == 0) {
            playersImg[SearchAndKill.playerIndex].setLayoutY(newPy);
            playersGun[SearchAndKill.playerIndex].setLayoutY(newPy-13);
            playersGunEffect[SearchAndKill.playerIndex].setLayoutY(newPy-23);
            playersName[SearchAndKill.playerIndex].setLayoutY(newPy-52);
            playersLife[SearchAndKill.playerIndex].setLayoutY(newPy-33);
            bloodEffect[SearchAndKill.playerIndex].setLayoutY(newPy);
            fog.setLayoutY(newPy-683);

            SearchAndKill.matchPlayers.get(SearchAndKill.playerIndex).setAngel((int)playersImg[SearchAndKill.playerIndex].getRotate());


            try {

                String request = "updateposition," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex) + "," + Integer.toString((int)newPx) + "," + Integer.toString((int)newPy) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getRotate())+","+Integer.toString(0);
                SearchAndKill.player.getOos().writeObject(request);
                Thread.sleep(2);
            }
            catch (Exception e) {
            }
        }

    }
    public void moveRight(){

        playersImg[SearchAndKill.playerIndex].setRotate(90.0);
        playersGun[SearchAndKill.playerIndex].setRotate(90.0);

        double px = playersImg[SearchAndKill.playerIndex].getLayoutX();
        double py = playersImg[SearchAndKill.playerIndex].getLayoutY();

        double newPx = px + speed;
        double newPy = py;

        int lBlockR = (int) ((newPx + 35) / 40);
        int lBlockC = (int) (newPy / 40);
        int rBlockR = (int) ((newPx + 35) / 40);
        int rBlockC = (int) ((newPy + 26) / 40);

        if (mapStructure[lBlockC][lBlockR] == 0 && mapStructure[rBlockC][rBlockR] == 0) {

            playersImg[SearchAndKill.playerIndex].setLayoutX(newPx);
            playersGun[SearchAndKill.playerIndex].setLayoutX(newPx-17);
            playersGunEffect[SearchAndKill.playerIndex].setLayoutX(newPx-27);
            playersName[SearchAndKill.playerIndex].setLayoutX(newPx-29);
            playersLife[SearchAndKill.playerIndex].setLayoutX(newPx-15);
            bloodEffect[SearchAndKill.playerIndex].setLayoutX(newPx-17);
            fog.setLayoutX(newPx-1215);

            SearchAndKill.matchPlayers.get(SearchAndKill.playerIndex).setAngel((int)playersImg[SearchAndKill.playerIndex].getRotate());

            try {

                String request = "updateposition," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex) + "," + Integer.toString((int)newPx) + "," + Integer.toString((int)newPy) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getRotate())+","+Integer.toString(0);
                SearchAndKill.player.getOos().writeObject(request);
                Thread.sleep(2);
            }
            catch (Exception e) {
            }

        }


    }
    public void moveLeft(){

        playersImg[SearchAndKill.playerIndex].setRotate(-90.0);
        playersGun[SearchAndKill.playerIndex].setRotate(-90.0);

        double px = playersImg[SearchAndKill.playerIndex].getLayoutX();
        double py = playersImg[SearchAndKill.playerIndex].getLayoutY();

        double newPx = px - speed;
        double newPy = py;

        int lBlockR = (int) (newPx / 40);
        int lBlockC = (int) ((newPy + 26) / 40);
        int rBlockR = (int) (newPx / 40);
        int rBlockC = (int) (newPy / 40);

        if (mapStructure[lBlockC][lBlockR] == 0 && mapStructure[rBlockC][rBlockR] == 0) {

            playersImg[SearchAndKill.playerIndex].setLayoutX(newPx);
            playersGun[SearchAndKill.playerIndex].setLayoutX(newPx-17);
            playersGunEffect[SearchAndKill.playerIndex].setLayoutX(newPx-27);
            playersName[SearchAndKill.playerIndex].setLayoutX(newPx-29);
            playersLife[SearchAndKill.playerIndex].setLayoutX(newPx-15);
            bloodEffect[SearchAndKill.playerIndex].setLayoutX(newPx-17);
            fog.setLayoutX(newPx-1215);

            SearchAndKill.matchPlayers.get(SearchAndKill.playerIndex).setAngel((int)playersImg[SearchAndKill.playerIndex].getRotate());

            try {

                String request = "updateposition," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex) + "," + Integer.toString((int)newPx) + "," + Integer.toString((int)newPy) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getRotate())+","+Integer.toString(0);
                SearchAndKill.player.getOos().writeObject(request);
                Thread.sleep(2);
            }
            catch (Exception e) { }

        }


    }

    public void playerAim(double x ,double y){

        if(!SearchAndKill.moving){

        double px = playersImg[SearchAndKill.playerIndex].getLayoutX() + 15;
        double py = playersImg[SearchAndKill.playerIndex].getLayoutY() +17.5;

        double base = x-px;
        double height = y-py;

        double diagonal = Math.sqrt((Math.pow(base,2.0))+Math.pow(height,2.0));
        double RadAngel = Math.asin((base/diagonal));
        double angel = Math.toDegrees(RadAngel);

        if(x>=px && y<=py) {

            playersImg[SearchAndKill.playerIndex].setRotate(angel);
            playersGun[SearchAndKill.playerIndex].setRotate(angel);
            try {
                String request = "updateposition," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getLayoutX()) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getLayoutY()) + "," + Integer.toString((int) playersImg[SearchAndKill.playerIndex].getRotate())+","+Integer.toString(0);
                SearchAndKill.player.getOos().writeObject(request);
            }
            catch (Exception e){ }

        }
        else if(x>=px && y>=py){

            playersImg[SearchAndKill.playerIndex].setRotate(180-angel);
            playersGun[SearchAndKill.playerIndex].setRotate(180-angel);

            try {
                String request = "updateposition," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getLayoutX()) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getLayoutY()) + "," + Integer.toString((int) playersImg[SearchAndKill.playerIndex].getRotate())+","+Integer.toString(0);
                SearchAndKill.player.getOos().writeObject(request);
            }
            catch (Exception e){ }

        }
        else if (x<=px && y<=py){
            playersImg[SearchAndKill.playerIndex].setRotate(angel);
            playersGun[SearchAndKill.playerIndex].setRotate(angel);

            try {
                String request = "updateposition," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getLayoutX()) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getLayoutY()) + "," + Integer.toString((int) playersImg[SearchAndKill.playerIndex].getRotate())+","+Integer.toString(0);
                SearchAndKill.player.getOos().writeObject(request);
            }
            catch (Exception e){ }
        }
        else {
            playersImg[SearchAndKill.playerIndex].setRotate(-180-angel);
            playersGun[SearchAndKill.playerIndex].setRotate(-180-angel);

            try {
                String request = "updateposition," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getLayoutX()) + "," + Integer.toString((int)playersImg[SearchAndKill.playerIndex].getLayoutY()) + "," + Integer.toString((int) playersImg[SearchAndKill.playerIndex].getRotate())+","+Integer.toString(0);
                SearchAndKill.player.getOos().writeObject(request);
            }
            catch (Exception e){ }
        }
    }

    }

    public  void fire () {

        if ( playersImg[SearchAndKill.playerIndex].isVisible() && SearchAndKill.updateGame) {

            Sound.stopMusic();
            SearchAndKill.moving = false;
            Sound.playSoundEffect(5);


            double angel = playersGun[SearchAndKill.playerIndex].getRotate();

            playersGunEffect[SearchAndKill.playerIndex].setRotate(angel);
            playersGunEffect[SearchAndKill.playerIndex].setVisible(true);
            new HideMatchGunFireEffectThread(this, playersGunEffect[SearchAndKill.playerIndex]);

            for (int i = 0; i < 3; i++) {
                if (i != SearchAndKill.playerIndex) {
                    if (isGotHit(angel, playersImg[SearchAndKill.playerIndex].getLayoutX(), playersImg[SearchAndKill.playerIndex].getLayoutY(), playersImg[i].getLayoutX(), playersImg[i].getLayoutY())) {
                        int distance = (int)getDistance(i);
                        System.out.println(distance);
                        if((SearchAndKill.matchPlayers.get(SearchAndKill.playerIndex).getGun().equals("riffel") && distance<160) || ( SearchAndKill.matchPlayers.get(SearchAndKill.playerIndex).getGun().equals("MachinGun") && distance<95)){

                        try {
                            SearchAndKill.player.getOos().writeObject("fire," + SearchAndKill.player.getName() + "," + SearchAndKill.matchIndex + "," + SearchAndKill.playerIndex + "," + Integer.toString(i));
                        }
                        catch (IOException e) {}
                    }
                    }
                }
            }

        }
    }
    public  void setRoundStartingTimerLabel(int time){

        String roundNoStr = "Round: "+Integer.toString(roundNo);
        String roundStartingTimerStr = "Starting at: "+Integer.toString(time)+" Seconds";

        roundNoLabel.setText(roundNoStr);
        roundStartingTimerLabel.setText(roundStartingTimerStr);


    }

    public void setNameAvatarGun(){

        try {

            SearchAndKill.player.getOos().writeObject("getGamePlayInfo," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex));
            Object gotObj = SearchAndKill.player.getOis().readObject();
            String infos = (String) gotObj;
            String []datas= infos.split(",");

            for (int i = 0; i < 3; i++) {

                Image playerImage = new Image(getClass().getResourceAsStream("match" + SearchAndKill.matchPlayers.get(i).getAvatar() + "New.png"));
                Image playerGun = new Image(getClass().getResourceAsStream("match" + SearchAndKill.matchPlayers.get(i).getGun() + ".png"));
                Image gunEffect = new Image(getClass().getResourceAsStream("fireEffectOK.png"));
                Image mapBackGround;
               
                if(SearchAndKill.mapIndex == 0){
                    mapBackGround = new Image(getClass().getResourceAsStream("map0.jpg"));
                }
                else if(SearchAndKill. mapIndex == 1){
                    mapBackGround = new Image(getClass().getResourceAsStream("map1.jpg"));
                }
                else{
                    mapBackGround = new Image(getClass().getResourceAsStream("map2.jpg"));
                }
                
                backGround.setImage(mapBackGround);

                playersName[i].setText(SearchAndKill.matchPlayers.get(i).getName());
                playersName[i].setLayoutX(Integer.parseInt(datas[(i * 6) + 1])-29);
                playersName[i].setLayoutY(Integer.parseInt(datas[(i * 6) + 2])-52);

                playersImg[i].setImage(playerImage);
                playersImg[i].setLayoutX(Integer.parseInt(datas[(i * 6) + 1]));
                playersImg[i].setLayoutY(Integer.parseInt(datas[(i * 6) + 2]));
                playersImg[i].setRotate(Integer.parseInt(datas[(i * 6) + 3]));

                playersGun[i].setImage(playerGun);
                playersGun[i].setLayoutX(Integer.parseInt(datas[(i * 6) + 1])-17);
                playersGun[i].setLayoutY(Integer.parseInt(datas[(i * 6) + 2])-13);
                playersGun[i].setRotate(Integer.parseInt(datas[(i * 6) + 3]));

                playersGunEffect[i].setImage(gunEffect);
                playersGunEffect[i].setLayoutX(Integer.parseInt(datas[(i * 6) + 1])-27);
                playersGunEffect[i].setLayoutY(Integer.parseInt(datas[(i * 6) + 2])-23);
                playersGunEffect[i].setRotate(Integer.parseInt(datas[(i * 6) + 3]));

                bloodEffect[i].setLayoutX(Integer.parseInt(datas[(i * 6) + 1])-17);
                bloodEffect[i].setLayoutY(Integer.parseInt(datas[(i * 6) + 2]));

                playersLife[i].setLayoutX(Integer.parseInt(datas[(i * 6) + 1])-15);
                playersLife[i].setLayoutY(Integer.parseInt(datas[(i * 6) + 2])-33);

                SearchAndKill.matchPlayers.get(i).setPosX(Integer.parseInt(datas[(i * 6) + 1]));
                SearchAndKill.matchPlayers.get(i).setPosY(Integer.parseInt(datas[(i * 6) + 2]));

                fog.setLayoutX(playersImg[SearchAndKill.playerIndex].getLayoutX()-1215);
                fog.setLayoutY(playersImg[SearchAndKill.playerIndex].getLayoutY()-683);

                if(i == SearchAndKill.playerIndex) {
                    killCountLabel.setText(Integer.toString(SearchAndKill.matchPlayers.get(i).getKills()));
                    deathCountLabel.setText(Integer.toString(SearchAndKill.matchPlayers.get(i).getDeaths()));

                    SearchAndKill.player.setStartX(Integer.parseInt(datas[(i * 6) + 1]));
                    SearchAndKill.player.setStartY(Integer.parseInt(datas[(i * 6) + 2]));
                }

                markImage.setLayoutX(-100);
                markImage.setLayoutY(-100);


            }
        }
        catch (Exception e){}

    }

    public void updatePositions(){

        try {

            SearchAndKill.player.getOos().writeObject("getGamePlayInfo," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex));

            Object gotObj = SearchAndKill.player.getOis().readObject();
            String infos = (String) gotObj;

            System.out.println(infos);

            String[] data = infos.split(",");

            for (int i = 0; i<3; i++) {

                if (i != SearchAndKill.playerIndex ) {

                    int x = Integer.parseInt(data[(i * 6) + 1]);
                    int y = Integer.parseInt(data[(i * 6) + 2]);
                    int angel = Integer.parseInt(data[(i * 6) + 3]);

                    playersName[i].setLayoutX(x - 29);
                    playersName[i].setLayoutY(y - 52);

                    playersLife[i].setLayoutX(x - 15);
                    playersLife[i].setLayoutY(y - 33);

                    playersImg[i].setLayoutX(x);
                    playersImg[i].setLayoutY(y);
                    playersImg[i].setRotate(angel);

                    playersGun[i].setLayoutX(x - 17);
                    playersGun[i].setLayoutY(y - 13);
                    playersGun[i].setRotate(angel);

                    playersGunEffect[i].setLayoutX(x - 27);
                    playersGunEffect[i].setLayoutY(y - 23);
                    playersGunEffect[i].setRotate(angel);

                    bloodEffect[i].setLayoutX(x-17);
                    bloodEffect[i].setLayoutY(y);


                }

                if(i == SearchAndKill.markedPlayerIndex){
                    markImage.setLayoutX(Integer.parseInt(data[(i * 6) + 1])-10);
                    markImage.setLayoutY(Integer.parseInt(data[(i * 6) + 2]));
                }

                int tempLife = Integer.parseInt(data[(i*6)+4]);
                if(tempLife< SearchAndKill.matchPlayers.get(i).getLife()){
                    bloodEffect[i].setVisible(true);
                    new HideBloodEffectThread(this,bloodEffect[i]);
                }
                SearchAndKill.matchPlayers.get(i).setLife(tempLife);

                if(tempLife>0) {

                    if (tempLife > 200) {
                        tempLife -= 200;
                    }
                    else if (tempLife > 100) {

                        if(i == SearchAndKill.playerIndex){
                            if(life3.isVisible()){
                                re();
                            }
                            life3.setVisible(false);

                        }
                        tempLife -= 100;
                        SearchAndKill.matchPlayers.get(i).setDeaths(1);

                    }
                    else {

                        if(i == SearchAndKill.playerIndex){
                            if(life2.isVisible()){
                                re();
                            }
                            life2.setVisible(false);
                        }
                        SearchAndKill.matchPlayers.get(i).setDeaths(2);
                    }
                    playersLife[i].setProgress(((double) tempLife) / 100);
                }
                else{

                    if(i == SearchAndKill.playerIndex){
                        life1.setVisible(false);
                        fog.setVisible(false);
                    }
                    SearchAndKill.matchPlayers.get(i).setDeaths(3);

                    playersName[i].setVisible(false);
                    playersLife[i].setVisible(false);
                    playersImg[i].setVisible(false);
                    playersGun[i].setVisible(false);
                    playersGunEffect[i].setVisible(false);

                }

                if(i == SearchAndKill.playerIndex) {
                    killCountLabel.setText(data[(i * 6) + 5]);
                    deathCountLabel.setText(data[(i * 6) + 6]);
                }


            }

            if((data[6].equals("3") && data[12].equals("3")) || (data[6].equals("3") && data[18].equals("3")) || (data[12].equals("3") && data[18].equals("3"))){
                SearchAndKill.updateGame = false;
                roundNo = 4;
               
            }


        }
        catch (Exception e){}

    }

    public void showMatchResult(String phase){

        try {
            Thread.sleep(500);
            fog.setVisible(false);
            SearchAndKill.player.getOos().writeObject("getmatchresult,"+SearchAndKill.player.getName()+","+SearchAndKill.matchIndex+","+SearchAndKill.playerIndex+","+phase);
            Object gotResutl = SearchAndKill.player.getOis().readObject();
            String resultStr = (String)gotResutl;

            System.out.println(resultStr);

            String[] resultData = resultStr.split(",");

            int[] kills = new int[3];
            int[] deaths = new int[3];
            String[] names = new String[3];

            for(int i=0; i<SearchAndKill.matchPlayers.size(); i++){

                matchResultPlayersName[i].setVisible(true);
                matchResultPlayersDeath[i].setVisible(true);
                matchResultPlayersKill[i].setVisible(true);

                if(i == SearchAndKill.playerIndex){

                    SearchAndKill.player.setDeaths(SearchAndKill.player.getDeaths()+ Integer.parseInt(resultData[(i*3)+1]));
                    SearchAndKill.player.setKills(SearchAndKill.player.getKills()+Integer.parseInt(resultData[(i*3)+2]));
                    SearchAndKill.player.setMatchPlayed(SearchAndKill.player.getMatchPlayed()+1);
                }
            }


            Platform.runLater(()-> {

                for(int i=0; i<SearchAndKill.matchPlayers.size(); i++){                
                    matchResultPlayersName[i].setText(resultData[(i*3)]);
                    matchResultPlayersDeath[i].setText(Integer.toString(Integer.parseInt(resultData[(i*3)+1])));
                    matchResultPlayersKill[i].setText(resultData[(i*3)+2]);
                }
            });


            matchResultBox.setVisible(true);
            matchResultLabel.setVisible(true);
            matchResultFieldBox.setVisible(true);
            player0Box.setVisible(true);
            player1Box.setVisible(true);
            player2Box.setVisible(true);
            rpLine.setVisible(true);
            nkLine.setVisible(true);
            kdLine.setVisible(true);
            rankImageOne.setVisible(true);
            rankImageTwo.setVisible(true);
            rankImageThree.setVisible(true);
            matchResultFieldLabel.setVisible(true);

            okButton.setVisible(true);
        }
        catch (Exception e){}

    }

    public void showOrHideInstruction(){

        if(instructionBox.isVisible()){

            instructionBox.setVisible(false);

            wPoli.setVisible(false);
            sPoli.setVisible(false);
            dPoli.setVisible(false);
            aPoli.setVisible(false);

            wLabel.setVisible(false);
            sLabel.setVisible(false);
            dLabel.setVisible(false);
            aLabel.setVisible(false);

            aimLabel1.setVisible(false);
            aimLabel2.setVisible(false);
            fireLabel1.setVisible(false);
            fireLabel2.setVisible(false);
        }
        else{

            instructionBox.setVisible(true);

            wPoli.setVisible(true);
            sPoli.setVisible(true);
            dPoli.setVisible(true);
            aPoli.setVisible(true);

            wLabel.setVisible(true);
            sLabel.setVisible(true);
            dLabel.setVisible(true);
            aLabel.setVisible(true);

            aimLabel1.setVisible(true);
            aimLabel2.setVisible(true);
            fireLabel1.setVisible(true);
            fireLabel2.setVisible(true);
        }
    }

    public void goBack(ActionEvent event) throws IOException {



        Sound.playSoundEffect(1);
        Sound.playMusic(0);

        SearchAndKill.matchPlayers = new ArrayList<Player>();

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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void re(){

        playersName[SearchAndKill.playerIndex].setLayoutX(SearchAndKill.player.getStartX() - 29);
        playersName[SearchAndKill.playerIndex].setLayoutY(SearchAndKill.player.getStartY() - 52);

        playersLife[SearchAndKill.playerIndex].setLayoutX(SearchAndKill.player.getStartX() - 15);
        playersLife[SearchAndKill.playerIndex].setLayoutY(SearchAndKill.player.getStartY() - 33);

        playersImg[SearchAndKill.playerIndex].setLayoutX(SearchAndKill.player.getStartX());
        playersImg[SearchAndKill.playerIndex].setLayoutY(SearchAndKill.player.getStartY());
        playersImg[SearchAndKill.playerIndex].setRotate(0);

        playersGun[SearchAndKill.playerIndex].setLayoutX(SearchAndKill.player.getStartX() - 17);
        playersGun[SearchAndKill.playerIndex].setLayoutY(SearchAndKill.player.getStartY() - 13);
        playersGun[SearchAndKill.playerIndex].setRotate(0);

        playersGunEffect[SearchAndKill.playerIndex].setLayoutX(SearchAndKill.player.getStartX() - 27);
        playersGunEffect[SearchAndKill.playerIndex].setLayoutY(SearchAndKill.player.getStartY() - 23);
        playersGunEffect[SearchAndKill.playerIndex].setRotate(0);

        bloodEffect[SearchAndKill.playerIndex].setLayoutX(SearchAndKill.player.getStartX()-17);
        bloodEffect[SearchAndKill.playerIndex].setLayoutY(SearchAndKill.player.getStartY());

        fog.setLayoutY(SearchAndKill.player.getStartY()-683);
        fog.setLayoutX(SearchAndKill.player.getStartX()-1215);

        try {

            String request = "updateposition," + SearchAndKill.player.getName() + "," + Integer.toString(SearchAndKill.matchIndex) + "," + Integer.toString(SearchAndKill.playerIndex) + "," + Integer.toString(SearchAndKill.player.getStartX()) + "," + Integer.toString(SearchAndKill.player.getStartY()) + "," + Integer.toString(0)+","+Integer.toString(0);
            SearchAndKill.player.getOos().writeObject(request);
        }
        catch (Exception e) { }


    }
    public boolean isGotHit(double angel, double fX, double fY, double tX, double tY){

        fX = fX+13;
        fY = fY+17;
        angel = Math.toRadians(90-angel);

        if(!isBulletBlocked(angel,fX,fY,tX,tY)){

            if(isGetIntersect(angel,fX,fY,tX,tY,35)) {
                if ((Math.toDegrees(angel) >= 0 && Math.toDegrees(angel) <= 90 && fX <= tX && fY >= tY) || (Math.toDegrees(angel) >= 90 && Math.toDegrees(angel) <= 180 && fX >= tX && fY >= tY) || (Math.toDegrees(angel) >= 180 && Math.toDegrees(angel) <= 270 && fX >= tX && fY <= tY) || (Math.toDegrees(angel) <= 0 && Math.toDegrees(angel) >= -90 && fX <= tX && fY <= tY)) {

                    return  true;
                }
            }
        }

        return  false;
    }

    public boolean isBulletBlocked(double angel,double startingX,double startingY,double endingX,double endingY ){
        {
            int startingPositionBlockRow = (int) (startingY / 40.0);
            int startingPositionBlockColumn = (int) (startingX / 40.0);
            int endingPositionBlockRow = (int) (endingY / 40.0);
            int endingPositionBlockColumn = (int) (endingX / 40.0);

            int rowStart;
            int rowEnd;

            if (startingPositionBlockRow > endingPositionBlockRow) {
                rowStart = endingPositionBlockRow;
                rowEnd = startingPositionBlockRow;
            }
            else {
                rowStart = startingPositionBlockRow;
                rowEnd = endingPositionBlockRow;
            }

            int columnStart;
            int columnEnd;

            if (startingPositionBlockColumn > endingPositionBlockColumn) {
                columnStart = endingPositionBlockColumn;
                columnEnd = startingPositionBlockColumn;
            } else {
                columnStart = startingPositionBlockColumn;
                columnEnd = endingPositionBlockColumn;
            }


            if (startingPositionBlockRow == endingPositionBlockRow && startingPositionBlockColumn != endingPositionBlockColumn) {

                for(int i = columnStart+1; i<columnEnd ; i++){
                    if(mapStructure[rowStart][i] == 1){
                        if(isGetIntersect(angel,startingX,startingY,i*40, rowStart*40,40)){
                            return true;
                        }
                    }
                }

            }
            else if (startingPositionBlockColumn == endingPositionBlockColumn) {

                for(int i = rowStart+1; i<rowEnd ; i++){
                    if(mapStructure[i][columnStart] == 1){
                        if(isGetIntersect(angel,startingX,startingY,columnStart*40, i*40,40)){
                            return true;
                        }
                    }
                }

            }
            else {

                for(int i = rowStart; i<= rowEnd; i++){
                    for(int j = columnStart ; j<= columnEnd; j++){
                        if(!((startingPositionBlockColumn == j && startingPositionBlockRow == i) || (endingPositionBlockColumn == j && endingPositionBlockRow ==i)) && mapStructure[i][j]==1){

                            if(isGetIntersect(angel,startingX,startingY,j*40,i*40,40) ){
                                return  true;
                            }

                        }
                    }
                }




            }
        }
        return false;
    }

    public boolean isGetIntersect(double angel,double fX,double fY,double tX,double tY,int edge) {

        double m = Math.tan(-angel);
        double c = fY - (m*fX);

        double[] interceptsX = new double[4];
        double[] interceptsY = new double[4];

        interceptsX[0]= tX;
        interceptsY[0] = m*interceptsX[0] + c;

        interceptsX[1] = tX+edge;
        interceptsY[1] = (m*interceptsX[1]) + c;

        interceptsY[2] = tY;
        interceptsX[2] = (interceptsY[2]-c)/m;

        interceptsY[3] = tY+edge;
        interceptsX[3] = (interceptsY[3]-c)/m;

        for (int i = 0; i < 4; i++) {

            if (interceptsX[i] >= tX && interceptsX[i] <= (tX + edge) && interceptsY[i] >= tY && interceptsY[i] <= (tY + edge)){

                return  true;

            }
        }

        return  false;

    }

    public double getDistance(int index){

        double x1 = playersImg[SearchAndKill.playerIndex].getLayoutX() + playersImg[SearchAndKill.playerIndex].getFitWidth() / 2;
        double y1 =  playersImg[SearchAndKill.playerIndex].getLayoutY() +  playersImg[SearchAndKill.playerIndex].getFitHeight() / 2;
        double x2 = playersImg[index].getLayoutX() +playersImg[index].getFitWidth() / 2;
        double y2 = playersImg[index].getLayoutY() + playersImg[index].getFitHeight() / 2;

        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distance;
    }



    public Label getRoundNoLabel(){
        return  this.roundNoLabel;
    }
    public Label getRoundStartingTimerLabel(){
        return  this.roundStartingTimerLabel;
    }
    public Rectangle getAnnouncementBox(){
        return  this.announcementBox;
    }
    public  Label getMatchTimer(){
        return  this.matchTimer;
    }
    public void setMatchTimer(int time){
        matchTimer.setText(Integer.toString(time));
    }

   public int getRoundNo(){
        return this.roundNo;
   }
   public void setRoundNo(int roundNo){
        this.roundNo = roundNo;
   }
   public Label getMatchRoundNumberLabel(){
        return this.matchRoundNumberLabel;
   }
   public ImageView getMarkImage(){
        return  this. markImage;
   }



}

class RoundBreakThread implements Runnable{

    private GamePlayController gpc;
    private Thread thread;

    public RoundBreakThread(GamePlayController gpc){

        this.gpc = gpc;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run(){

        if(gpc.getRoundNo()<=3) {

            Platform.runLater(() -> gpc.getMatchRoundNumberLabel().setText(Integer.toString(gpc.getRoundNo())));
            gpc.getRoundNoLabel().setVisible(true);
            gpc.getRoundStartingTimerLabel().setVisible(true);
            gpc.getAnnouncementBox().setVisible(true);

            for (int i = 3; i >= 0; i--) {

                final int time = i;
                Platform.runLater(() -> gpc.setRoundStartingTimerLabel(time));

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {}

            }

            gpc.getRoundNoLabel().setVisible(false);
            gpc.getRoundStartingTimerLabel().setVisible(false);
            gpc.getAnnouncementBox().setVisible(false);


            SearchAndKill.updateGame = true;


            new MatchTimerThread(gpc);
            new GamePlayPositionUpdateThread(gpc);

        }
        else{
            gpc.showMatchResult("end");
        }
    }
}

class MatchTimerThread implements Runnable {

    Thread thread;
    GamePlayController gpc;

    public MatchTimerThread(GamePlayController gpc) {

        this.gpc = gpc;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {

        for (int i = 30; i >= 0 && SearchAndKill.updateGame; i--) {


            final int time = i;
            Platform.runLater(() -> gpc.setMatchTimer(time));

            if(i%10 == 0){
                new ShowMarkedImage(gpc,gpc.getMarkImage());
            }


            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {}
        }
        
        gpc.re();
        SearchAndKill.updateGame = false;
        gpc.setRoundNo(gpc.getRoundNo() + 1);
        new RoundBreakThread(gpc);

    }
}

class GamePlayPositionUpdateThread implements Runnable{

    private GamePlayController gpc;
    private Thread thread;

    public GamePlayPositionUpdateThread(GamePlayController gpc){

        this.gpc = gpc;
        this.thread = new Thread(this);
        this.thread.start();

    }

    @Override
    public void run() {

        while (SearchAndKill.updateGame) {
            try {

                Platform.runLater(()-> gpc.updatePositions());
                Thread.sleep(30);
            }
            catch (Exception e) {
            }

        }
    }
}


class HideMatchGunFireEffectThread implements Runnable{

    Thread thread;
    GamePlayController gpc;
    ImageView gunEffect;
    public  HideMatchGunFireEffectThread(GamePlayController gpc, ImageView gunEffect){
        this.gpc = gpc;
        this.gunEffect = gunEffect;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public  void run(){
        try {
            Thread.sleep(100);
        }
        catch(InterruptedException e){}

        gunEffect.setVisible(false);

    }
}
class ShowMarkedImage implements Runnable{

    Thread thread;
    GamePlayController gpc;
    ImageView markImage;
    public  ShowMarkedImage(GamePlayController gpc, ImageView markImage){
        this.gpc = gpc;
        this.markImage = markImage;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public  void run(){


        Random random = new Random();
        int tempMarkedPlayerIndex =0;

        for(int i =0; i<100; i++) {

            tempMarkedPlayerIndex = random.nextInt(SearchAndKill.matchPlayers.size());

            if(tempMarkedPlayerIndex != SearchAndKill.playerIndex && SearchAndKill.matchPlayers.get(tempMarkedPlayerIndex).getLife()>0){
                SearchAndKill.markedPlayerIndex = tempMarkedPlayerIndex;
                break;
            }
        }

        try {
            Thread.sleep(30);
        }
        catch(InterruptedException e){}

        markImage.setVisible(true);

        new HideMarkedImage(gpc, gpc.getMarkImage());
        new RotateMarkedImage(gpc,gpc.getMarkImage());

    }
}
class HideMarkedImage implements Runnable{

    Thread thread;
    GamePlayController gpc;
    ImageView markImage;
    public  HideMarkedImage(GamePlayController gpc, ImageView markImage){
        this.gpc = gpc;
        this.markImage = markImage;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public  void run(){
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){}

        markImage.setVisible(false);

    }
}

class RotateMarkedImage implements Runnable{

    Thread thread;
    GamePlayController gpc;
    ImageView markImage;
    public  RotateMarkedImage(GamePlayController gpc, ImageView markImage){
        this.gpc = gpc;
        this.markImage = markImage;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public  void run(){

        int i =0;
        while (markImage.isVisible()){
            final int rotation = i;
            Platform.runLater(()-> markImage.setRotate(rotation));
            i++;
            if(i==180){
                i= -180;
            }
            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e ){}
        }


    }
}


class HideBloodEffectThread implements Runnable{

    Thread thread;
    GamePlayController gpc;
    ImageView bloodEffect;
    public  HideBloodEffectThread(GamePlayController gpc, ImageView bloodEffect){
        this.gpc = gpc;
        this.bloodEffect = bloodEffect;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public  void run(){
        try {
            Thread.sleep(150);
        }
        catch(InterruptedException e){}

        bloodEffect.setVisible(false);

    }
}