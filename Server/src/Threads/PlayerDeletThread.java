package Threads;
import EssentialClasses.Player;
import Main.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.ArrayList;

public class PlayerDeletThread implements Runnable {

    private String[] infos;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Thread thread;

    public PlayerDeletThread(String playerinfo, ObjectOutputStream oos,ObjectInputStream ois){
        this.infos = playerinfo.split(",");
        this.ois = ois;
        this.oos = oos;
        this.thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run(){
        
        try{

            BufferedReader reader = new BufferedReader(new FileReader("src\\resource\\allPlayersNameList.txt"));

            String playersName = "empty";
            int lineCount = 0;
            boolean playerExist = false;
            String allPlayerNameList = "";
            while ((playersName = reader.readLine())!= null) {
                
                if(infos[1].equals(playersName)){
                    playerExist = true;
                }
                else{
                    if(lineCount == 0){
                        allPlayerNameList += playersName;
                        lineCount++;
                    }
                    else{
                        allPlayerNameList += "\n"+playersName;
                    }
                }
               
            }
            reader.close();
            
            if(playerExist){

                int index = -1;
                if((index = playerExist(new Player(infos[1], infos[2]),Server.allConnectedPlayerList)) != -1){
                    
                    Server.allConnectedPlayerList.remove(index);
                }

                File playerfile = new File("src\\resource\\players\\"+infos[1]+".txt");
                playerfile.delete();

                BufferedWriter writer = new BufferedWriter(new FileWriter("src\\resource\\allPlayersNameList.txt"));
                writer.write(allPlayerNameList);
                writer.close();

                oos.writeObject("Delet successful");
            }
            else{
                oos.writeObject("This player dosen't exist");
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static int playerExist(Player player, ArrayList<Player> players){
            for(int i =0 ; i<players.size(); i++){
                if(player.equals(players.get(i))){
                    return i;
                }

            }
            return -1;

        }

        
    
}