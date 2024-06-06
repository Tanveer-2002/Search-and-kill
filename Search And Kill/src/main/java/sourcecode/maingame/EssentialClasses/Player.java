package sourcecode.maingame.EssentialClasses;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player implements Serializable{

    private String name;
    private String password;
    private int kills;
    private int deaths;
    private  int matchPlayed;
    private int posX;
    private int posY;
    private  int life;
    private int startX;
    private int startY;

    private int angel;

    String  avatar;
    String gun;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public Player(){
        this.life =300;
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

    }

    public Player(String name,String password){
        this.name = name;
        this.password = password;
        this.kills = 0;
        this.life = 300;
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

    public boolean equals(Player player){
        if(player.getName().equals(this.name) && player.getPassword().equals(this.password)){
            return true;
        }
        else{
            return false;
        }
    }

    public int getLife(){
        return this.life;
    }
    public void setLife(int life){
        this.life = life;
    }

    public int getStartX(){
        return this.startX;
    }
    public void setStartX(int x){
        this.startX = x;
    }
    public int getStartY(){
        return this.startY;
    }
    public void setStartY(int y){
        this.startY = y;
    }

    @Override
    public String toString(){
        return name+","+ password+","+ kills+","+deaths+","+matchPlayed+","+avatar+","+gun;
    }
    


}