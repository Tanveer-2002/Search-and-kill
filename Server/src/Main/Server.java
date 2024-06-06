package Main;
import EssentialClasses.Player;
import EssentialClasses.Match;
import Threads.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class Server{
    
    public static ArrayList<Player> allConnectedPlayerList = new ArrayList<Player>();
    public static ArrayList<Match> randomMatchs = new ArrayList<>();
    public static String[] chats = new String[16];

    public static void main(String[] args) throws IOException{

        for(int i= 0; i<chats.length; i++){
            chats[i] = " ";
        }
    
        ServerSocket serverSocket =  new ServerSocket(33333);
        System.out.println("Sever Started: ");

        while(true)
        {
            Socket socket = serverSocket.accept();
            System.out.println("Cleint Connected:");

            new ServerThread(socket);
        
        }
    }
    
}



class ServerThread implements Runnable{

    Socket socket;
    Thread thread;

    public ServerThread(Socket socket){
        this.socket = socket;
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){
        ObjectInputStream ois;
        ObjectOutputStream oos;

        String name = "User";
        try{
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            
            while (socket.isConnected()) {
                    
                Object obj = ois.readObject();
                String playerinfo = (String)obj;
                //System.out.println(playerinfo);
                String[] infos = playerinfo.split(",");
                name = infos[1];

                switch (infos[0]) {
                    case "signup":{
                        new PlayerSignupThread(playerinfo, oos, ois);
                    }
                    break;
                    case "login":{
                        new PlayerLogInThread(playerinfo, oos, ois);                        
                    }     
                    break;
                    case "logout":
                    {
                        new  PlayerLogOutThread(playerinfo, oos, ois);
                    }
                    break;
                    case "delet":{
                        new PlayerDeletThread(playerinfo, oos, ois);
                    }
                    break; 
                    case "getchat":{
                        new GlobalChatGetterThread(oos);
                    }   
                    break;
                    case "addchat":{
                        new GlobalChatWriterThread(infos[1], infos[2]);
                    }
                    break;
                    case "update" :{
                        new UpdateNameAndPassThread(playerinfo, oos, ois);
                    }
                    break;
                    case "editavt":{
                        new AvatarEditThread(playerinfo,oos,ois);
                    }
                    break;
                    case "editgun":{
                        new GunEditThread(playerinfo, oos, ois);
                    }
                    break;
                    case "joinRndMatch" :{
                        Player player = new Player(infos[1],infos[2]);
                        int index = playerExist(player, Server.allConnectedPlayerList);

                        new JoinRandomMatchThread(index);
                    }
                    break;
                    case "getWRoomInfo":{
                        int matchIndex = Integer.parseInt(infos[2]);
                        int playerIndex = Integer.parseInt(infos[3]);

                        new WRoomInfoGetterThread(matchIndex, playerIndex);
                    }
                    break;
                    case "updateposition":{
                        
                        int matchIndex = Integer.parseInt(infos[2]);
                        int playerIndex = Integer.parseInt(infos[3]);
                        int x = Integer.parseInt(infos[4]);
                        int y = Integer.parseInt(infos[5]);
                        int angel = Integer.parseInt(infos[6]);
                        int isFire = Integer.parseInt(infos[7]);

                        new UpdatePositonThread(matchIndex, playerIndex, x, y, angel,isFire);
                    }
                    break;
                    case "getGamePlayInfo":{
                        new RndGamePlayInfoGetterThread(Integer.parseInt(infos[2]), Integer.parseInt(infos[3]));
                    }
                    break;
                    case "getmatchresult":{
                        
                        int matchIndex = Integer.parseInt(infos[2]);
                        int playerIndex = Integer.parseInt(infos[3]);

                        new MatchResultGetterThread(matchIndex,playerIndex);

                    } 
                    break;
                    case "fire":{
                        int matchIndex = Integer.parseInt(infos[2]);
                        int playerIndex = Integer.parseInt(infos[3]);
                        int victimIndex = Integer.parseInt(infos[4]);

                        new FireThread(matchIndex,playerIndex,victimIndex);

                    }
                    break;
                    case "getallplayersName":{
                        
                        Player p = new Player(infos[1],infos[2]);
                        int pIndex = playerExist(p, Server.allConnectedPlayerList);

                        new AllNameGatteThread(pIndex);

                    }
                    break;
                    case "getOthersInfo":{
                        
                        Player p = new Player(infos[1],infos[2]);
                        int pIndex = playerExist(p, Server.allConnectedPlayerList);

                        new OthersInfoGatterThead(Server.allConnectedPlayerList.get(pIndex),infos[3]);

                    }
                    break;
                    default:{
                        System.out.println("ulalalluala");
                        System.out.println(infos[0]);
                    }
                }
                       
            }
            
        }
        catch(IOException e){
            
            System.out.println(name+" Disconnected:");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        for(int i = 0; i<Server.allConnectedPlayerList.size(); i++){
            if(Server.allConnectedPlayerList.get(i).getName().equals(name)){
                Server.allConnectedPlayerList.remove(i);
                break;
            }
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

}