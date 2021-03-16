package mvc.modele.tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTicTacToe extends Remote {

	public String echo() throws RemoteException;

	public int numPartie() throws RemoteException;

}
