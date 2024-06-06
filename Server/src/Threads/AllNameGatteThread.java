package Threads;

import Main.Server;

public class AllNameGatteThread implements Runnable{

    private Thread thread;
    private int pIndex;

    public AllNameGatteThread(int pIndex){
        this.pIndex = pIndex;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run(){
        
        String s = "";

        for(int i = 0; i<Server.allConnectedPlayerList.size(); i++){
            s+=Server.allConnectedPlayerList.get(i).getName();
            s+=",";
        }

        try{
            Server.allConnectedPlayerList.get(pIndex).getOos().writeObject(s);
        }
        catch(Exception e){}

    }
    
}
