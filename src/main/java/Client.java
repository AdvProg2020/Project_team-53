import View.Menu.MainMenu;
import View.Menu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client extends Application {
    public static Stage window = new Stage();

    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            window.setOnCloseRequest(e -> {
                try {
                    dataOutputStream.writeUTF("Exit");
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });

            Menu.setSocket(socket);
            Menu.setDataInputStream(dataInputStream);
            Menu.setDataOutputStream(dataOutputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //Database.initialize();
        launch(args);
        //Database.writeDataOnFile();
        //mainMenu.execute();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Menu.setWindow(window);
        //Menu.setMusic();
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}
