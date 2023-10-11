package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{

    ServerSocket server;
    Socket client;
    BufferedReader inClient;
    DataOutputStream outClient;

    public ServerThread(Socket client) throws IOException {
        this.client = client;
        inClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outClient = new DataOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {
        try{
           startComm();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startComm() throws IOException {

        int inputNumber;
        int randomNumber = (int)(Math.random() * 1000);
            outClient.writeBytes("--INDOVINA IL NUMERO--\n");
            //outClient.writeBytes("  inserire un numero da 1 a 1000"+" il numero e "+randomNumber+"\n"); utilizzato in debug
        outClient.writeBytes("  inserire un numero da 1 a 1000\n");
        for(;;){
            inputNumber = Integer.parseInt(inClient.readLine());

            System.out.println("[CLIENT] "+ randomNumber);
            if(inputNumber == randomNumber ){
                outClient.writeBytes("Hai indovinato!\n");
                break;
            }else{
                outClient.writeBytes("Sbagliato, riprova!\n");
            }

        }

        System.out.println("Termine connessione con "+client.getLocalAddress());
        client.close();
    }
}
