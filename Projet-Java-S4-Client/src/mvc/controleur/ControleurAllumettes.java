package mvc.controleur;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControleurAllumettes implements Initializable {

	@FXML
	private Button boutonUn, boutonDeux, quitter;

	@FXML
	private Label nombreAllumettesPossedes, compteurAllumettesPartie;

	int nbAllumette = 0;
	int nbAllumettesPartie = 0;

	@FXML
	void retirerAllumette(ActionEvent event) {
		Button bouton = (Button) event.getSource();

		int nbAllumRetirer = Integer.parseInt(bouton.getText());
		nbAllumette += nbAllumRetirer;
		nbAllumettesPartie = Integer.parseInt(compteurAllumettesPartie.getText());
		nbAllumettesPartie -= nbAllumRetirer;

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

		nbAllumette = 0;
		nbAllumettesPartie = new Random().nextInt(12) + 7; // genere un nombre d'allumettes entre 7 et 19
		if (nbAllumettesPartie % 2 == 0) {
			nbAllumettesPartie++;
		}

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumette);

		boutonDeux.setDisable(false);

	}

}
