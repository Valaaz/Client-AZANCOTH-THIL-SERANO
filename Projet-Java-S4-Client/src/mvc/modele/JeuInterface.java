package mvc.modele;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JeuInterface extends Remote {
	public String echo() throws RemoteException;

	public Object affiche() throws RemoteException;
}
