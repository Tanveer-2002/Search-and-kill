package Threads;
import EssentialClasses.Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Main.Server;;

public class AvatarEditThread implements Runnable {

    private String[] infos;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Thread thread;

    public AvatarEditThread(String playerinfo, ObjectOutputStream oos,ObjectInputStream ois){
        this.infos = playerinfo.split(",");
        this.ois = ois;
        this.oos = oos;
        this.thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run(){

        try{
            BufferedReader reader = new BufferedReader(new FileReader("src\\resource\\players\\"+infos[1]+".txt"));
            String allData = reader.readLine();
            reader.close();

            String[] datas = allData.split(",");
            datas[5] = infos[3];

            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\resource\\players\\"+infos[1]+".txt"));
            writer.write(datas[0]+","+datas[1]+","+datas[2]+","+datas[3]+","+datas[4]+","+datas[5]+","+datas[6]);
            writer.close();

            int playerIndex = playerExist(new Player(infos[1],infos[2]), Server.allConnectedPlayerList);

            Server.allConnectedPlayerList.get(playerIndex).setAvatar(infos[3]);

            oos.writeObject("ok");
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
