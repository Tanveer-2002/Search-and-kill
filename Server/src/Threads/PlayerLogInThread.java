package Threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import EssentialClasses.Player;
import Main.Server;

public class PlayerLogInThread implements Runnable{

    private String[] infos;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Thread thread;
    public PlayerLogInThread(String playerinfo, ObjectOutputStream oos,ObjectInputStream ois){
        this.infos = playerinfo.split(",");
        this.ois = ois;
        this.oos = oos;
        this.thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run(){
        if(playerExist(new Player(infos[1],infos[2]), Server.allConnectedPlayerList) == -1){

            BufferedReader reader;
            try{

                reader = new BufferedReader(new FileReader("src\\resource\\players\\"+infos[1]+".txt"));
                
                String sinfos = reader.readLine();
                String[] serverPlayerInfos = sinfos.split(",");

                if(serverPlayerInfos[0].equals(infos[1]) && serverPlayerInfos[1].equals(infos[2])){

                    Player player = new Player(sinfos);
                    
                    player.setOis(ois);
                    player.setOos(oos);
                        
                    Server.allConnectedPlayerList.add(player);

                    oos.writeObject("ok,"+sinfos);
                    reader.close();

                }
               else{
                    oos.writeObject("no");
                }

            }
            catch(IOException e){
                try{
                oos.writeObject("no");
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            }

        }
        else{
            try{
                oos.writeObject("okbut");
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