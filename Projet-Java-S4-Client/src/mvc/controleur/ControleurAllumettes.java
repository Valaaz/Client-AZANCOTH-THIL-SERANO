package mvc.controleur;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mvc.modele.allumettes.InterfaceAllumettes;

public class ControleurAllumettes implements Initializable {

	private InterfaceAllumettes allumette;

	@FXML
	private Button boutonUn, boutonDeux, quitter;

	@FXML
	private Label nombreAllumettesPossedes, compteurAllumettesPartie, labelPartie;

	int nbAllumette = 0;
	int nbAllumettesPartie = 0;
	int idPartie;

	@FXML
	void retirerUneAllumette() {

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumette);

		if (nbAllumettesPartie == 0) {
			System.out.println("Fin de la partie");
		}

		else {
			IA();
		}
	}

	@FXML
	void retirerDeuxAllumettes() {

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumette);

		if (nbAllumettesPartie == 0) {
			System.out.println("Fin de la partie");
		}

		else {
			IA();
		}
	}

	public void IA() {
		/*
		 * int nbAllumetteRetirer = Random.nextInt(1) + 1; // TODO fonction permettant
		 * // de prendre un nb aléatoire // d'allumettes
		 *
		 * nbAllumette += nbAllumRetirer; nbAllumettesPartie =
		 * Integer.parseInt(compteurAllumettesPartie.getText()); nbAllumettesPartie -=
		 * nbAllumRetirer;
		 */

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumette);

		if (nbAllumettesPartie == 0) {
			System.out.println("Fin de la partie");
		} else if (nbAllumettesPartie == 1) {
			boutonDeux.setDisable(true);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			allumette = (InterfaceAllumettes) Naming.lookup("rmi://localhost:8000/allumette");
			idPartie = allumette.nouvellePartie();

		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.out.println("Init : " + e);
		}

		labelPartie.setText("Partie n°" + idPartie);

		nbAllumette = 0;
		nbAllumettesPartie = new Random().nextInt(12) + 7; // genere un nombre d'allumettes entre 7 et 19
		if (nbAllumettesPartie % 2 == 0) {
			nbAllumettesPartie++;
		}

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumette);

		boutonDeux.setDisable(false);

	}

	@FXML
	public void quitter() {

		Stage stage = (Stage) quitter.getScene().getWindow();
		stage.close();
	}

}
