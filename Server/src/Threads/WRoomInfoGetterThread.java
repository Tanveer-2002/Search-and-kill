package Threads;
import java.io.IOException;

import Main.Server;

public class WRoomInfoGetterThread implements Runnable{
    private Thread thread;
    private int matchIndex;
    private int playerIndex;
   
    public WRoomInfoGetterThread(int matchIndex ,int playerIndex){
        this.matchIndex = matchIndex;
        this.playerIndex = playerIndex;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run(){

        String wRPInfos ="WRPInfos,";
        for(int i = 0; i<Server.randomMatchs.get(matchIndex).getMatchPlayers().size(); i++){

            wRPInfos += Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getName()+","+Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getAvatar()+","+Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getGun();            
         
            if(i < Server.randomMatchs.get(matchIndex).getMatchPlayers().size()-1 ){
                wRPInfos +=",";
            }

        }

        try{
            Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).getOos().writeObject(wRPInfos);
            System.out.println(wRPInfos);
        
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }


    }
    
}
