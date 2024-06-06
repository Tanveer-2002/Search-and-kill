package Threads;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import EssentialClasses.*;
import Main.Server;



public class JoinRandomMatchThread implements Runnable{

    private Thread thread;
    private int index;
    
    public JoinRandomMatchThread(int index){
        this.index = index;
        this.thread = new Thread(this);
        this.thread.start();

    }

    @Override
    public void run(){
       
        int map=0;
        Random random = new Random();
        int matchNumber = Server.randomMatchs.size();
        int playerNumber = 0;

        if(matchNumber == 0){

            map = random.nextInt(3);
            Server.randomMatchs.add(new Match(map));

            Player p = Server.allConnectedPlayerList.get(index);
            Server.randomMatchs.get(matchNumber).getMatchPlayers().add(p);
            Server.randomMatchs.get(matchNumber).getMatchPlayers().get(0).setKills(0);
            Server.randomMatchs.get(matchNumber).getMatchPlayers().get(0).setDeaths(0);
            Server.randomMatchs.get(matchNumber).getMatchPlayers().get(0).setLife(300);

            int []positon = getRandPositoin(Server.randomMatchs.get(0 ), 0);

            Server.randomMatchs.get(matchNumber).getMatchPlayers().get(0).setPosX(positon[0]);
            Server.randomMatchs.get(matchNumber).getMatchPlayers().get(0).setPosY(positon[1]); 
        }
        else{
            
            boolean playerJoind = false;

            for(int i = 0; i<Server.randomMatchs.size(); i++){

                if(Server.randomMatchs.get(i).getMatchPlayers().size() < 3){
                    
                    playerNumber  = Server.randomMatchs.get(i).getMatchPlayers().size();

                    Player p = Server.allConnectedPlayerList.get(index);
                    Server.randomMatchs.get(i).getMatchPlayers().add(p);
                    Server.randomMatchs.get(i).getMatchPlayers().get(playerNumber).setKills(0);
                    Server.randomMatchs.get(i).getMatchPlayers().get(playerNumber).setDeaths(0);
                    Server.randomMatchs.get(i).getMatchPlayers().get(playerNumber).setLife(300);
                  
                    int [] positon = getRandPositoin(Server.randomMatchs.get(i), playerNumber);
                    
                    Server.randomMatchs.get(i).getMatchPlayers().get(playerNumber).setPosX(positon[0]);
                    Server.randomMatchs.get(i).getMatchPlayers().get(playerNumber).setPosY(positon[1]); 
                    
                    map = Server.randomMatchs.get(i).getMap();
                    matchNumber = i;
                    playerJoind = true;
                    break;
                }
            }

            
            if(!playerJoind){ 

                map = random.nextInt(3);
                Server.randomMatchs.add(new Match(map));
                Player p = Server.allConnectedPlayerList.get(index);
                Server.randomMatchs.get(matchNumber).getMatchPlayers().add(p);
                Server.randomMatchs.get(matchNumber).getMatchPlayers().get(0).setKills(0);
                Server.randomMatchs.get(matchNumber).getMatchPlayers().get(0).setDeaths(0);
                Server.randomMatchs.get(matchNumber).getMatchPlayers().get(0).setLife(300);
                
                int [] positon = getRandPositoin(Server.randomMatchs.get(matchNumber), 0);

                Server.randomMatchs.get(matchNumber).getMatchPlayers().get(0).setPosX(positon[0]);
                Server.randomMatchs.get(matchNumber).getMatchPlayers().get(0).setPosY(positon[1]);

            }
           

        }

        int x = Server.randomMatchs.get(matchNumber).getMatchPlayers().get(playerNumber).getPosX();
        int y = Server.randomMatchs.get(matchNumber).getMatchPlayers().get(playerNumber).getPosY();
        String reply = Integer.toString(matchNumber)+","+Integer.toString(playerNumber)+","+Integer.toString(map)+","+Integer.toString(x)+","+Integer.toString(y);
        
        try{
            Server.allConnectedPlayerList.get(index).getOos().writeObject(reply);
        }
        catch(IOException e){
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

    public int[] getRandPositoin(Match match,int playerindex){
        int[] positon = new int[2];
        Random random = new Random();
        if(match.getMap() == 0){
            
            while (true) {
                int rndPositon = random.nextInt(5);
                if(rndPositon == 0){
                    
                    positon[0] = 406;
                    positon[1] = 123;

                }
                else if(rndPositon == 1){

                    positon[0] = 388;
                    positon[1] = 548;

                }
                else if(rndPositon == 2){

                    positon[0] = 607;
                    positon[1] = 323;

                }
                else if(rndPositon == 3){

                    positon[0] = 805;
                    positon[1] = 533;

                }
                else{

                    positon[0] = 852;
                    positon[1] = 100;

                }
                
                boolean positionExist = false;
                for(int i=0; i<match.getMatchPlayers().size(); i++){
                    if(i!=playerindex && positon[0] == match.getMatchPlayers().get(i).getPosX() && positon[1] == match.getMatchPlayers().get(i).getPosY()){
                        positionExist = true;
                        break;
                    }
                }

                if(!positionExist){
                    break;
                }

            }
            
        }
        else if(match.getMap() == 1){
           
            while (true) {
                int rndPositon = random.nextInt(5);
                if(rndPositon == 0){
                    
                    positon[0] = 198;
                    positon[1] = 188;

                }
                else if(rndPositon == 1){

                    positon[0] = 331;
                    positon[1] = 551;

                }
                else if(rndPositon == 2){

                    positon[0] = 610;
                    positon[1] = 164;

                }
                else if(rndPositon == 3){

                    positon[0] = 1016;
                    positon[1] = 183;

                }
                else{

                    positon[0] = 890;
                    positon[1] = 558;

                }
                
                boolean positionExist = false;
                for(int i=0; i<match.getMatchPlayers().size(); i++){
                    if(i!=playerindex && positon[0] == match.getMatchPlayers().get(i).getPosX() && positon[1] == match.getMatchPlayers().get(i).getPosY()){
                        positionExist = true;
                        break;
                    }
                }

                if(!positionExist){
                    break;
                }

            }
            

        }
        else{
            while (true) {
                int rndPositon = random.nextInt(5);
                if(rndPositon == 0){
                    
                    positon[0] = 138;
                    positon[1] = 146;

                }
                else if(rndPositon == 1){

                    positon[0] = 293;
                    positon[1] = 414;

                }
                else if(rndPositon == 2){

                    positon[0] = 606;
                    positon[1] = 220;

                }
                else if(rndPositon == 3){

                    positon[0] = 928;
                    positon[1] = 417;

                }
                else{

                    positon[0] = 1092;
                    positon[1] = 128;

                }
                
                boolean positionExist = false;
                for(int i=0; i<match.getMatchPlayers().size(); i++){
                    if(i!=playerindex && positon[0] == match.getMatchPlayers().get(i).getPosX() && positon[1] == match.getMatchPlayers().get(i).getPosY()){
                        positionExist = true;
                        break;
                    }
                }

                if(!positionExist){
                    break;
                }

            }
            

        }

        return positon;
    }
    
}
