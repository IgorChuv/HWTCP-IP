import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class LocalHost {

    public static void main(String[] args) {
        String localHost= "netology.homework";
        int port= 80;

        try(Scanner scanner = new Scanner(System.in);
            Socket clientSocket = new Socket(localHost, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                String response = "";
                while(true) {
                    response = in.readLine();
                    System.out.println(response);
                    if(response.equals("Goodbye!")){
                        break;
                    }
                    String input = scanner.nextLine();
                    out.println(input);
                }
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
