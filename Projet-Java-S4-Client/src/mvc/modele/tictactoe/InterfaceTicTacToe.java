package mvc.modele.tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTicTacToe extends Remote {

	public int numPartie() throws RemoteException;

	public int getNombreJoueur(int id) throws RemoteException;

	public void setNombreJoueur(int id, int joueur) throws RemoteException;

	public int getTourActuel(int id) throws RemoteException;

	public void setTourActuel(int id, int tour) throws RemoteException;

	public String getFormeActuel(int id) throws RemoteException;

	public void setFormeActuel(int id, String forme) throws RemoteException;

	public String[] getLabels(int id) throws RemoteException;

	public void setLabels(int id, String[] l) throws RemoteException;

	public boolean verificationVictoire(int idPartie, int tour, String l1, String l2, String l3, String l4, String l5,
			String l6, String l7, String l8, String l9) throws RemoteException;

	public boolean verificationMatchNul(int idPartie, String l1, String l2, String l3, String l4, String l5, String l6,
			String l7, String l8, String l9) throws RemoteException;

	public String initialisation() throws RemoteException;

	public String getFormeJoue(int id) throws RemoteException;

}
