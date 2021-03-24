package mvc.controleur;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.modele.tictactoe.InterfaceTicTacToe;

public class ControleurJeuTicTacToe implements Initializable {

	@FXML
	private Label label1, label2, label3, label4, label5, label6, label7, label8, label9, labelIdPartie;

	// Ces 2 labels doivent être instanciés avec le "new Label()" pour ne pas
	// générer un "NullPointerException" dans les threads
	@FXML
	private Label tourJoueur = new Label();
	@FXML
	private Label labelJoueur = new Label();
	@FXML
	private Button btnQuitter;

	private int tour = 1;
	private int idPartie;
	private int nbJoueur = 0;
	private InterfaceTicTacToe intTtt;
	private int numJoueur;
	// Tableau des 9 labels de la grille
	String[] labels;

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

				new Thread(attente).start();
			} else {
				numJoueur = 2;
				labelJoueur.setText("Vous êtes le joueur " + numJoueur + ", forme : O");
				System.out.println("JOUEUR " + numJoueur);

				tourJoueur.setText("Au tour du joueur " + intTtt.getTourActuel(idPartie));

				intTtt.setTourActuel(idPartie, tour);
			}

		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			System.out.println("RMI exception" + e);
		}

		new Thread(joue).start();
	}

	Task<Void> joue = new Task<Void>() {
		@Override
		public Void call() {
			try {
				while (intTtt.getFinPartie(idPartie) == 0) {
					if (attente.isDone() && numJoueur == 1 || numJoueur == 2) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								tour();
							}
						});

						TimeUnit.SECONDS.sleep(1);
					}
				}

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							bloquerLabel();
							tourJoueur.setText("Fin de la partie");
							if (intTtt.getFinPartie(idPartie) == 1) {
								majLabels();
								TimeUnit.SECONDS.sleep(1);
								afficheVictoire();
							} else if (intTtt.getFinPartie(idPartie) == 2) {
								majLabels();
								TimeUnit.SECONDS.sleep(1);
								afficheMatchNul();
							} else
								quitterMatch();
						} catch (RemoteException | InterruptedException e) {
							System.out.println(e);
						}
					}
				});

			} catch (RemoteException | InterruptedException e) {
				System.out.println("Task joue exception : " + e);
			}

			return null;
		}
	};

	public void tour() {
		try {
			majLabels();

			if (intTtt.getTourActuel(idPartie) == numJoueur)
				tourJoueur.setText("A votre tour");
			else
				tourJoueur.setText("Tour de l'adversaire");

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
			if (intTtt.getTourActuel(idPartie) == 1) {
				l.setText("X");
				l.setTextFill(Color.BLUE);
				intTtt.setTourActuel(idPartie, 2);
			} else {
				l.setText("O");
				l.setTextFill(Color.RED);
				intTtt.setTourActuel(idPartie, 1);
			}
		} catch (RemoteException e) {
			System.out.println("Tour exception " + e);
		}
	}

	EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			try {
				if (e.getSource() == label1 && label1.getText() == "") {
					poseForme(label1);
					labels[0] = intTtt.getFormeJoue(idPartie);
				}
				if (e.getSource() == label2 && label2.getText() == "") {
					poseForme(label2);
					labels[1] = intTtt.getFormeJoue(idPartie);
				}
				if (e.getSource() == label3 && label3.getText() == "") {
					poseForme(label3);
					labels[2] = intTtt.getFormeJoue(idPartie);
				}
				if (e.getSource() == label4 && label4.getText() == "") {
					poseForme(label4);
					labels[3] = intTtt.getFormeJoue(idPartie);
				}
				if (e.getSource() == label5 && label5.getText() == "") {
					poseForme(label5);
					labels[4] = intTtt.getFormeJoue(idPartie);
				}
				if (e.getSource() == label6 && label6.getText() == "") {
					poseForme(label6);
					labels[5] = intTtt.getFormeJoue(idPartie);
				}
				if (e.getSource() == label7 && label7.getText() == "") {
					poseForme(label7);
					labels[6] = intTtt.getFormeJoue(idPartie);
				}
				if (e.getSource() == label8 && label8.getText() == "") {
					poseForme(label8);
					labels[7] = intTtt.getFormeJoue(idPartie);
				}
				if (e.getSource() == label9 && label9.getText() == "") {
					poseForme(label9);
					labels[8] = intTtt.getFormeJoue(idPartie);
				}
				e.consume();

				intTtt.setLabels(idPartie, labels);

				if (intTtt.verificationVictoire(idPartie, tour, label1.getText(), label2.getText(), label3.getText(),
						label4.getText(), label5.getText(), label6.getText(), label7.getText(), label8.getText(),
						label9.getText()) == true) {
					intTtt.setFin(idPartie, 1);
				}
				if (intTtt.verificationMatchNul(idPartie) == true) {
					intTtt.setFin(idPartie, 2);
				}

				tour();

			} catch (RemoteException re) {
				System.out.println(re);
			}
		}
	};

	private void afficheVictoire() {
		System.out.println("------FIN Victoire joueur " + numJoueur + " ------");

		Alert alert = new Alert(AlertType.INFORMATION);

		try {
			if (intTtt.getFormeJoue(idPartie).equals("X")) {
				if (numJoueur == 1) {
					alert.setContentText("Vous avez gagné !!");
					alert.setHeaderText("Victoire");
				} else {
					alert.setContentText("Vous avez perdu..");
					alert.setHeaderText("Défaite");
				}
			} else {
				if (numJoueur == 1) {
					alert.setContentText("Vous avez perdu..");
					alert.setHeaderText("Défaite");
				} else {
					alert.setContentText("Vous avez gagné !!");
					alert.setHeaderText("Victoire");
				}
			}
		} catch (RemoteException e) {
			System.out.println(e);
		}

		DialogPane pane = alert.getDialogPane();

		ObjectProperty<ButtonType> result = new SimpleObjectProperty<>();
		for (ButtonType type : pane.getButtonTypes()) {
			ButtonType resultValue = type;
			((Button) pane.lookupButton(type)).setOnAction(e -> {
				result.set(resultValue);
				pane.getScene().getWindow().hide();
			});
		}

		pane.getScene().setRoot(new Label());
		Scene scene = new Scene(pane);

		Stage dialog = new Stage();
		dialog.setTitle("Joueur " + numJoueur);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.getIcons().add(new Image("/mvc/vue/images/icon.svg.png"));

		dialog.show();
	}

	private void afficheMatchNul() {
		System.out.println("------FIN match nul joueur " + numJoueur + " ------");

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Match nul");
		alert.setHeaderText("Joueur " + numJoueur);
		alert.setContentText("Aucun des deux joueurs n'a gagné.");

		DialogPane pane = alert.getDialogPane();

		ObjectProperty<ButtonType> result = new SimpleObjectProperty<>();
		for (ButtonType type : pane.getButtonTypes()) {
			ButtonType resultValue = type;
			((Button) pane.lookupButton(type)).setOnAction(e -> {
				result.set(resultValue);
				pane.getScene().getWindow().hide();
			});
		}

		pane.getScene().setRoot(new Label());
		Scene scene = new Scene(pane);

		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setScene(scene);
		dialog.setTitle("Match nul");
		dialog.setResizable(false);
		dialog.getIcons().add(new Image("/mvc/vue/images/icon.svg.png"));

		dialog.show();
	}

	private void quitterMatch() {
		System.out.println("------FIN joueur quitte ------");

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Un joueur a quitté le match");
		alert.setHeaderText("Joueur " + numJoueur);
		alert.setContentText("Votre adversaire a quitté la partie");

		DialogPane pane = alert.getDialogPane();

		ObjectProperty<ButtonType> result = new SimpleObjectProperty<>();
		for (ButtonType type : pane.getButtonTypes()) {
			ButtonType resultValue = type;
			((Button) pane.lookupButton(type)).setOnAction(e -> {
				result.set(resultValue);
				pane.getScene().getWindow().hide();
			});
		}

		pane.getScene().setRoot(new Label());
		Scene scene = new Scene(pane);

		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.getIcons().add(new Image("/mvc/vue/images/icon.svg.png"));

		dialog.showAndWait();

		Stage stage = (Stage) btnQuitter.getScene().getWindow();
		stage.close();
	}

	public void bloquerLabel() {
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
						labelJoueur.setText("Vous êtes le joueur " + numJoueur + ", forme : X");
						System.out.println("JOUEUR " + numJoueur);

						try {
							tourJoueur.setText("Au tour du joueur " + intTtt.getTourActuel(idPartie));
							intTtt.setTourActuel(idPartie, tour);
						} catch (RemoteException e) {
							System.out.println("Attente exception : " + e);
						}
					}
				});

			} catch (RemoteException | InterruptedException e) {
				System.out.println(e);
			}
			return null;
		}
	};

	public void majLabels() throws RemoteException {
		labels = intTtt.getLabels(idPartie);

		label1.setText(labels[0]);
		label2.setText(labels[1]);
		label3.setText(labels[2]);
		label4.setText(labels[3]);
		label5.setText(labels[4]);
		label6.setText(labels[5]);
		label7.setText(labels[6]);
		label8.setText(labels[7]);
		label9.setText(labels[8]);
	}

	@FXML
	public void quitter() {
		if (attente.cancel())
			System.out.println("Attente cancel");
		if (joue.cancel())
			System.out.println("Joue cancel");

		try {
			intTtt.setFin(idPartie, 3);
		} catch (RemoteException e) {
			System.out.println(e);
		}

		Stage stage = (Stage) btnQuitter.getScene().getWindow();
		stage.close();
	}

}
