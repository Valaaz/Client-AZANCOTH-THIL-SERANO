package mvc.controleur;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import mvc.modele.tictactoe.InterfaceTicTacToe;

public class ControleurJeuTicTacToe implements Initializable {

	@FXML
	private Label label1, label2, label3, label4, label5, label6, label7, label8, label9, labelIdPartie;
	@FXML
	private Label tourJoueur = new Label();
	@FXML
	private Label labelJoueur = new Label();

	private int tour = 1;
	private static int idPartie;
	private int nbJoueur = 0;
	private static InterfaceTicTacToe intTtt;
	private int numJoueur;
	String[] labels;
	private boolean partieFinie = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			intTtt = (InterfaceTicTacToe) Naming.lookup("rmi://localhost:8000/tictactoe");
			idPartie = intTtt.numPartie();
			nbJoueur = intTtt.getNombreJoueur(idPartie);
			intTtt.setNombreJoueur(idPartie, nbJoueur + 1);

			labelIdPartie.setText("Partie n°" + idPartie);

			label2.setStyle("-fx-border-color: black; -fx-border-style: hidden solid hidden solid;");
			label5.setStyle("-fx-border-color: black;");
			label8.setStyle("-fx-border-color: black; -fx-border-style: hidden solid hidden solid;");
			label4.setStyle("-fx-border-color: black; -fx-border-style: solid hidden solid hidden;");
			label6.setStyle("-fx-border-color: black; -fx-border-style: solid hidden solid hidden;");

			label1.setOnMouseClicked(handler);
			label2.setOnMouseClicked(handler);
			label3.setOnMouseClicked(handler);
			label4.setOnMouseClicked(handler);
			label5.setOnMouseClicked(handler);
			label6.setOnMouseClicked(handler);
			label7.setOnMouseClicked(handler);
			label8.setOnMouseClicked(handler);
			label9.setOnMouseClicked(handler);

			label1.setDisable(true);
			label2.setDisable(true);
			label3.setDisable(true);
			label4.setDisable(true);
			label5.setDisable(true);
			label6.setDisable(true);
			label7.setDisable(true);
			label8.setDisable(true);
			label9.setDisable(true);

			if (intTtt.getNombreJoueur(idPartie) == 1) {
				tourJoueur.setText("En attente d'un deuxième joueur..");
				numJoueur = 1;
				/*
				 * new Thread(new Runnable() {
				 *
				 * @Override public void run() { Platform.runLater(new Runnable() {
				 *
				 * @Override public void run() { try { while (intTtt.getNombreJoueur(idPartie)
				 * == 1) { System.out.println("en attente.."); TimeUnit.SECONDS.sleep(2); }
				 * labelJoueur.setText("Vous êtes le joueur " + numJoueur);
				 * System.out.println("JOUEUR " + numJoueur);
				 *
				 * tourJoueur.setText("Au tour du joueur " + intTtt.getTourActuel(idPartie));
				 *
				 * intTtt.setTourActuel(idPartie, tour);
				 *
				 * } catch (RemoteException | InterruptedException e) { System.out.println(e); }
				 * } }); } }).start();
				 */
				new Thread(attente).start();
			} else {
				numJoueur = 2;
				labelJoueur.setText("Vous êtes le joueur " + numJoueur);
				System.out.println("JOUEUR " + numJoueur);

				tourJoueur.setText("Au tour du joueur " + intTtt.getTourActuel(idPartie));

				intTtt.setTourActuel(idPartie, tour);
			}

		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			System.out.println("RMI exception" + e);
		}
		/*
		 * if (numJoueur == 1) { while (attente.isRunning()) if (!attente.isRunning())
		 * new Thread(joue).start(); } else new Thread(joue).start();
		 */

		new Thread(joue).start();

	}

	Task<Void> joue = new Task<Void>() {
		@Override
		public Void call() {
			while (partieFinie != true) {
				if (attente.isDone() && numJoueur == 1 || numJoueur == 2) {

					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							tour();
						}
					});

					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						System.out.println("Time exception : " + e);
					}
				}
			}
			return null;
		}
	};

	public void tour() {
		try {
			labels = intTtt.getLabels(idPartie);
			tourJoueur.setText("Au tour du joueur " + intTtt.getTourActuel(idPartie));

			label1.setText(labels[0]);
			label2.setText(labels[1]);
			label3.setText(labels[2]);
			label4.setText(labels[3]);
			label5.setText(labels[4]);
			label6.setText(labels[5]);
			label7.setText(labels[6]);
			label8.setText(labels[7]);
			label9.setText(labels[8]);

			System.out.println("Tour : " + intTtt.getTourActuel(idPartie) + ", Num joueur : " + numJoueur);

			if (intTtt.getTourActuel(idPartie) == 1 && numJoueur == 1
					|| intTtt.getTourActuel(idPartie) == 2 && numJoueur == 2) {
				label1.setDisable(false);
				label2.setDisable(false);
				label3.setDisable(false);
				label4.setDisable(false);
				label5.setDisable(false);
				label6.setDisable(false);
				label7.setDisable(false);
				label8.setDisable(false);
				label9.setDisable(false);
			} else {
				label1.setDisable(true);
				label2.setDisable(true);
				label3.setDisable(true);
				label4.setDisable(true);
				label5.setDisable(true);
				label6.setDisable(true);
				label7.setDisable(true);
				label8.setDisable(true);
				label9.setDisable(true);
			}

		} catch (RemoteException e) {
			System.out.println("Tour exception " + e);
		}
	}

	public void poseForme(Label l) {
		try {
			// String joueurActuel = "Au tour du joueur ";
			if (intTtt.getTourActuel(idPartie) == 1) {
				l.setText("X");
				intTtt.setTourActuel(idPartie, 2);
			} else {
				l.setText("O");
				intTtt.setTourActuel(idPartie, 1);
			}
			// joueurActuel += intTtt.getTourActuel(idPartie);
			// tourJoueur.setText(joueurActuel);
		} catch (RemoteException e) {
			System.out.println("Tour exception " + e);
		}
	}

	public String getForme(int tour) {
		tour--; // On décremente le tour de 1 car on l'incrémente dans la fonction poseForme()
				// et nous voulons récupérer le tour qui a été joué
		if (tour == 1)
			return "X";
		else
			return "O";
	}

	EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			try {
				if (e.getSource() == label1 && label1.getText() == "") {
					poseForme(label1);
					labels[0] = getForme(intTtt.getTourActuel(idPartie));
				}
				if (e.getSource() == label2 && label2.getText() == "") {
					poseForme(label2);
					labels[1] = getForme(intTtt.getTourActuel(idPartie));
				}
				if (e.getSource() == label3 && label3.getText() == "") {
					poseForme(label3);
					labels[2] = getForme(intTtt.getTourActuel(idPartie));
				}
				if (e.getSource() == label4 && label4.getText() == "") {
					poseForme(label4);
					labels[3] = getForme(intTtt.getTourActuel(idPartie));
				}
				if (e.getSource() == label5 && label5.getText() == "") {
					poseForme(label5);
					labels[4] = getForme(intTtt.getTourActuel(idPartie));
				}
				if (e.getSource() == label6 && label6.getText() == "") {
					poseForme(label6);
					labels[5] = getForme(intTtt.getTourActuel(idPartie));
				}
				if (e.getSource() == label7 && label7.getText() == "") {
					poseForme(label7);
					labels[6] = getForme(intTtt.getTourActuel(idPartie));
				}
				if (e.getSource() == label8 && label8.getText() == "") {
					poseForme(label8);
					labels[7] = getForme(intTtt.getTourActuel(idPartie));
				}
				if (e.getSource() == label9 && label9.getText() == "") {
					poseForme(label9);
					labels[8] = getForme(intTtt.getTourActuel(idPartie));
				}
				e.consume();

				intTtt.setLabels(idPartie, labels);

				if (intTtt.verificationVictoire(idPartie, tour, label1.getText(), label2.getText(), label3.getText(),
						label4.getText(), label5.getText(), label6.getText(), label7.getText(), label8.getText(),
						label9.getText()) == true) {
					partieFinie = true;
					// reinitialisation();
				}
				if (intTtt.verificationMatchNul(idPartie, label1.getText(), label2.getText(), label3.getText(),
						label4.getText(), label5.getText(), label6.getText(), label7.getText(), label8.getText(),
						label9.getText()) == true) {
					partieFinie = true;
					// reinitialisation();
				}

				tour();

			} catch (RemoteException re) {
				System.out.println(re);
			}
		}

	};

	Task<Void> attente = new Task<Void>() {
		@Override
		public Void call() {
			try {
				while (intTtt.getNombreJoueur(idPartie) == 1) {
					System.out.println("en attente..");
					TimeUnit.SECONDS.sleep(2);
				}

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						labelJoueur.setText("Vous êtes le joueur " + numJoueur);
						System.out.println("JOUEUR " + numJoueur);

						try {
							tourJoueur.setText("Au tour du joueur " + intTtt.getTourActuel(idPartie));
							intTtt.setTourActuel(idPartie, tour);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				});

			} catch (RemoteException | InterruptedException e) {
				System.out.println(e);
			}
			return null;
		}
	};

}
