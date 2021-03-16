package mvc.controleur;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import mvc.modele.tictactoe.InterfaceTicTacToe;

public class ControleurJeuTicTacToe implements Initializable {

	@FXML
	private Label label1, label2, label3, label4, label5, label6, label7, label8, label9, tourJoueur, labelIdPartie;

	private int tour = 1;
	private int nbJoueur = 0;
	private int idPartie;
	private InterfaceTicTacToe intTtt;

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

		try {
			intTtt = (InterfaceTicTacToe) Naming.lookup("rmi://localhost:8000/tictactoe");
			System.out.println(intTtt.echo());
			idPartie = intTtt.numPartie();
			/*
			 * if (nbJoueur == 0) { idPartie = intTtt.numPartie(); nbJoueur++; } else if
			 * (nbJoueur == 1) { idPartie = intTtt.numPartie() - 1; nbJoueur++; } else if
			 * (nbJoueur == 2) { idPartie = intTtt.numPartie(); nbJoueur++; }
			 * System.out.println(nbJoueur);
			 */

		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			System.out.println(e);
		}

		labelIdPartie.setText("Partie n°" + idPartie);
	}

	EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			if (e.getSource() == label1 && label1.getText() == "") {
				poseForme(label1);
			}
			if (e.getSource() == label2 && label2.getText() == "") {
				poseForme(label2);
			}
			if (e.getSource() == label3 && label3.getText() == "") {
				poseForme(label3);
			}
			if (e.getSource() == label4 && label4.getText() == "") {
				poseForme(label4);
			}
			if (e.getSource() == label5 && label5.getText() == "") {
				poseForme(label5);
			}
			if (e.getSource() == label8 && label8.getText() == "") {
				poseForme(label8);
			}
			if (e.getSource() == label7 && label7.getText() == "") {
				poseForme(label7);
			}
			if (e.getSource() == label6 && label6.getText() == "") {
				poseForme(label6);
			}
			if (e.getSource() == label9 && label9.getText() == "") {
				poseForme(label9);
			}
			e.consume();

			try {
				if (intTtt.verificationVictoire(idPartie, tour, label1.getText(), label2.getText(), label3.getText(),
						label4.getText(), label5.getText(), label6.getText(), label7.getText(), label8.getText(),
						label9.getText()) == true) {
					reinitialisation();
				}
				if (intTtt.verificationMatchNul(idPartie, label1.getText(), label2.getText(), label3.getText(),
						label4.getText(), label5.getText(), label6.getText(), label7.getText(), label8.getText(),
						label9.getText()) == true) {
					reinitialisation();
				}
			} catch (RemoteException e1) {
				System.out.println(e1);
			}
		}

	};

	public void poseForme(Label l) {
		String joueurActuel = "Au tour du joueur ";
		if (tour == 1) {
			l.setText("X");
			tour += 1;
		} else {
			l.setText("O");
			tour -= 1;
		}
		joueurActuel += tour;
		tourJoueur.setText(joueurActuel);
	}

	/*
	 * public String getForme(int tour) { tour--; // On décremente le tour de 1 car
	 * on l'incrémente dans la fonction poseForme() // et nous voulons récupérer le
	 * tour qui a été joué if (tour == 1) return "X"; else return "O"; }
	 */

	public void reinitialisation() {
		System.out.println("Nouvelle partie");
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
