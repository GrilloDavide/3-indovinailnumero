package it.fi.meucci;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerMultiThread MyServer = new ServerMultiThread(new ServerSocket(6789));

        MyServer.serverStart();
    }
}