package Threads;
import java.io.IOException;

import Main.Server;

import Main.Server;

public class RndGamePlayInfoGetterThread implements Runnable {

    private Thread thread;
    private int matchIndex;
    private int playerIndex;

    public RndGamePlayInfoGetterThread(int matchIndex, int playerIndex){
        this.matchIndex = matchIndex;
        this.playerIndex = playerIndex;
        this.thread = new Thread(this);
        this.thread.start();
    }
    @Override
    public void run(){
        
        
        String reply ="gamePlayInfos,";

        for(int i=0; i<Server.randomMatchs.get(matchIndex).getMatchPlayers().size(); i++){
    
            reply += Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getPosX();
            reply += ",";
            reply += Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getPosY();
            reply += ",";
            reply += Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getAngel();
            reply += ",";
            reply += Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getLife();
            reply += ",";
            reply += Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getKills();
            reply += ",";
            reply += Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getDeaths();
            
            if(i!= Server.randomMatchs.get(matchIndex).getMatchPlayers().size()-1){
                reply += ",";
            }
        }
        try{
            Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).getOos().writeObject(reply);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
}
