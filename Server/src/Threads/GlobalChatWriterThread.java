package Threads;
import Main.Server;

public class GlobalChatWriterThread implements Runnable{

    Thread thread ;
    String name;
    String chat;

    public GlobalChatWriterThread(String name , String chat)
    {
        this.name = name;
        this.chat = chat;
        this.thread = new Thread(this);
        this.thread.start();
    }
    
    @Override
    public void run(){

        for(int i = 0; i<Server.chats.length-2; i++){

            Server.chats[i]=Server.chats[i+2];
        }

        Server.chats[14] = name;
        Server.chats[15] = chat;
       
    }
}
