package mvc.controleur;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mvc.modele.allumettes.InterfaceAllumettes;

public class ControleurAllumettes implements Initializable {

	private InterfaceAllumettes allumette;

	@FXML
	private Button boutonUn, boutonDeux, boutonQuitter;

	@FXML
	private Label nombreAllumettesPossedes, compteurAllumettesPartie, labelPartie;

	int nbAllumettesJoueur = 0;
	int nbAllumettesPartie = 0;
	int idPartie;

	public void finDePartie() {
		if (nbAllumettesPartie == 0) {
			boutonUn.setDisable(true);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Partie Terminée");
			alert.setHeaderText("Partie Terminée");
			alert.setContentText("Partie terminée");
			alert.show();
		}

		if (nbAllumettesPartie < 2) {
			boutonDeux.setDisable(true);
		}
	}

	@FXML
	void retirerUneAllumette() throws RemoteException {

		allumette.soustraireAllumettes(idPartie, 1);

		allumette.setNombreAllumettesJoueur(idPartie, nbAllumettesJoueur + 1);

		nbAllumettesPartie = allumette.getNbAllumettePartie(idPartie);
		nbAllumettesJoueur = allumette.getNombreAllumettesJoueur(idPartie);

		finDePartie();

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumettesJoueur);

	}

	@FXML
	void retirerDeuxAllumettes() throws RemoteException {

		allumette.soustraireAllumettes(idPartie, 2);

		allumette.setNombreAllumettesJoueur(idPartie, nbAllumettesJoueur + 2);

		nbAllumettesPartie = allumette.getNbAllumettePartie(idPartie);
		nbAllumettesJoueur = allumette.getNombreAllumettesJoueur(idPartie);

		finDePartie();

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumettesJoueur);
	}

	public void IA() throws RemoteException {

		allumette.coupIA();

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumettesJoueur);

		if (nbAllumettesPartie == 0) {
			System.out.println("Fin de la partie");
		} else if (nbAllumettesPartie == 1) {
			boutonDeux.setDisable(true); // si il reste une allumette, désactiver le bouton 2
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			allumette = (InterfaceAllumettes) Naming.lookup("rmi://localhost:8000/allumette");
			idPartie = allumette.nouvellePartie();
			nbAllumettesPartie = allumette.generationAleatoireAllumettes(idPartie);
			allumette.setNbAllumettePartie(idPartie, nbAllumettesPartie);

		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.out.println("Init : " + e);
		}

		labelPartie.setText("Partie n°" + idPartie);

		nbAllumettesJoueur = 0;

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumettesJoueur);

		boutonDeux.setDisable(false);

	}

	@FXML
	public void quitter() {

		Stage stage = (Stage) boutonQuitter.getScene().getWindow();
		stage.close();
		System.out.println("Jeu canceled");
	}

}
