package Threads;
import EssentialClasses.Player;
import Main.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UpdateNameAndPassThread implements Runnable{

    private String[] infos;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Thread thread;

    public UpdateNameAndPassThread(String playerinfo, ObjectOutputStream oos,ObjectInputStream ois){
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
            String listNames = "empty";
            String allPlayers = "";
            boolean playerExist = false;
            int line = 0;
            
            while ((listNames = reader.readLine())!= null) {
                if(line == 0){
                    line ++;
                    if(listNames.equals(infos[1])){
                        allPlayers+=infos[3];
                    }
                    else{
                        allPlayers += listNames;
                    }
                }
                else{
                    allPlayers += "\n";
                    if(listNames.equals(infos[1])){
                        allPlayers+=infos[3];
                    }
                    else{
                        allPlayers += listNames;
                    }
                }
                
                if(!infos[1].equals(infos[3])&& infos[3].equals(listNames)){
                    playerExist = true;
                    break;
                }
                
            }
            reader.close();
            
            if(!playerExist){

                BufferedWriter writer = new BufferedWriter(new FileWriter("src\\resource\\allPlayersNameList.txt"));
                writer.write(allPlayers);
                writer.close();

                BufferedReader playReader = new BufferedReader(new FileReader("src\\resource\\players\\"+infos[1]+".txt"));
                String playerInfos = playReader.readLine();
                playReader.close();

                String[] playerInfoData = playerInfos.split(",");
                String newPlayerData = infos[3]+","+infos[4]+","+ playerInfoData[2]+","+playerInfoData[3]+","+playerInfoData[4]+","+playerInfoData[5]+","+playerInfoData[6];

                File playerfile = new File("src\\resource\\players\\"+infos[1]+".txt");
                playerfile.delete();

                BufferedWriter playWriter = new BufferedWriter(new FileWriter("src\\resource\\players\\"+infos[3]+".txt"));
                playWriter.write(newPlayerData);
                playWriter.close();
            
                int playerIndex = playerExist(new Player(infos[1],infos[2]), Server.allConnectedPlayerList);
                Server.allConnectedPlayerList.get(playerIndex).setName(infos[3]);
                Server.allConnectedPlayerList.get(playerIndex).setPassword(infos[4]);
                oos.writeObject("ok,"+newPlayerData);

            }
            else{
                oos.writeObject("no,");
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
