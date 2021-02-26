package mvc.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControleurJeuTicTacToe implements Initializable {

	@FXML
	private Label label00, label10, label20, label01, label11, label21, label02, label12, label22;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		label00.setOnMouseClicked(handler);
		label10.setOnMouseClicked(handler);
		label20.setOnMouseClicked(handler);
		label01.setOnMouseClicked(handler);
		label11.setOnMouseClicked(handler);
		label21.setOnMouseClicked(handler);
		label02.setOnMouseClicked(handler);
		label12.setOnMouseClicked(handler);
		label22.setOnMouseClicked(handler);
		label10.setStyle("-fx-border-color: black; -fx-border-style: hidden solid hidden solid;");
		label11.setStyle("-fx-border-color: black;");
		label12.setStyle("-fx-border-color: black; -fx-border-style: hidden solid hidden solid;");
		label01.setStyle("-fx-border-color: black; -fx-border-style: solid hidden solid hidden;");
		label21.setStyle("-fx-border-color: black; -fx-border-style: solid hidden solid hidden;");
	}

	EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			if (e.getSource() == label00) {
				System.out.println(label00.getId());
				label00.setText("X");
			}
			if (e.getSource() == label10) {
				System.out.println(label10.getId());
				label10.setText("X");
			}
			if (e.getSource() == label20) {
				System.out.println(label20.getId());
				label20.setText("X");
			}
			if (e.getSource() == label01) {
				System.out.println(label01.getId());
				label01.setText("X");
			}
			if (e.getSource() == label11) {
				System.out.println(label11.getId());
				label11.setText("X");
			}
			if (e.getSource() == label12) {
				System.out.println(label12.getId());
				label12.setText("X");
			}
			if (e.getSource() == label02) {
				System.out.println(label02.getId());
				label02.setText("X");
			}
			if (e.getSource() == label21) {
				System.out.println(label21.getId());
				label21.setText("X");
			}
			if (e.getSource() == label22) {
				System.out.println(label22.getId());
				label22.setText("X");
			}
			e.consume();
		}

	};

}
