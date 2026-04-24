import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Runs the chat server.
 * The server listens for incoming client connections, assigns each client
 * a unique user ID, and broadcasts messages to every connected client.
 *
 * @author Prason KC
 */
public class ChatServer {
    /**
     * Default port.
     */
    static final int DEFAULT_PORT = 5000;

    /**
     * Maximum number of clients that the server can handle.
     */
    private static final int MAX_THREADS = 100;

    /**
     * Stores each connected client's output stream and ID.
     */
    private static final ConcurrentHashMap<Integer, PrintWriter> activeClients =
            new ConcurrentHashMap<>();

    /**
     * Generates sequential numeric IDs for newly connected clients.
     */
    private static final AtomicInteger clientCounter = new AtomicInteger(0);

    /**
     * Starts the server and listens continuously for client connections.
     *
     * @param args command-line arguments; {@code args[0]} can be a
     * valid port number from 1 to 65535
     */
    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        Socket client;

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
                if (port <= 0 || port > 65535) {
                    throw new NumberFormatException("Invalid Port");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        try (ServerSocket server = new ServerSocket(port);
             ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS)) {
            System.out.println("Listening on port: " + port);
            while (true) {
                try {
                    client = server.accept();
                    System.out.println(
                            "\nClient connected: " + client.getRemoteSocketAddress());

                    pool.execute(new ClientHandler(client));
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles all communication for a single connected client.
     */
    private static class ClientHandler implements Runnable {
        /**
         * Socket used to communicate with the client.
         */
        private final Socket clientSocket;

        /**
         * Numeric identifier for client connection.
         */
        private int idCounter;

        /**
         * Creates a handler for one client connection.
         *
         * @param client the connected client socket
         */
        ClientHandler(Socket client) {
            this.clientSocket = client;
        }

        @Override
        public void run() {
            this.idCounter = clientCounter.incrementAndGet();
            String clientID = "user-" + idCounter;
            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter writer =
                         new PrintWriter(
                                 new DataOutputStream(clientSocket.getOutputStream()), true)) {
                writer.println("ID_ASSIGNMENT: " + clientID);
                activeClients.put(idCounter, writer);

                updateServerLog();

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("\n" + "[" + clientID + "]" + message);
                    broadcast("[" + clientID + "]:" + message);
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                activeClients.remove(idCounter);
                System.out.println("[" + clientID + "]:" + "disconnected...");
                broadcast("[" + clientID + "]:" + "disconnected...");
                updateServerLog();
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        /**
         * Prints the current list of connected users to the server console.
         */
        private void updateServerLog() {
            System.out.println("\n--- Current Users: " + activeClients.size() + " ---");
            for (Integer clientID : activeClients.keySet()) {
                System.out.println("[" + "user-" + clientID + "]");
            }
        }

        /**
         * Broadcasts a message to all currently connected clients and removes
         * any client entries who are disconnected
         *
         * @param message the message to send to every connected client
         */
        private void broadcast(String message) {
            activeClients.entrySet().removeIf(entry -> {
                PrintWriter writer = entry.getValue();
                if (writer.checkError()) {
                    return true;
                } else {
                    writer.println(message);
                    return false;
                }
            });
        }
    }
}
