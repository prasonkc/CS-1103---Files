import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Runs the chat client.
 * The client connects to a server, sends messages entered by the user,
 * and displays messages received from the server.
 *
 * @author Prason KC
 */
public class ChatClient {
    /**
     * Stores the user ID assigned by the server after connection.
     */
    static String id;

    /**
     * Default port used when the user does not enter a port number.
     */
    static final int DEFAULT_PORT = 5000;

    /**
     * Prompts the user for connection information, connects to the server,
     * and continuously sends messages until the user types {@code exit}.
     *
     * @param args command-line arguments;
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Server IP: ");
        String serverURL = scanner.nextLine();

        System.out.print("Enter Port Number (Leave empty for default port - 5000): ");
        String portInput = scanner.nextLine();

        int port = DEFAULT_PORT;
        try {
            if (!portInput.isEmpty()) {
                port = Integer.parseInt(portInput);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid port. Using default 5000.");
        }

        try (Socket client = new Socket(serverURL, port)) {
            System.out.println("Connected to the server.\n");

            new Thread(new MessageReader(client)).start();

            PrintWriter messageOut = new PrintWriter(client.getOutputStream(), true);

            while (true) {
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
                messageOut.println(userInput);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads and displays messages sent by the server.
     */
    private static class MessageReader implements Runnable {
        /**
         * Socket used to receive messages from the server.
         */
        private final Socket clientSocket;

        /**
         * Creates a message reader for one client socket.
         *
         * @param client the connected client socket
         */
        MessageReader(Socket client) {
            this.clientSocket = client;
        }

        @Override
        public void run() {
            try (BufferedReader messageIn =
                         new BufferedReader(
                                 new InputStreamReader(clientSocket.getInputStream()))) {
                String incomingMessage;

                while ((incomingMessage = messageIn.readLine()) != null) {
                    System.out.print("\r");

                    if (incomingMessage.startsWith("ID_ASSIGNMENT:")) {
                        id = incomingMessage.split(":")[1].trim();
                        System.out.println("[SYSTEM]: Your assigned ID is " + id);
                    } else {
                        System.out.println(incomingMessage);
                    }

                    System.out.print(">>> ");
                }
            } catch (IOException e) {
                System.out.println("\r[SYSTEM]: Connection to server lost.");
            }
        }
    }
}
