package Threads;

import Main.Server;

public class UpdateRandomMatchDataThread implements Runnable {
    
    private Thread thread;
    private int matchIndex;
    private int playerIndex;
    private int x;
    private int y;
   
    public UpdateRandomMatchDataThread(int matchIndex, int playerIndex, int x, int y){
        this.matchIndex = matchIndex;
        this.playerIndex = playerIndex;
        this.x = x;
        this.y = y;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run(){
        Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).setPosX(x);
        Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).setPosY(y);
        System.out.println(Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).getPosX() + Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).getPosY());
     
    }

    
}
