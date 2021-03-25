package mvc.controleur;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import mvc.modele.allumettes.InterfaceAllumettes;

public class ControleurJeuAllumette implements Initializable {

	private InterfaceAllumettes allumette;

	@FXML
	private Button boutonUn, boutonDeux, boutonQuitter;

	@FXML
	private Label nombreAllumettesPossedes, labelPartie, tourJoueur, compteurAllumettesPartie;

	int nbAllumettesJoueur = 0;
	int nbAllumettesPartie = 0;
	int nbAllumettesTotal = 0;
	int idPartie;
	int tour;

	@FXML
	private ImageView a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19;
	@FXML
	private ArrayList<ImageView> listeAllumettes = new ArrayList<ImageView>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			listeAllumettes.add(a1);
			listeAllumettes.add(a2);
			listeAllumettes.add(a3);
			listeAllumettes.add(a4);
			listeAllumettes.add(a5);
			listeAllumettes.add(a6);
			listeAllumettes.add(a7);
			listeAllumettes.add(a8);
			listeAllumettes.add(a9);
			listeAllumettes.add(a10);
			listeAllumettes.add(a11);
			listeAllumettes.add(a12);
			listeAllumettes.add(a13);
			listeAllumettes.add(a14);
			listeAllumettes.add(a15);
			listeAllumettes.add(a16);
			listeAllumettes.add(a17);
			listeAllumettes.add(a18);
			listeAllumettes.add(a19);

			allumette = (InterfaceAllumettes) Naming.lookup("rmi://localhost:8000/allumette");
			idPartie = allumette.nouvellePartie();
			nbAllumettesPartie = allumette.generationAleatoireAllumettes(idPartie);
			nbAllumettesTotal = nbAllumettesPartie;
			allumette.setNbAllumettePartie(idPartie, nbAllumettesPartie);
			tour();

		} catch (MalformedURLException | RemoteException | NotBoundException | InterruptedException e) {
			System.out.println("Init : " + e);

		}

		for (int i = 0; i < nbAllumettesPartie; i++) {
			listeAllumettes.get(i).setVisible(true);
		}

		labelPartie.setText("Partie n°" + idPartie);

		nbAllumettesJoueur = 0;

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("Vous possédez " + nbAllumettesJoueur + " allumettes");

		boutonDeux.setDisable(false);

	}

	public void verifFinDePartie() {
		if (nbAllumettesPartie <= 0) {
			finDePartie();
		}
	}

	public void finDePartie() {
		Alert alert = new Alert(AlertType.INFORMATION);

		if (nbAllumettesJoueur % 2 == 0) { // Si compteur du joueur paire alors défaite
			alert.setTitle("Défaite");
			alert.setHeaderText("Dommage vous avez perdu ...");
			alert.setContentText("Réessayez une prochaine fois");

		} else { // Sinon victoire
			alert.setTitle("Victoire");
			alert.setHeaderText("Bravo vous avez gagné !");
			alert.setContentText("Le goût de la victoire est en vous");

		}
		alert.show();
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
		nombreAllumettesPossedes.setText("Vous possédez " + nbAllumettesJoueur + " allumettes");

		tour();

	}

	@FXML
	void retirerDeuxAllumettes() throws RemoteException, InterruptedException {

		allumette.soustraireAllumettes(idPartie, 2);

		allumette.setNombreAllumettesJoueur(idPartie, nbAllumettesJoueur + 2);

		nbAllumettesPartie = allumette.getNbAllumettePartie(idPartie);
		nbAllumettesJoueur = allumette.getNombreAllumettesJoueur(idPartie);

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);
		nombreAllumettesPossedes.setText("Vous possédez " + nbAllumettesJoueur + " allumettes");

		tour();
	}

	public void tour() throws RemoteException, InterruptedException {
		tour = allumette.getTour(idPartie);

		compteurAllumettesPartie.setText("" + nbAllumettesPartie);

		for (int i = 0; i < nbAllumettesTotal; i++) {
			listeAllumettes.get(i).setVisible(false);
		}
		for (int i = 0; i < nbAllumettesPartie; i++) {
			listeAllumettes.get(i).setVisible(true);
		}

		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event -> {

			try {
				allumette.coupIA(idPartie);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			compteurAllumettesPartie.setText("" + nbAllumettesPartie);
			try {
				nbAllumettesPartie = allumette.getNbAllumettePartie(idPartie);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
