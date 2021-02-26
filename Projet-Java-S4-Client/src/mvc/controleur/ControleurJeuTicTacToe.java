package mvc.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ControleurJeuTicTacToe implements Initializable {

	@FXML
	private Label label00, label10, label20, label01, label11, label21, label02, label12, label22;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		label10.setStyle("-fx-border-color: black; -fx-border-style: hidden solid hidden solid;");
		label11.setStyle("-fx-border-color: black;");
		label12.setStyle("-fx-border-color: black; -fx-border-style: hidden solid hidden solid;");
		label01.setStyle("-fx-border-color: black; -fx-border-style: solid hidden solid hidden;");
		label21.setStyle("-fx-border-color: black; -fx-border-style: solid hidden solid hidden;");
	}

}
