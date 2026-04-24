# Client-Server Chat Application

This is a simple Java chat application with two programs:

- `ChatServer` accepts client connections, assigns each client a user ID,
  and broadcasts messages to all connected clients.
- `ChatClient` connects to the server and lets a user send and receive
  chat messages from the console.

## Files

- `src/ChatServer.java` contains the server program.
- `src/ChatClient.java` contains the client program.

## How to Compile

Compile both source files from the project directory:

```bash
javac src/ChatServer.java src/ChatClient.java
```

## How to Run (Using Localhost)

Start the server first:

```bash
java -cp src ChatServer
```

To use a custom port, use a command-line argument:

```bash
java -cp src ChatServer 6000
```

Start a client:

```bash
java -cp src ChatClient
```

When the client starts, enter the server IP address and port number.
Type `exit` to close the client.
