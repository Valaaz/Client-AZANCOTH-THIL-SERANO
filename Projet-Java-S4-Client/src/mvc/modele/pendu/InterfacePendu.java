package mvc.modele.pendu;

import java.rmi.Remote;

import java.rmi.RemoteException;

public interface InterfacePendu extends Remote {
	
	public String affichage(char[] c) throws RemoteException; 
	
	public String generationMotAleatoire() throws RemoteException; 
	
	public char[] ecritLettres (String mot, char c, char[] motDecompose) throws RemoteException; 
	
	public int dessinerPendu() throws RemoteException; 
	
	//public int dessinerPendu(int idPartie) throws RemoteException; 
	
	//public boolean partieTerminee(int idPartie) throws RemoteException; 
	
	//public int nouvellePartie() throws RemoteException; 
}
