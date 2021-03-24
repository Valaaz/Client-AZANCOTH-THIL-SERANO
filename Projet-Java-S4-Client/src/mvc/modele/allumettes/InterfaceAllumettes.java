package mvc.modele.allumettes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceAllumettes extends Remote {

	public int coupIA() throws RemoteException;

	public String partieTerminee(int resultat) throws RemoteException;

	public int nouvellePartie() throws RemoteException;

	void soustraireAllumettes(int id, int nbRetirer) throws RemoteException;

	int getNombreAllumettesJoueur(int id) throws RemoteException;

	int getNbAllumettePartie(int id) throws RemoteException;

	int generationAleatoireAllumettes(int id) throws RemoteException;

	void setNbAllumettePartie(int id, int nbAllumettes) throws RemoteException;

	void setNombreAllumettesJoueur(int id, int nbAllumettesJoueur) throws RemoteException;

}
