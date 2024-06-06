package sourcecode.maingame.EssentialClasses;


import java.util.ArrayList;

public class Match {

    private ArrayList<Player> matchPlayers = new ArrayList<>();
    private Map map;


    public void setMap(Map map){
        this.map = map;
    }
    public  Map getMap(){
        return  this.map;
    }
    public  void setMatchPlayers(ArrayList<Player> matchPlayers){
        this.matchPlayers = matchPlayers;
    }
    public ArrayList<Player> getMatchPlayers(){
        return  matchPlayers;
    }
}
