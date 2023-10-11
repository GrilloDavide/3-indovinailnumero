package it.fi.meucci;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client2 {
    public static void main( String[] args ) throws IOException {

        Socket mioSocket = new Socket( InetAddress.getLocalHost() , 6789);

        DataOutputStream outServer = new DataOutputStream(mioSocket.getOutputStream());
        Scanner inServer = new Scanner (new InputStreamReader(mioSocket.getInputStream()));

        System.out.println( " Connessione effettuata " );

        Scanner inputTastiera = new Scanner(System.in);
        String output = " ";
        String inputString = " ";

        System.out.println("[SERVER] "+inServer.nextLine());
        System.out.println("[SERVER] "+inServer.nextLine());

        do{
            System.out.print("[TU] ");output = inputTastiera.nextLine();
            outServer.writeBytes(output + "\n");

            System.out.println("[SERVER] "+inServer.nextLine());
            inputString = inServer.nextLine();
            System.out.println("[SERVER] "+inputString);

        }while((!inputString.equals("Termine comunicazione")));


        mioSocket.close();
        inputTastiera.close();
    }

}