package Threads;

import Main.Server;

public class FireThread implements Runnable {

    private Thread thread;
    private int matchIndex;
    private int playerIndex;
    private int victimIndex;

    public FireThread( int matchIndex, int playerIndex, int victimIndex){

        this.matchIndex = matchIndex;
        this.playerIndex = playerIndex;
        this.victimIndex = victimIndex;
        this.thread = new Thread(this);
        this.thread.start();

    }

    @Override
    public void run(){

        if(Server.randomMatchs.get(matchIndex).getMatchPlayers().get(victimIndex).getLife()>0){

            if(Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).getGun().equals("riffel")){
               Server.randomMatchs.get(matchIndex).getMatchPlayers().get(victimIndex).setLife( Server.randomMatchs.get(matchIndex).getMatchPlayers().get(victimIndex).getLife() - 10);
            }
            else{
                Server.randomMatchs.get(matchIndex).getMatchPlayers().get(victimIndex).setLife( Server.randomMatchs.get(matchIndex).getMatchPlayers().get(victimIndex).getLife() - 20);
            }
            if(Server.randomMatchs.get(matchIndex).getMatchPlayers().get(victimIndex).getLife()%100 == 0){
                int tempkills = Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).getKills();
                tempkills++;
                Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).setKills(tempkills);

                int tempDeaths = Server.randomMatchs.get(matchIndex).getMatchPlayers().get(victimIndex).getDeaths();
                tempDeaths++;
                Server.randomMatchs.get(matchIndex).getMatchPlayers().get(victimIndex).setDeaths(tempDeaths);
            }
        }
        
        
    }
    
}
