package it.fi.meucci;

import java.io.IOException;
import java.net.*;

public class ServerMultiThread {
    ServerSocket server;


    public ServerMultiThread(ServerSocket server){
        this.server = server;
    }

    public void serverStart() throws IOException {
        for(;;){
            System.out.println( " Server in attesa di un client " );
            Socket client = server.accept();
            System.out.println("Il client "+client.getLocalAddress()+" si è collegato");
            ServerThread thread = new ServerThread(client);
            thread.start();
        }

    }

}
