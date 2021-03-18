package mvc.modele.pendu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacePendu extends Remote {
	
	public String generationMotAleatoire() throws RemoteException; 
	public char ecritLettres (char c) throws RemoteException; 
	public void dessinerPendu() throws RemoteException; 
}
