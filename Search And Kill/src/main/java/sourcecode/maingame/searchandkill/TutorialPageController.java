package sourcecode.maingame.searchandkill;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TutorialPageController implements Initializable {

    private int[][] mapStructure = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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

    @FXML
    private ImageView player;
    @FXML
    private ImageView gun;
    @FXML
    private ImageView gunEffect;
    @FXML
    private Label instructionsLabel;
    @FXML
    private Rectangle instBox;
    @FXML
    private Label terPos;
    @FXML
    private ImageView enemyPlayer;
    @FXML
    private ImageView enemyGun;
    @FXML
    private Label enemyName;
    @FXML
    private ProgressBar enemyLife;


    private  double speed = 5;
    private boolean wTue = false,sTue = false,dTue = false,aTue = false;
    private boolean quadrant1 = false,quadrant2 = false,quadrant3 = false,quadrant4 = false, done = false, xMarked = false;
    private  boolean mouseClicked = false;
    private  int enemyLifeValue = 100;
    private boolean killed = false;

    private Parent root;
    private Stage stage;
    private Scene scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enemyLife.setStyle("-fx-accent: #ff0000;");
    }
    public void moveUp() {

        player.setRotate(0.0);
        gun.setRotate(0.0);

        double px = player.getLayoutX();
        double py = player.getLayoutY();

        double newPx = px;
        double newPy = py - speed;

        int lBlockR = (int) (newPx / 40);
        int lBlockC = (int) (newPy / 40);
        int rBlockR = (int) ((newPx + 26) / 40);
        int rBlockC = (int) (newPy / 40);

        if (mapStructure[lBlockC][lBlockR] == 0 && mapStructure[rBlockC][rBlockR] == 0) {
            player.setLayoutY(player.getLayoutY() - speed);
            gun.setLayoutY(gun.getLayoutY() - speed);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }

        }

        if (!wTue && !sTue && !dTue && !aTue) {
            setInstructions("Press and hold 's' key for move down");
            wTue = true;
        }

        if(!done && wTue && sTue && dTue && aTue && (newPx>= terPos.getLayoutX() && newPx<= (terPos.getLayoutX()+40) && newPy>= terPos.getLayoutY() && newPy<= (terPos.getLayoutY()+80))){
            setInstructions("Now move the cursor around the player to make the aim");
            terPos.setVisible(false);
            quadrant1 = false;
            quadrant2 = false;
            quadrant3 = false;
            quadrant4 = false;
            xMarked = true;


        }

    }

    public void moveDown() {

        player.setRotate(180.0);
        gun.setRotate(180.0);

        double px = player.getLayoutX();
        double py = player.getLayoutY();

        double newPx = px;
        double newPy = py + speed;

        int lBlockR = (int) ((newPx + 26) / 40);
        int lBlockC = (int) ((newPy + 35) / 40);
        int rBlockR = (int) (newPx / 40);
        int rBlockC = (int) ((newPy + 35) / 40);

        if (mapStructure[lBlockC][lBlockR] == 0 && mapStructure[rBlockC][rBlockR] == 0) {
            player.setLayoutY(player.getLayoutY() + speed);
            gun.setLayoutY(gun.getLayoutY() + speed);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }

        if (wTue && !sTue && !dTue && !aTue) {
            setInstructions("Press and hold 'd' key for move right");
            sTue = true;
        }

        if(!done && wTue && sTue && dTue && aTue && (newPx>= terPos.getLayoutX() && newPx<= (terPos.getLayoutX()+40) && newPy>= terPos.getLayoutY() && newPy<= (terPos.getLayoutY()+80))){
            setInstructions("Now move the cursor around the player to make the aim");
            terPos.setVisible(false);
            quadrant1 = false;
            quadrant2 = false;
            quadrant3 = false;
            quadrant4 = false;
            xMarked = true;

        }

    }

    public void moveRight() {

        player.setRotate(90.0);
        gun.setRotate(90.0);

        double px = player.getLayoutX();
        double py = player.getLayoutY();

        double newPx = px + speed;
        double newPy = py;

        int lBlockR = (int) ((newPx + 35) / 40);
        int lBlockC = (int) (newPy / 40);
        int rBlockR = (int) ((newPx + 35) / 40);
        int rBlockC = (int) ((newPy + 26) / 40);

        if (mapStructure[lBlockC][lBlockR] == 0 && mapStructure[rBlockC][rBlockR] == 0) {


            player.setLayoutX(player.getLayoutX() + speed);
            gun.setLayoutX(gun.getLayoutX() + speed);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }

        }

        if (wTue && sTue && !dTue && !aTue) {
            setInstructions("Press and hold 'a' key for move left");
            dTue = true;
        }

        if(!done && wTue && sTue && dTue && aTue && (newPx>= terPos.getLayoutX() && newPx<= (terPos.getLayoutX()+40) && newPy>= terPos.getLayoutY() && newPy<= (terPos.getLayoutY()+80))){
            setInstructions("Now move the cursor around the player to make the aim");
            terPos.setVisible(false);
            quadrant1 = false;
            quadrant2 = false;
            quadrant3 = false;
            quadrant4 = false;
            xMarked = true;

        }
    }

    public void moveLeft() {

        player.setRotate(-90.0);
        gun.setRotate(-90.0);

        double px = player.getLayoutX();
        double py = player.getLayoutY();

        double newPx = px - speed;
        double newPy = py;

        int lBlockR = (int) (newPx / 40);
        int lBlockC = (int) ((newPy + 26) / 40);
        int rBlockR = (int) (newPx / 40);
        int rBlockC = (int) (newPy / 40);

        if (mapStructure[lBlockC][lBlockR] == 0 && mapStructure[rBlockC][rBlockR] == 0) {
            player.setLayoutX(player.getLayoutX() - speed);
            gun.setLayoutX(gun.getLayoutX() - speed);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }

        }

        if (wTue && sTue && dTue && !aTue) {
            setInstructions("Go to X marked position");
            aTue = true;
            terPos.setVisible(true);
        }

        if(!done && wTue && sTue && dTue && aTue && (newPx>= terPos.getLayoutX() && newPx<= (terPos.getLayoutX()+40) && newPy>= terPos.getLayoutY() && newPy<= (terPos.getLayoutY()+80))){
            setInstructions("Now move the cursor around the player to make the aim");
            terPos.setVisible(false);
            quadrant1 = false;
            quadrant2 = false;
            quadrant3 = false;
            quadrant4 = false;
            xMarked = true;


        }
    }

    public void playerAim(double x ,double y){

        if(wTue && sTue && dTue && aTue && xMarked && !SearchAndKill.moving) {
            double px = player.getLayoutX() + 15;
            double py = player.getLayoutY() + 17.5;

            double base = x - px;
            double height = y - py;

            double diagonal = Math.sqrt((Math.pow(base, 2.0)) + Math.pow(height, 2.0));
            double RadAngel = Math.asin((base / diagonal));
            double angel = Math.toDegrees(RadAngel);

            if (x >= px && y <= py) {
                player.setRotate(angel);
                gun.setRotate(angel);
                quadrant1 = true;
                if (quadrant1 && quadrant2 && quadrant3 && quadrant4 && !done) {
                    done = true;
                    goToFire();
                }
            } else if (x >= px && y >= py) {
                player.setRotate(180 - angel);
                gun.setRotate(180 - angel);
                quadrant4 = true;
                if (quadrant1 && quadrant2 && quadrant3 && quadrant4 && !done) {
                    done = true;
                    goToFire();
                }
            } else if (x <= px && y <= py) {
                player.setRotate(angel);
                gun.setRotate(angel);
                quadrant2 = true;
                if (quadrant1 && quadrant2 && quadrant3 && quadrant4 && !done) {
                    done = true;
                    goToFire();
                }
            } else {
                player.setRotate(-180 - angel);
                gun.setRotate(-180 - angel);
                quadrant3 = true;
                if (quadrant1 && quadrant2 && quadrant3 && quadrant4 && !done) {
                    done = true;
                    goToFire();
                }

            }
        }

    }
    public  void fire (){



        if(wTue && sTue && dTue && aTue && xMarked && done) {

            Sound.stopMusic();
            SearchAndKill.moving = false;

            Sound.playSoundEffect(5);
            double angel = gun.getRotate();

            gunEffect.setLayoutX(gun.getLayoutX() - 10);
            gunEffect.setLayoutY(gun.getLayoutY() - 10);
            gunEffect.setRotate(angel);
            gunEffect.setVisible(true);

            new HideGunFireEffectThread(this);
            if (!killed) {
                if (isGotHit(angel, player.getLayoutX(), player.getLayoutY(), enemyPlayer.getLayoutX(), enemyPlayer.getLayoutY())) {

                    enemyLifeValue = (enemyLifeValue - 10);
                    if (enemyLifeValue == 0) {

                        enemyPlayer.setVisible(false);
                        enemyGun.setVisible(false);
                        enemyLife.setVisible(false);
                        enemyName.setVisible(false);
                        killed = true;

                        Platform.runLater(() -> {
                            setInstructions("Well Done!!!");
                        });
                        new HideInstructionThread(this);

                    } else {
                        setEnemyLife();
                    }

                }
            }

            if (!mouseClicked) {
                mouseClicked = true;
                goToKill();
            }
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
    public void setInstructions(String instruction) {
        instructionsLabel.setText(instruction);
    }
    public Label getInstructionsLabel(){
        return this.instructionsLabel;
    }

    public Rectangle getInstBox(){
        return  this.instBox;
    }

    public ImageView getGunEffect(){

        return this.gunEffect;
    }
    public ImageView getEnemyPlayer(){
        return  this.enemyPlayer;}

    public ImageView getEnemyGun() {
        return  this.enemyGun;

    }
    public void setEnemyLifeValue(int enemyLifeValue) {
        this.enemyLifeValue = enemyLifeValue;

    }

    public int getEnemyLifeValue() {
        return this.enemyLifeValue;

    }
    public void setEnemyLife() {
        enemyLife.setProgress(enemyLifeValue/100.0);
    }
    public ProgressBar getEnemyLife(){
        return this.enemyLife;
    }
    public void goToFire(){
        setInstructions("Click on mouse to fire");
    }
    public void setKilled(boolean killed){
        this.killed= killed;
    }
    public boolean getKilled(){
        return this.killed;
    }

    public Label getEnemyName() {
        return enemyName;
    }
    public void goToKill(){
        setInstructions("Kill the enemy");
        enemyPlayer.setVisible(true);
        enemyGun.setVisible(true);
        enemyName.setVisible(true);
        enemyLife.setVisible(true);
        enemyLife.setProgress(enemyLifeValue/100.0);

    }
    public int[][] getMapStructure(){
        return  this.mapStructure;
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
}


class HideInstructionThread implements Runnable{

    Thread thread;
    TutorialPageController tc;
    public  HideInstructionThread(TutorialPageController tc){
        this.tc = tc;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public  void run(){
        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException e){}

        tc.getInstructionsLabel().setVisible(false);
        tc.getInstBox().setVisible(false);

    }
}

class HideGunFireEffectThread implements Runnable{

    Thread thread;
    TutorialPageController tc;
    public  HideGunFireEffectThread(TutorialPageController tc){
        this.tc = tc;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public  void run(){
        try {
            Thread.sleep(100);
        }
        catch(InterruptedException e){}

        tc.getGunEffect().setVisible(false);

    }
}
