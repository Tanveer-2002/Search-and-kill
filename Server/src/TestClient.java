import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) throws IOException{
        System.out.println("Client Started:");
        Socket socket = new Socket("127.0.0.1",33333);
        System.out.println("Cleint Connected:");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        
        Scanner sc = new Scanner(System.in);
        while (true) {

        System.out.println("press 1 for login:");
        System.out.println("Prass 2 for logout");
        System.out.println("press 3 for sign-up");
        System.out.println("press 4 to delet");
        int op = sc.nextInt();
        
        if(op == 1){
            sc.nextLine();
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc. nextLine();

            oos.writeObject("login,"+name+","+password);   
              

        }
        else if(op == 2){
            sc.nextLine();
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc. nextLine();
                            
            oos.writeObject("logout,"+name+","+password); 
                          
         }       
        else if(op ==3){
            sc.nextLine();
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc. nextLine();
        
            oos.writeObject("signup,"+name+","+password);  
           
            }
        else if (op == 4){
            sc.nextLine();
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc. nextLine();
        
            oos.writeObject("delet,"+name+","+password);  
            

        }    
        
            
            try{
                Object ob = ois.readObject();
                String str = (String)ob;
                System.out.println(str);
            }
            catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }  
                    
        }

    }
    
}



