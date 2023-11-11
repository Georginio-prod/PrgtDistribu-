package dev2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client extends Thread {
    private Socket client;
    private int numero;

    public Client(Socket s, int num) {
        client = s;
        numero = num;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            System.out.println("Client num" + numero);
            System.out.println("Vous êtes le client num" + numero);

            while (true) {
                String s = in.readLine();
                System.out.println("Votre commande contient " + s.length() + " caractères");

                // Ajout de la condition pour vérifier si le message est "Correct"
                if (s.equals("Correct")) {
                    System.out.println("Félicitations ! Vous avez gagné !");
                    break;  // Quitter la boucle si le message est "Correct"
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                client.close();  // Fermer la connexion lorsque la boucle se termine
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Adresse IP du serveur : ");
        String serverAddress = scanner.nextLine();
        Socket socket = new Socket(serverAddress, 6668);

        // Socket socket = new Socket("localhost", 12345);
        Scanner serverInput = new Scanner(socket.getInputStream());
        PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);

        // Lecture du message de bienvenue du serveur
        System.out.println(serverInput.nextLine());

        boolean correctGuess = false;

        while (!correctGuess) {
            System.out.print("Devinez le nombre : ");
            int guess = scanner.nextInt();
            clientOutput.println(guess);

            String response = serverInput.nextLine();
            System.out.println("Serveur dit : " + response);

            if (response.equals("Correct")) {
                correctGuess = true;
                break;  // Quitter la boucle si le message est "Correct"
            }
        }

        System.out.println("Fermeture de la connexion.");
        socket.close();
    }
}
