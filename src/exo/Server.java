package exo;

import java.io.*;
import java.net.*;
public class Server {
    public static void main (String[] args){
        try{
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("j'attend la connexion d'un client");
            Socket clientSocket=ss.accept();
            System.out.println("Nouveau client connecté");
            System.out.println("génération d'objet inpstrean et OutpusStream de la socket");
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            System.out.println("j'attend un nombre (1 octet)!");
            int nb = is.read();
            System.out.println("j'envoie la réponse");
            os.write(nb*5);
            System.out.println("déconnexion di client");
            clientSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
