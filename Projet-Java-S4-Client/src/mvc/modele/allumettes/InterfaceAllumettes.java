package mvc.modele.allumettes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceAllumettes extends Remote {

	public int coupIA() throws RemoteException;

	public int generationAleatoireAllumettes() throws RemoteException;

	public int soustraireAllumettes() throws RemoteException;

	public String partieTerminee(int resultat) throws RemoteException;

	public int getNombreAllumettes();

	public int nouvellePartie() throws RemoteException;

}
