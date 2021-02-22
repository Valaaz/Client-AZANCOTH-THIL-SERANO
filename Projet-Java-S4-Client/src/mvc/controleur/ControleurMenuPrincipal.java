package mvc.controleur;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleurMenuPrincipal {

	@FXML
	private MenuItem menuItemQuitter;
	@FXML
	private Button boutonPendu;

	@FXML
	public void jeuDuPendu() {
		try {
			URL fxmlURL = getClass().getResource("/mvc/vue/FichePendu.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			Stage stage = new Stage();

			stage.setResizable(false);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setTitle("Pendu");
			stage.setScene(new Scene(root, 500, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void quitterApplication() {
		System.exit(0);
	}

}
