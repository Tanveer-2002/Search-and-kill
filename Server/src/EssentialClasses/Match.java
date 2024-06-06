package EssentialClasses;

import java.util.ArrayList;

public class Match {

    private ArrayList<Player> matchPlayers = new ArrayList<>();
    private int map;
    
    public Match(int map){
        this.map = map;
    }

    public ArrayList<Player> getMatchPlayers(){
        return matchPlayers;
    }
    public int getMap(){
        return map;
    }
    
}
