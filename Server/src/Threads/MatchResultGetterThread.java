package Threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Main.Server;

public class MatchResultGetterThread implements Runnable{

    private Thread thread;
    private int matchIndex;
    private int playerIndex;

    public MatchResultGetterThread(int matchIndex, int playerIndex){

        this.matchIndex = matchIndex;
        this.playerIndex = playerIndex;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run(){

        String matchresult ="";
        int []pps = new int[3];

        for(int i = 0; i<Server.randomMatchs.get(matchIndex).getMatchPlayers().size(); i++){

            Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).setPP();
            pps[i] = Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getPP();

            if(i == playerIndex){
                try{
                    BufferedReader reader = new BufferedReader(new FileReader("src\\resource\\players\\"+Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getName()+".txt"));
                    String playerInfos = reader.readLine();
                    reader.close();
                    String[] playersData = playerInfos.split(",");
                    int newKillNumber =  Integer.parseInt(playersData[2])+ Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getKills();
                    int newDeath = Integer.parseInt(playersData[2])+ Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getDeaths();
                    int newMatchPlayed = Integer.parseInt(playersData[4])+1;
                    playerInfos = playersData[0]+","+playersData[1]+","+newKillNumber+","+newDeath+","+newMatchPlayed+","+playersData[5]+","+playersData[6];
                    
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\resource\\players\\"+Server.randomMatchs.get(matchIndex).getMatchPlayers().get(i).getName()+".txt"));
                    writer.write(playerInfos);
                    writer.close();
                }
                catch(IOException e){}
            }

           
        }

        int []sortedIndexs = {0,1,3};

        if(pps[0]>=pps[1] && pps[0]>=pps[2]){
            sortedIndexs[0] = 0;
            if(pps[1]>= pps[2]){
                sortedIndexs[1] = 1;
                sortedIndexs[2] = 2;
            }
            else{
                sortedIndexs[1] = 2;
                sortedIndexs[2] = 1;
            }
        }
        else if(pps[1]>=pps[0] && pps[1]>=pps[2]){
            sortedIndexs[0]= 1;
            if(pps[0]>= pps[2]){
                sortedIndexs[1] = 0;
                sortedIndexs[2] = 2;
            }
            else{
                sortedIndexs[1] = 2;
                sortedIndexs[2] = 0;
            }
        }
        else{
            sortedIndexs[0]= 2;
            if(pps[0]>= pps[1]){
                sortedIndexs[1] = 0;
                sortedIndexs[2] = 1;
            }
            else{
                sortedIndexs[1] = 1;
                sortedIndexs[2] = 0;
            }
        }

       

        for(int i = 0; i<Server.randomMatchs.get(matchIndex).getMatchPlayers().size(); i++){

            matchresult += Server.randomMatchs.get(matchIndex).getMatchPlayers().get(sortedIndexs[i]).getName();
            matchresult += ",";
            matchresult += Integer.toString(Server.randomMatchs.get(matchIndex).getMatchPlayers().get(sortedIndexs[i]).getDeaths());
            matchresult += ",";
            matchresult += Integer.toString(Server.randomMatchs.get(matchIndex).getMatchPlayers().get(sortedIndexs[i]).getKills());
            
            if(i != Server.randomMatchs.get(matchIndex).getMatchPlayers().size()-1){
                matchresult += ",";
            }
        }

        try{

            Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).getOos().writeObject(matchresult);
            
        }
        catch (IOException e){}
    }
    
}
