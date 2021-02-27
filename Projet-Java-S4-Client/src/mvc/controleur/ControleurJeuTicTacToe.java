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
	private Label label1, label2, label3, label4, label5, label6, label7, label8, label9, tourJoueur;

	public int tour = 1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tour = 1;
		tourJoueur.setText("Au tour du joueur " + tour);
		label1.setOnMouseClicked(handler);
		label2.setOnMouseClicked(handler);
		label3.setOnMouseClicked(handler);
		label4.setOnMouseClicked(handler);
		label5.setOnMouseClicked(handler);
		label6.setOnMouseClicked(handler);
		label7.setOnMouseClicked(handler);
		label8.setOnMouseClicked(handler);
		label9.setOnMouseClicked(handler);
		label2.setStyle("-fx-border-color: black; -fx-border-style: hidden solid hidden solid;");
		label5.setStyle("-fx-border-color: black;");
		label8.setStyle("-fx-border-color: black; -fx-border-style: hidden solid hidden solid;");
		label4.setStyle("-fx-border-color: black; -fx-border-style: solid hidden solid hidden;");
		label6.setStyle("-fx-border-color: black; -fx-border-style: solid hidden solid hidden;");
	}

	EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			if (e.getSource() == label1 && label1.getText() == "") {
				System.out.println(label1.getId());
				poseForme(label1);
			}
			if (e.getSource() == label2 && label2.getText() == "") {
				System.out.println(label2.getId());
				poseForme(label2);
			}
			if (e.getSource() == label3 && label3.getText() == "") {
				System.out.println(label3.getId());
				poseForme(label3);
			}
			if (e.getSource() == label4 && label4.getText() == "") {
				System.out.println(label4.getId());
				poseForme(label4);
			}
			if (e.getSource() == label5 && label5.getText() == "") {
				System.out.println(label5.getId());
				poseForme(label5);
			}
			if (e.getSource() == label8 && label8.getText() == "") {
				System.out.println(label8.getId());
				poseForme(label8);
			}
			if (e.getSource() == label7 && label7.getText() == "") {
				System.out.println(label7.getId());
				poseForme(label7);
			}
			if (e.getSource() == label6 && label6.getText() == "") {
				System.out.println(label6.getId());
				poseForme(label6);
			}
			if (e.getSource() == label9 && label9.getText() == "") {
				System.out.println(label9.getId());
				poseForme(label9);
			}
			e.consume();
			verificationVictoireJoueur1();
			verificationVictoireJoueur2();
		}

	};

	public void poseForme(Label l) {
		String joueurActuel = "Au tour du joueur ";
		if (tour == 1) {
			l.setText("X");
			tour += 1;
			joueurActuel += tour;
		} else {
			l.setText("O");
			tour -= 1;
			joueurActuel += tour;
		}
		tourJoueur.setText(joueurActuel);
	}

	public void verificationVictoireJoueur1() {
		if (label1.getText() == "X" && label2.getText() == "X" && label3.getText() == "X") {
			System.out.println("Victoire des X !!");
			reinitialisation();
		}
		if (label4.getText() == "X" && label5.getText() == "X" && label6.getText() == "X") {
			System.out.println("Victoire des X !!");
			reinitialisation();
		}
		if (label7.getText() == "X" && label8.getText() == "X" && label9.getText() == "X") {
			System.out.println("Victoire des X !!");
			reinitialisation();
		}
		if (label1.getText() == "X" && label4.getText() == "X" && label7.getText() == "X") {
			System.out.println("Victoire des X !!");
			reinitialisation();
		}
		if (label2.getText() == "X" && label5.getText() == "X" && label8.getText() == "X") {
			System.out.println("Victoire des X !!");
			reinitialisation();
		}
		if (label3.getText() == "X" && label6.getText() == "X" && label9.getText() == "X") {
			System.out.println("Victoire des X !!");
			reinitialisation();
		}
		if (label1.getText() == "X" && label5.getText() == "X" && label9.getText() == "X") {
			System.out.println("Victoire des X !!");
			reinitialisation();
		}
		if (label7.getText() == "X" && label5.getText() == "X" && label3.getText() == "X") {
			System.out.println("Victoire des X !!");
			reinitialisation();
		}
	}

	public void verificationVictoireJoueur2() {
		if (label1.getText() == "O" && label2.getText() == "O" && label3.getText() == "O") {
			System.out.println("Victoire des O !!");
			reinitialisation();
		}
		if (label4.getText() == "O" && label5.getText() == "O" && label6.getText() == "O") {
			System.out.println("Victoire des O !!");
			reinitialisation();
		}
		if (label7.getText() == "O" && label8.getText() == "O" && label9.getText() == "O") {
			System.out.println("Victoire des O !!");
			reinitialisation();
		}
		if (label1.getText() == "O" && label4.getText() == "O" && label7.getText() == "O") {
			System.out.println("Victoire des O !!");
			reinitialisation();
		}
		if (label2.getText() == "O" && label5.getText() == "O" && label8.getText() == "O") {
			System.out.println("Victoire des O !!");
			reinitialisation();
		}
		if (label3.getText() == "O" && label6.getText() == "O" && label9.getText() == "O") {
			System.out.println("Victoire des O !!");
			reinitialisation();
		}
		if (label1.getText() == "O" && label5.getText() == "O" && label9.getText() == "O") {
			System.out.println("Victoire des O !!");
			reinitialisation();
		}
		if (label7.getText() == "O" && label5.getText() == "O" && label3.getText() == "O") {
			System.out.println("Victoire des O !!");
			reinitialisation();
		}
	}

	public void reinitialisation() {
		System.out.println("Nouvelle partie :");
		tour = 1;
		tourJoueur.setText("Au tour du joueur " + tour);
		label1.setText("");
		label2.setText("");
		label3.setText("");
		label4.setText("");
		label5.setText("");
		label6.setText("");
		label7.setText("");
		label8.setText("");
		label9.setText("");
	}

}
