package Threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PlayerSignupThread implements Runnable{

    private String[] infos;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Thread thread;
    public PlayerSignupThread(String playerinfo, ObjectOutputStream oos,ObjectInputStream ois){
        this.infos = playerinfo.split(",");
        this.ois = ois;
        this.oos = oos;
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){
        try{

            BufferedReader reader = new BufferedReader(new FileReader("src\\resource\\allPlayersNameList.txt"));
            String playersName = "empty";
            boolean playerExits = false;
            int lineCount = 0;
        
            while ((playersName = reader.readLine())!= null) {

                lineCount++;
                if(playersName.equals(infos[1])){

                    playerExits = true;
                    reader.close();
                    break;
                }
            }

            if(!playerExits){

                BufferedWriter playersNameListWriter = new BufferedWriter(new FileWriter("src\\resource\\allPlayersNameList.txt",true));
                if(lineCount == 0){
                    playersNameListWriter.write(infos[1]);
                }
                else{
                    playersNameListWriter.write("\n"+infos[1]);
                }
                playersNameListWriter.close();

                BufferedWriter playerWriter = new BufferedWriter(new FileWriter("src\\resource\\players\\"+infos[1]+".txt"));
                playerWriter.write(infos[1]+","+infos[2]+",0,0,0,Mogambo,riffel");
                playerWriter.close();
                oos.writeObject("ok");

            }
            else{
                oos.writeObject("no");
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    
}
