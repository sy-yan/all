package java_test03_BIO_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BIOClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
         Socket socket = new Socket("www.alibaba.com",80);
         InputStreamReader reader =new InputStreamReader (socket.getInputStream());
			BufferedReader buffered_reader =new BufferedReader(reader);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			// String client ="<"+socket.getInetAddress().toString()+":"+socket.getPort()+">";
			// String request = buffered_reader.readLine();
			// System.out.println(client+"say:"+request);
			String line = "Hello.";
			writer.println(line);
			writer.flush();
			String response = buffered_reader.readLine();
			System.out.println("Server says:"+response);
			while(response!=null) {
				response = buffered_reader.readLine();
				System.out.println("Server says:"+response);
			}
			writer.close();
			buffered_reader.close();
			socket.close();
	}

}
