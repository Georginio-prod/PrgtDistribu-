package exo;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    public static void main (String[] args){
        try(Socket clientSocket = new Socket("localhost",1234))
        {
            System.out.println("créer une connexion au server");
            System.out.println("génération d'objet inpstrean et OutpusStream de la socket");
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            System.out.println("lire un nombre au clavier NB=");
            Scanner clavier= new Scanner(System.in);
            int nb = clavier.nextInt();
            System.out.println("Envoyer le nombre " +nb+ " au serveur");
            os.write(nb);
            System.out.println("Attendre la réponse du serveur");
            int rep= is.read();
            System.out.println("la réponse est:" +rep);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

