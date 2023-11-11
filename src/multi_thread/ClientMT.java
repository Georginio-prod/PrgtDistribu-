package multi_thread;

import dev.Client;

import java.io.*;
import java.net.*;


public class ClientMT extends Thread {
    Socket client;
    int numero;
    public ClientMT(Socket s, int num){
        client=s;
        numero=num;
    }
    public void num (){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            System.out.println("Client num" + numero);
            System.out.println("Vous etes le client num" + numero);
            while (true) {
                String s = in.readLine();
                System.out.println("Votre commande contient" + s.length() + "caractères");

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        }
    }


