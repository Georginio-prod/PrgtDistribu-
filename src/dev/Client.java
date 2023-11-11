package dev;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Adresse IP du serveur : ");
        String serverAddress = scanner.nextLine();

        Socket socket = new Socket(serverAddress, 12345);
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
            }
        }

        System.out.println("Fermeture de la connexion.");
        socket.close();
    }
}





