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

        String inputClient;
        int randomNumber = (int)(Math.random() * 1000);
            outClient.writeBytes("--INDOVINA IL NUMERO--\n");
            //outClient.writeBytes("  inserire un numero da 1 a 1000"+" il numero e "+randomNumber+"\n"); utilizzato in debug
        outClient.writeBytes("  inserire un numero da 1 a 1000, per terminare la comunicazione inviare 'TERMINA'\n");

        for(;;){
            inputClient = inClient.readLine();
            int inputNumber = 0;

            System.out.println("[CLIENT "+client.getPort()+"] "+ inputClient);

            if(inputClient.equals("TERMINA")){
                outClient.writeBytes("La comunicazione verra' terminata\n");
                break;
            }

            try {
                inputNumber = Integer.parseInt(inputClient); //per gestire l'inserimento di caratteri
                if(inputNumber == randomNumber ) {
                    outClient.writeBytes("Hai indovinato!\n");
                    break;
                }
                outClient.writeBytes("Sbagliato!\n");
                if(inputNumber < randomNumber){
                    outClient.writeBytes("Il numero da indovinare e' piu' grande\n");
                }else
                    outClient.writeBytes("Il numero da indovinare e' piu' piccolo\n");

            } catch (Exception e) {

                outClient.writeBytes("Errore:\n");
                outClient.writeBytes("Non e' stato inviato un numero. Se si vuole terminare la comunicazione, inviare 'TERMINA'\n");
            }


        }

        outClient.writeBytes("Termine comunicazione\n");
        System.out.println("Termine connessione con "+client.getPort());
        client.close();
    }
}
