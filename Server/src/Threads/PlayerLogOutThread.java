package Threads;
import EssentialClasses.*;
import Main.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PlayerLogOutThread implements Runnable{
    private String[] infos;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Thread thread;

    public PlayerLogOutThread(String playerinfo, ObjectOutputStream oos,ObjectInputStream ois){
        this.infos = playerinfo.split(",");
        this.ois = ois;
        this.oos = oos;
        this.thread = new Thread(this);
        thread.start();
    }
    
    public void run(){

        int index = -1;
        
        if((index = playerExist(new Player(infos[1], infos[2]), Server.allConnectedPlayerList)) != -1){
            try{
                Server.allConnectedPlayerList.remove(index);
                oos.writeObject("ok");
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            try{
            oos.writeObject("no");
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
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