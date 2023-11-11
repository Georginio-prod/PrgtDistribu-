package dev;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Serveur en attente de connexion...");

        // Générer un nombre aléatoire entre 1 et 100 inclus
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;

        System.out.println("Le nombre secret est : " + secretNumber);

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connecté.");

        Scanner clientInput = new Scanner(clientSocket.getInputStream());
        PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);

        serverOutput.println("Bienvenue ! Devinez le nombre entre 1 et 100.");

        boolean correctGuess = false;

        while (!correctGuess) {
            int guess = clientInput.nextInt();
            System.out.println("Client a deviné : " + guess);

            if (guess < secretNumber) {
                serverOutput.println("Plus grand");
            } else if (guess > secretNumber) {
                serverOutput.println("Plus petit");
            } else {
                serverOutput.println("Correct");
                correctGuess = true;
            }
        }

        System.out.println("Fermeture de la connexion.");
        serverSocket.close();
        clientSocket.close();
    }
}





