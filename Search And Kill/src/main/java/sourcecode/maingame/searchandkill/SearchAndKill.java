package sourcecode.maingame.searchandkill;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sourcecode.maingame.EssentialClasses.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SearchAndKill extends Application{

    public static ArrayList<Player> matchPlayers = new ArrayList<>(3);
    public static String[] chats = new String[16];
    public static Player player;
    public static Match match = new Match();
    public static int matchIndex = -1;
    public static int playerIndex = -1;
    public static int mapIndex = -1;
    public static int markedPlayerIndex = 0;
    public static boolean showChat = false;
    public static boolean updateWRoom = false;
    public static boolean updateGamePlayInfoToServer = false;
    public static boolean updateGame = false;
    public static boolean moving = false;
    public static boolean firing = false; 

    public static void main(String[] args)throws IOException{
        Sound sound = new Sound();
        Socket socket = new Socket("192.168.0.105",33333);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        player = new Player();
        player.setOos(oos);
        player.setOis(ois);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("logInPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
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
        stage.setScene(scene);
        stage.setTitle("Search and kill");
        Image icon  =  new Image(getClass().getResourceAsStream("icon.png"));
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.show();

    }
    
}
