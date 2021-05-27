import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int port = 80;
        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                out.println("New connection accepted. Please, introduce yourself:");
                final String name = in.readLine();

                out.println(String.format("Hi %s, your port is %d. Are you child? (yes/no) ", name, clientSocket.getPort()));
                final String verification = in.readLine();

                if (verification.equals("yes")) {
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                } else if (verification.equals("no")) {
                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                } else {
                    out.println("Answer is incorrect!");
                }

                while(true){
                    String input = in.readLine();
                    if(input.equals("end") || input.equals("End")){
                        out.println("Goodbye!");
                        break;
                    }
                    out.println("You say: " + input);
                }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
