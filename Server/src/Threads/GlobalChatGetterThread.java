package Threads;
import Main.Server;
import java.io.IOException;
import java.io.ObjectOutputStream;
public class GlobalChatGetterThread implements Runnable  {

    Thread thread ;
    ObjectOutputStream oos;
    public GlobalChatGetterThread(ObjectOutputStream oos){
        this.oos = oos;
        this.thread = new Thread(this);
        this.thread.start();
    }


    @Override
    public void run(){
        try{
            oos.writeObject(Server.chats[0]+","+Server.chats[1]+","+Server.chats[2]+","+Server.chats[3]+","+Server.chats[4]+","+Server.chats[5]+","+Server.chats[6]+","+Server.chats[7]+","+Server.chats[8]+","+Server.chats[9]+","+Server.chats[10]+","+Server.chats[11]+","+Server.chats[12]+","+Server.chats[13]+","+Server.chats[14]+","+Server.chats[15]);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
    
}
