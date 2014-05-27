package classSlick;
import java.net.*;
import java.io.*;

public class MyClient {
    
    public void clientConnection(String command){
        String serverName="127.0.0.1";
        int portNum=6000;
        Socket clientSocket;
        PrintWriter clientOut;
        
        try{
            clientSocket=new Socket(serverName, portNum);//create client's socket
            clientOut=new PrintWriter(clientSocket.getOutputStream(), true);//get the client's output stream
            clientOut.print(command);//this is what is sent to the server's input stream via output stream
            clientOut.close();//close the printwriter
            clientSocket.close();//close socket
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
