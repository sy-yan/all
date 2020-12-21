package java_test03_BIO_server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketHandler implements Runnable{
	private Socket socket;
	public SocketHandler(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {
		try {
			InputStreamReader reader =new InputStreamReader (socket.getInputStream());
			BufferedReader buffered_reader =new BufferedReader(reader);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			String client ="<"+socket.getInetAddress().toString()+":"+socket.getPort()+">";
			String request = buffered_reader.readLine();
			System.out.println(client+"say:"+request);
			String line = "Hello,too";
			writer.println(line);
			writer.flush();
			writer.close();
			buffered_reader.close();
			socket.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}