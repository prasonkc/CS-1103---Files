# Online Chat Application

This is a simple Java chat application with two programs:

- `ChatServer` accepts client connections, assigns each client a user ID,
  and broadcasts messages to all connected clients.
- `ChatClient` connects to the server and lets a user send and receive
  chat messages from the console.

## Files

- `src/ChatServer.java` contains the server program.
- `src/ChatClient.java` contains the client program.

## Technology Used
- Java
- Socket Programming (`ServerSocket` and `Socket`)
- Multithreading

## How it works
- The server listens for client connections and assigns each client a unique ID
- Each client connects to the server using a socket
- Clients send messages to the server
- The server broadcasts messages to all connected clients
- Each client uses one thread to send messages and another to receive messages

## How to Compile

Compile both source files from the project directory:

```bash
javac src/ChatServer.java src/ChatClient.java
```

## How to Run (Using Localhost)

Start the server first:

```bash
java ChatServer
```

To use a custom port, use a command-line argument:

```bash
java ChatServer 6000
```

Start a client:

```bash
java ChatClient
```

When the client starts, enter the server IP address and port number.
By default, the application uses port 5000.


## Commands
Type `exit` to close the client.
Use `Ctrl + C` to close the server.
