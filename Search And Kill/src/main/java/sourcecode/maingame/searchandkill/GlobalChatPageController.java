package sourcecode.maingame.searchandkill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.io.IOException;

public class GlobalChatPageController {


    @FXML
    private Rectangle chatBox1;
    @FXML
    private Rectangle chatBox2;
    @FXML
    private Rectangle chatBox3;
    @FXML
    private Rectangle chatBox4;
    @FXML
    private Rectangle chatBox5;
    @FXML
    private Rectangle chatBox6;
    @FXML
    private Rectangle chatBox7;
    @FXML
    private Rectangle chatBox8;


    @FXML
    private Label chatName1;
    @FXML
    private Label chatName2;
    @FXML
    private Label chatName3;
    @FXML
    private Label chatName4;
    @FXML
    private Label chatName5;
    @FXML
    private Label chatName6;
    @FXML
    private Label chatName7;
    @FXML
    private Label chatName8;

    @FXML
    private Label chatMessage1;
    @FXML
    private Label chatMessage2;
    @FXML
    private Label chatMessage3;
    @FXML
    private Label chatMessage4;
    @FXML
    private Label chatMessage5;
    @FXML
    private Label chatMessage6;
    @FXML
    private Label chatMessage7;
    @FXML
    private Label chatMessage8;


    @FXML
    private TextField myMessage;
    @FXML
    private Label goBackButtonLabel;
    @FXML
    private Button goBackButton;
    @FXML
    private Label status;

    private Parent root;
    private Scene scene;
    private Stage stage;

    public  void sendMessage()throws IOException {
        String message = myMessage.getText();
        if(message.length()<=50) {
            if (!message.equals("")) {
                if (isValidString(message)) {

                    Sound.playSoundEffect(1);

                    SearchAndKill.player.getOos().writeObject("addchat," + SearchAndKill.player.getName() + "," + myMessage.getText());
                    myMessage.setText("");
                    status.setText("");
                }
                else {

                    Sound.playSoundEffect(2);

                    status.setText("A message only can contain (ALPHABETS) (NUMBERS) (.) and (?)");
                }
            }
            else {

                Sound.playSoundEffect(2);

                status.setText("Empty message !!!");
            }
        }
        else {

            Sound.playSoundEffect(2);

            status.setText("A message can contain 50 characters only");
        }
    }

    public void setChats(){

        chatName1.setText(SearchAndKill.chats[0]);
        chatMessage1.setText(SearchAndKill.chats[1]);
        chatName2.setText(SearchAndKill.chats[2]);
        chatMessage2.setText(SearchAndKill.chats[3]);
        chatName3.setText(SearchAndKill.chats[4]);
        chatMessage3.setText(SearchAndKill.chats[5]);
        chatName4.setText(SearchAndKill.chats[6]);
        chatMessage4.setText(SearchAndKill.chats[7]);
        chatName5.setText(SearchAndKill.chats[8]);
        chatMessage5.setText(SearchAndKill.chats[9]);
        chatName6.setText(SearchAndKill.chats[10]);
        chatMessage6.setText(SearchAndKill.chats[11]);
        chatName7.setText(SearchAndKill.chats[12]);
        chatMessage7.setText(SearchAndKill.chats[13]);
        chatName8.setText(SearchAndKill.chats[14]);
        chatMessage8.setText(SearchAndKill.chats[15]);

        if(chatName1.getText().equals(SearchAndKill.player.getName())){
            chatBox1.setLayoutX(230);
            chatName1.setLayoutX(245);
            chatMessage1.setLayoutX(245);

        }
        else {
            chatBox1.setLayoutX(145);
            chatName1.setLayoutX(160);
            chatMessage1.setLayoutX(160);

        }
        if(chatName2.getText().equals(SearchAndKill.player.getName())){
            chatBox2.setLayoutX(230);
            chatName2.setLayoutX(245);
            chatMessage2.setLayoutX(245);

        }
        else {
            chatBox2.setLayoutX(145);
            chatName2.setLayoutX(160);
            chatMessage2.setLayoutX(160);

        }
        if(chatName3.getText().equals(SearchAndKill.player.getName())){
            chatBox3.setLayoutX(230);
            chatName3.setLayoutX(245);
            chatMessage3.setLayoutX(245);

        }
        else {
            chatBox3.setLayoutX(145);
            chatName3.setLayoutX(160);
            chatMessage3.setLayoutX(160);

        }
        if(chatName4.getText().equals(SearchAndKill.player.getName())){
            chatBox4.setLayoutX(230);
            chatName4.setLayoutX(245);
            chatMessage4.setLayoutX(245);

        }
        else {
            chatBox4.setLayoutX(145);
            chatName4.setLayoutX(160);
            chatMessage4.setLayoutX(160);

        }
        if(chatName5.getText().equals(SearchAndKill.player.getName())){
            chatBox5.setLayoutX(230);
            chatName5.setLayoutX(245);
            chatMessage5.setLayoutX(245);

        }
        else {
            chatBox5.setLayoutX(145);
            chatName5.setLayoutX(160);
            chatMessage5.setLayoutX(160);

        }
        if(chatName6.getText().equals(SearchAndKill.player.getName())){
            chatBox6.setLayoutX(230);
            chatName6.setLayoutX(245);
            chatMessage6.setLayoutX(245);

        }
        else {
            chatBox6.setLayoutX(145);
            chatName6.setLayoutX(160);
            chatMessage6.setLayoutX(160);

        }
        if(chatName7.getText().equals(SearchAndKill.player.getName())){
            chatBox7.setLayoutX(230);
            chatName7.setLayoutX(245);
            chatMessage7.setLayoutX(245);

        }
        else {
            chatBox7.setLayoutX(145);
            chatName7.setLayoutX(160);
            chatMessage7.setLayoutX(160);

        }
        if(chatName8.getText().equals(SearchAndKill.player.getName())){
            chatBox8.setLayoutX(230);
            chatName8.setLayoutX(245);
            chatMessage8.setLayoutX(245);

        }
        else {
            chatBox8.setLayoutX(145);
            chatName8.setLayoutX(160);
            chatMessage8.setLayoutX(160);

        }

        if(!chatMessage1.getText().equals(" ")){
            chatBox1.setVisible(true);
        }
        if(!chatMessage2.getText().equals(" ")){
            chatBox2.setVisible(true);
        }
        if(!chatMessage3.getText().equals(" ")){
            chatBox3.setVisible(true);
        }
        if(!chatMessage4.getText().equals(" ")){
            chatBox4.setVisible(true);
        }
        if(!chatMessage5.getText().equals(" ")){
            chatBox5.setVisible(true);
        }
        if(!chatMessage6.getText().equals(" ")){
            chatBox6.setVisible(true);
        }
        if(!chatMessage7.getText().equals(" ")){
            chatBox7.setVisible(true);
        }
        if(!chatMessage8.getText().equals(" ")){
            chatBox8.setVisible(true);
        }


    }

    public void goBack(ActionEvent event) throws IOException {


        Sound.playSoundEffect(1);
        Sound.playMusic(0);

        SearchAndKill.showChat = false;
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

    public boolean isValidString(String str){

        for(int i =0; i<str.length();i++){
            if(!((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') ||(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') || (str.charAt(i) >= '0' && str.charAt(i) <= '9') || (str.charAt(i) == '.') || (str.charAt(i) == '?') || (str.charAt(i) == ' '))){
                return  false;
            }
        }
        return  true;

    }

}


class ChatThread  implements Runnable{

    Thread thread;
    GlobalChatPageController globalChatPageController ;
    public  ChatThread(GlobalChatPageController globalChatPageController){
        this.globalChatPageController = globalChatPageController;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
      try {
          while(SearchAndKill.showChat){

              SearchAndKill.player.getOos().writeObject("getchat"+","+SearchAndKill.player.getName());
              Object getObj = SearchAndKill.player.getOis().readObject();
              String chat = (String) getObj;
              SearchAndKill.chats = chat.split(",");

              Platform.runLater(() -> globalChatPageController.setChats());
              Thread.sleep(15);
          }
      }
      catch (Exception e){
          System.out.println(e.getMessage());
      }


    }


}
