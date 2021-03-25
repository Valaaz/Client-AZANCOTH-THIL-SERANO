package mvc.controleur;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import mvc.modele.allumettes.InterfaceAllumettes;

public class ControleurAllumettes implements Initializable {

	private InterfaceAllumettes allumette;

	@FXML
	private Button boutonUn, boutonDeux, boutonQuitter;

	@FXML
	private Label nombreAllumettesPossedes, compteurAllumettesPartie, labelPartie, tourJoueur;

	int nbAllumettesJoueur = 0;
	int nbAllumettesPartie = 0;
	int idPartie;
	int tour;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			allumette = (InterfaceAllumettes) Naming.lookup("rmi://localhost:8000/allumette");
			idPartie = allumette.nouvellePartie();
			nbAllumettesPartie = allumette.generationAleatoireAllumettes(idPartie);
			allumette.setNbAllumettePartie(idPartie, nbAllumettesPartie);
			tour();

		} catch (MalformedURLException | RemoteException | NotBoundException | InterruptedException e) {
			System.out.println("Init : " + e);

		}

		labelPartie.setText("Partie n°" + idPartie);

		nbAllumettesJoueur = 0;

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumettesJoueur);

		boutonDeux.setDisable(false);

	}

	public void verifFinDePartie() {
		if (nbAllumettesPartie <= 0) {
			finDePartie();
		}
	}

	public void finDePartie() {
		Alert alert = new Alert(AlertType.INFORMATION);

		System.out.println("finDePartie" + nbAllumettesPartie);

		if (nbAllumettesJoueur % 2 == 0) { // Si compteur du joueur paire alors défaite
			alert.setTitle("Défaite");
			alert.setHeaderText("Dommage vous avez perdu ...");
			alert.setContentText("Réessayez une prochaine fois");

		} else { // Sinon victoire
			alert.setTitle("Victoire");
			alert.setHeaderText("Bravo vous avez gagné !");
			alert.setContentText("Le goût de la victoire est en vous");

		}
		alert.showAndWait();
		Stage stage = (Stage) boutonQuitter.getScene().getWindow();
		stage.close();

	}

	@FXML
	void retirerUneAllumette() throws RemoteException, InterruptedException {

		allumette.soustraireAllumettes(idPartie, 1);

		allumette.setNombreAllumettesJoueur(idPartie, nbAllumettesJoueur + 1);

		nbAllumettesPartie = allumette.getNbAllumettePartie(idPartie);
		nbAllumettesJoueur = allumette.getNombreAllumettesJoueur(idPartie);

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumettesJoueur);

		tour();

	}

	@FXML
	void retirerDeuxAllumettes() throws RemoteException, InterruptedException {

		allumette.soustraireAllumettes(idPartie, 2);

		allumette.setNombreAllumettesJoueur(idPartie, nbAllumettesJoueur + 2);

		nbAllumettesPartie = allumette.getNbAllumettePartie(idPartie);
		nbAllumettesJoueur = allumette.getNombreAllumettesJoueur(idPartie);

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("" + nbAllumettesJoueur);

		tour();
	}

	public void tour() throws RemoteException, InterruptedException {
		tour = allumette.getTour(idPartie);

		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event -> {

			try {
				allumette.coupIA(idPartie);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				nbAllumettesPartie = allumette.getNbAllumettePartie(idPartie);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			compteurAllumettesPartie.setText("" + nbAllumettesPartie);
			try {
				allumette.setTour(idPartie, tour - 1);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				tour();
			} catch (RemoteException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		if (tour == 1) {
			tourJoueur.setText("A votre tour...");
			boutonUn.setDisable(false);
			boutonDeux.setDisable(false);
			allumette.setTour(idPartie, tour + 1);
		} else if (nbAllumettesPartie >= 1) {
			boutonUn.setDisable(true);
			boutonDeux.setDisable(true);
			tourJoueur.setText("Au tour de l'adversaire...");
			pause.play();

		}

		if (nbAllumettesPartie < 2) {
			boutonDeux.setDisable(true);
		}

		if (nbAllumettesPartie <= 0) {
			boutonUn.setDisable(true); // Désactiver le bouton 1 lorsqu'il reste 0 allumette en jeu
			finDePartie();
		}
	}

	@FXML
	public void quitter() {

		Stage stage = (Stage) boutonQuitter.getScene().getWindow();
		stage.close();
		System.out.println("Jeu canceled");
	}

}
