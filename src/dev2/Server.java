package dev2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Server extends Thread {
    private static int nbClients = 0;
    private static int secretNumber;

    @Override
    public void run() {
        try {
            System.out.println("J'attends une connexion");
            ServerSocket ss = new ServerSocket(4321);
            Random random = new Random();

            while (true) {
                Socket s = ss.accept();
                System.out.println("Client connecté.");
                ++nbClients;
                secretNumber = random.nextInt(100) + 1;
                new ClientHandler(s, nbClients, secretNumber).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().start();  // Démarrer le serveur dans un thread séparé
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private int clientId;
    private int secretNumber;

    public ClientHandler(Socket socket, int clientId, int secretNumber) {
        this.clientSocket = socket;
        this.clientId = clientId;
        this.secretNumber = secretNumber;
    }

    @Override
    public void run() {
        try {
            Scanner clientInput = new Scanner(clientSocket.getInputStream());
            PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            serverOutput.println("Bienvenue ! Devinez le nombre entre 1 et 100.");

            boolean correctGuess = false;

            while (!correctGuess) {
                int guess = clientInput.nextInt();
                System.out.println("Client " + clientId + " a deviné : " + guess);

                if (guess < secretNumber) {
                    serverOutput.println("Plus grand");
                } else if (guess > secretNumber) {
                    serverOutput.println("Plus petit");
                } else {
                    serverOutput.println("Correct");
                    correctGuess = true;
                }
            }

            System.out.println("Fermeture de la connexion avec le client " + clientId + ".");
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
