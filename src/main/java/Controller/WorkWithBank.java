package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class WorkWithBank {
    public static final int PORT = 2222;
    public static final String IP = "127.0.0.1";

    private static DataOutputStream outputStream;
    private static DataInputStream inputStream;

    private static final String AdminBankUsername = "Admin";
    private static final String AdminBankPassword = "Admin";
    private static final String AdminBankId = "47039";

    public static void ConnectToBankServer() throws IOException {
        try {
            Socket socket = new Socket(IP, PORT);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
            inputStream.readUTF();
        } catch (IOException e) {
            throw new IOException("Exception while initiating connection:");
        }
    }



    private static String sendMessage(String msg){
        try {
            outputStream.writeUTF(msg);
            return inputStream.readUTF();
        } catch (IOException e) {
            return "Exception while sending message:";
        }

    }

    public static String increaseCredit(int much, String bankUsername, String bankPassword, String bankId) {
        String token = get_token(bankUsername , bankPassword);
        String res = sendMessage("create_receipt " + token + " move " + much + " " + bankId + " " + AdminBankId +  " increaseCredit");
        if (res.matches("[0-9]+")){
            res = sendMessage("pay " + res) ;
        }
        return res ;
    }

    private static String get_token(String bankUsername, String bankPassword) {
        return sendMessage("get_token " + bankUsername + " " + bankPassword);
    }

    public static String decreaseCredit(int much, String bankUsername, String bankPassword, String bankId) {
        String token = get_token(AdminBankUsername , AdminBankPassword);
        String res = sendMessage("create_receipt " + token + " move " + much + " " + AdminBankId + " " + bankId +  " decreaseCredit");
        if (res.matches("[0-9]+")){
            res = sendMessage("pay " + res) ;
        }
        return res ;
    }
}
