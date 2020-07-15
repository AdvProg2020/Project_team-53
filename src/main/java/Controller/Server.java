package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;

    public static void main(String[] args) {
        Server server = new Server();
        Database.initialize();
        server.waitForClient();
    }

    public Server() {
        try {
            this.serverSocket = new ServerSocket(8585);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void waitForClient()
    {
        while (true)
        {
            try {
                Socket clientSocket = serverSocket.accept();
                new ClientThread(clientSocket).start();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
