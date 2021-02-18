package rmi;

import java.rmi.Naming;

public class Client {
	public static void main(String[] argv) {
		try {
			int port = 8000;
			JeuInterface obj = (JeuInterface) Naming.lookup("rmi://localhost:" + port + "/hello");
			System.out.println(obj.echo());
		} catch (Exception e) {
			System.out.println("Client exception: " + e);
		}
	}
}