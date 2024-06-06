package Threads;

import java.io.BufferedReader;
import java.io.FileReader;

import EssentialClasses.Player;
import Main.Server;

public class OthersInfoGatterThead implements Runnable {
    
    private Player p;
    private Thread thread;
    private String name;

    public OthersInfoGatterThead(Player p, String name){
        this.p = p;
        this.name = name;
        this.thread = new Thread(this);
        this.thread.start();
    }
    
    @Override
    public void run(){

        try{

           BufferedReader reader = new BufferedReader(new FileReader("src\\resource\\players\\"+name+".txt"));
                String playerInfos = reader.readLine();
                reader.close();
            p.getOos().writeObject(playerInfos);
        }
        catch(Exception e){}


    }
    
}
