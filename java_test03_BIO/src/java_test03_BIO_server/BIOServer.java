package java_test03_BIO_server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    public static void main(String[] args)throws Exception{
    	ServerSocket server = new ServerSocket(3307);
    	while (true) {
    		Socket socket = server.accept();
    		SocketHandler socketHandler= new SocketHandler(socket);
    		Thread thread = new Thread(socketHandler);
    		thread.start();
    	}
    }
}


