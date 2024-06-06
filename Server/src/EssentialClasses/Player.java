package EssentialClasses;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player implements Serializable{

    private String name;
    private String password;
    private int kills;
    private int deaths;
    private int matchPlayed;
    private int posX = -100; 
    private int posY = -100;
    private int angel = 0;
    private int isFire = 0;
    private int life = 300;
    private boolean matchEnd;
    private int pp = 0;

    String  avatar;
    String gun;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public Player(){
        this.life = 300;
        this.matchEnd = false;
    }
    public Player(String str){

        String infos[] = str.split(",");
        this.name = infos[0];
        this.password = infos[1];
        this.kills = Integer.parseInt(infos[2]);
        this.deaths = Integer.parseInt(infos[3]);
        this.matchPlayed = Integer.parseInt(infos[4]);
        this.avatar = infos[5];
        this.gun = infos[6];
        this.life = 300;
        this.matchEnd = false;

    }

    public Player(String name,String password){
        this.life = 300;
        this.matchEnd = false;
        this.name = name;
        this.password = password;
        this.kills = 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getKills() {return kills;}
    public void setKills(int kills){ this.kills = kills; }

    public int getDeaths() { return deaths; }
    public void setDeaths(int deaths){ this.deaths = deaths; }
    
    public  int getMatchPlayed(){ return  matchPlayed; }
    public  void  setMatchPlayed(int matchPlayed){ this.matchPlayed = matchPlayed; }
    
    public  String getAvatar(){ return avatar; }
    public  void setAvatar(String avatar){ this.avatar = avatar; }
    
    public  String getGun(){ return gun; }
    public  void  setGun(String gun){ this.gun = gun; }
    
    public ObjectInputStream getOis() {
        return ois;
    }
    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }
    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }
    public int getPosX(){
        return posX;
    }
    public void setPosX(int posX){
        this.posX = posX;
    }
    public int getPosY(){
        return posY;
    }
    public void setPosY(int posY){
        this.posY = posY;
    }
    public int getAngel(){
        return this.angel;
    }
    public void setAngel(int angel){
        this.angel = angel;
    }    
    public int getIsFire(){
        return this.isFire;
    }
    public void setIsFire(int isFire){
        this.isFire = isFire;
    }
    public int getLife(){
        return life;
    }
    public void setLife(int life){
        this.life = life;
    }
    public boolean getMatchEnd(){
        return this.matchEnd;
    }
    public void setMatchEnd(boolean matchEnd){
        this.matchEnd = matchEnd;
    }

    public int getPP(){
        return this.pp;
    }
    public void setPP(){
        this.pp = kills - deaths;
    }
    public boolean equals(Player player){
        if(player.getName().equals(this.name) && player.getPassword().equals(this.password)){
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public String toString(){
        return name+","+ password+","+ kills+","+deaths+","+matchPlayed+","+avatar+","+gun;
    }

}