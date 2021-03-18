package mvc.modele.tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTicTacToe extends Remote {

	public int numPartie() throws RemoteException;

	public int getNombreJoueur(int id) throws RemoteException;

	public void setNombreJoueur(int id, int joueur) throws RemoteException;

	public boolean verificationVictoire(int idPartie, int tour, String l1, String l2, String l3, String l4, String l5,
			String l6, String l7, String l8, String l9) throws RemoteException;

	public boolean verificationMatchNul(int idPartie, String l1, String l2, String l3, String l4, String l5, String l6,
			String l7, String l8, String l9) throws RemoteException;

	public String initialisation() throws RemoteException;

}
