package mvc.controleur;

import java.rmi.Naming;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import mvc.modele.JeuInterface;

public class ControleurMenuPrincipal {

	@FXML
	private MenuItem menuItemQuitter;
	@FXML
	private MenuItem menuItemPendu;
	@FXML
	private MenuItem menuItemAllumettes;
	@FXML
	private MenuItem menuItemTicTacToe;
	@FXML
	private Button boutonPendu;

	@FXML
	public void jeuDuPendu() {
		try {
			int port = 8000;
			JeuInterface obj = (JeuInterface) Naming.lookup("rmi://localhost:" + port + "/hello");
			System.out.println(obj.echo());
			/*
			 * URL fxmlURL = getClass().getResource("/mvc/vue/FichePendu.fxml"); FXMLLoader
			 * fxmlLoader = new FXMLLoader(fxmlURL); Parent root = fxmlLoader.load();
			 * 
			 * Stage stage = new Stage();
			 * 
			 * stage.setResizable(false); stage.initModality(Modality.WINDOW_MODAL);
			 * stage.setTitle("Pendu"); stage.setScene(new Scene(root, 500, 400));
			 * stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table
			 * de donnees
			 */
		} catch (Exception e) {
			System.out.println("Client exception: " + e);
		}

	}

	@FXML
	public void quitterApplication() {
		System.exit(0);
	}

	@FXML
	public void reglesPendu() {
		String regles = "Le pendu se joue avec l'ordinateur. Vous devez deviner le mot que l'ordinateur a choisi en s�lectionnant une lettre."
				+ " Un trait appara�t sur le dessin du pendu � chaque mauvaise r�ponse. Si vous trouver le mot vous gagnez, si le dessin arrive au bout de sa forme vous perdez.";

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("R�gles du Pendu");
		alert.setHeaderText("Jeu du Pendu");
		alert.setContentText(regles);
		alert.setResizable(true);

		// Permet � la fen�tre de s'adapter � la taille du contenu
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();
	}

	@FXML
	public void reglesAllumettes() {
		String regles = "Ce jeu se joue avec l'ordinateur en tour par tour. Un nombre impair d'allumettes est dispos�. Chacun des joueurs prend une ou 2 allumettes quand vient son tour."
				+ " Le jeu s'arr�te quand le tas est vide et le gagant est celui qui poss�de un nombre impair d'allumettes.";

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("R�gles du jeu des Allumettes");
		alert.setHeaderText("Jeu des Allumettes");
		alert.setContentText(regles);
		alert.setResizable(true);

		// Permet � la fen�tre de s'adapter � la taille du contenu
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();
	}

	@FXML
	public void reglesTicTacToe() {
		String regles = "Le Tic-Tac-Toe ou Morpion se joue � 2 joueurs en tour par tour sur une grille en 3x3. Le gagnant est celui qui arrive aligner 3 de ses formes"
				+ " que ce soit horizontalement, verticalement ou en diagonal. L'un des joueurs joue avec des ronds et l'autre avec des croix.";

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("R�gles du Tic-Tac-Toe");
		alert.setHeaderText("Tic-Tac-Toe");
		alert.setContentText(regles);
		alert.setResizable(true);

		// Permet � la fen�tre de s'adapter � la taille du contenu
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();
	}

}
