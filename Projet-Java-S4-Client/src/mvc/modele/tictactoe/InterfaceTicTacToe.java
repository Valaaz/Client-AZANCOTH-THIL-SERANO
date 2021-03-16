package mvc.modele.tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTicTacToe extends Remote {

	public String echo() throws RemoteException;

	public int numPartie() throws RemoteException;

	public boolean verificationVictoire(int idPartie, int tour, String l1, String l2, String l3, String l4, String l5,
			String l6, String l7, String l8, String l9) throws RemoteException;

	public boolean verificationMatchNul(int idPartie, String l1, String l2, String l3, String l4, String l5, String l6,
			String l7, String l8, String l9) throws RemoteException;

}
