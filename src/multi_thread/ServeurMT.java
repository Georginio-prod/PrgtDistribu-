package multi_thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMT extends Thread{
    int nbClients = 0;
    public void run () {
        try {
            System.out.println("J'attends une connexion");
            ServerSocket ss = new ServerSocket(4321);
            while (true) {
                Socket s = ss.accept();
                ++nbClients;
                new ClientMT(s, nbClients).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] agrs){
            new ServeurMT().start();
        }
    }

