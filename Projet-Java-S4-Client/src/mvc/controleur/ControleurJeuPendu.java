package mvc.controleur;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import mvc.modele.pendu.InterfacePendu;

public class ControleurJeuPendu implements Initializable {

	@FXML
	private Button boutonA, boutonB, boutonC, boutonD, boutonE, boutonF, boutonG, boutonH, boutonI, boutonJ, boutonK,
			boutonL, boutonM, boutonN, boutonO, boutonP, boutonQ, boutonR, boutonS, boutonT, boutonU, boutonV, boutonW,
			boutonX, boutonY, boutonZ;

	@FXML
	private Label labelMot, labelPartie;

	@FXML
	private Line pendu1, pendu2, pendu3, pendu4, pendu5, pendu7, pendu8, pendu9, pendu10, pendu11;

	@FXML
	private Circle pendu6;

	@FXML
	private GridPane gridPaneLettre;

	InterfacePendu pendu;
	String mot;
	char[] motCache;
	int nbErreurs = 0;
	int idPartie;
	ArrayList<Button> listeBoutons = new ArrayList<Button>(); 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			pendu = (InterfacePendu) Naming.lookup("rmi://localhost:8000/pendu");
			idPartie = pendu.nouvellePartie();
			pendu.generationMotAleatoire(idPartie);
			mot = pendu.getMotAleatoire(idPartie);
			System.out.println("Mot aléatoire de la partie n°" + idPartie + " : " + mot);

			motCache = new char[mot.length()];
			for (int i = 0; i < mot.length(); i++) {
				motCache[i] = '_';
			}
			pendu.setMotCache(idPartie, motCache);
			labelMot.setText(pendu.affichage(idPartie));
			labelPartie.setText("Partie n°" + idPartie);
			initialisationInterface(); 
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
		}
		
	}
	
	public void initialisationInterface() {
		
		listeBoutons.add(boutonA);
		listeBoutons.add(boutonB);
		listeBoutons.add(boutonC);
		listeBoutons.add(boutonD);
		listeBoutons.add(boutonE);
		listeBoutons.add(boutonF);
		listeBoutons.add(boutonG);
		listeBoutons.add(boutonH);
		listeBoutons.add(boutonI);
		listeBoutons.add(boutonJ);
		listeBoutons.add(boutonK);
		listeBoutons.add(boutonL);
		listeBoutons.add(boutonM);
		listeBoutons.add(boutonN);
		listeBoutons.add(boutonO);
		listeBoutons.add(boutonP);
		listeBoutons.add(boutonQ);
		listeBoutons.add(boutonR);
		listeBoutons.add(boutonS);
		listeBoutons.add(boutonT);
		listeBoutons.add(boutonU);
		listeBoutons.add(boutonV);
		listeBoutons.add(boutonW);
		listeBoutons.add(boutonX);
		listeBoutons.add(boutonY);
		listeBoutons.add(boutonZ);
		
		for (int i=0; i<listeBoutons.size(); i++) {
			listeBoutons.get(i).setPrefSize(30,30);
			listeBoutons.get(i).setDisable(false);
		}
		
		pendu1.setVisible(false);
		pendu2.setVisible(false);
		pendu3.setVisible(false);
		pendu4.setVisible(false);
		pendu5.setVisible(false);
		pendu6.setVisible(false);
		pendu7.setVisible(false);
		pendu8.setVisible(false);
		pendu9.setVisible(false);
		pendu10.setVisible(false);
		pendu11.setVisible(false);
	}

	@FXML
	public void contientLettreA() throws RemoteException {
		boutonA.setDisable(true);
		contientLettre('a');
	}

	@FXML
	public void contientLettreB() throws RemoteException {
		boutonB.setDisable(true);
		contientLettre('b');
	}

	@FXML
	public void contientLettreC() throws RemoteException {
		boutonC.setDisable(true);
		contientLettre('c');
	}

	@FXML
	public void contientLettreD() throws RemoteException {
		boutonD.setDisable(true);
		contientLettre('d');
	}

	@FXML
	public void contientLettreE() throws RemoteException {
		boutonE.setDisable(true);
		contientLettre('e');
	}

	@FXML
	public void contientLettreF() throws RemoteException {
		boutonF.setDisable(true);
		contientLettre('f');
	}

	@FXML
	public void contientLettreG() throws RemoteException {
		boutonG.setDisable(true);
		contientLettre('g');
	}

	@FXML
	public void contientLettreH() throws RemoteException {
		boutonH.setDisable(true);
		contientLettre('h');
	}

	@FXML
	public void contientLettreI() throws RemoteException {
		boutonI.setDisable(true);
		contientLettre('i');
	}

	@FXML
	public void contientLettreJ() throws RemoteException {
		boutonJ.setDisable(true);
		contientLettre('j');
	}

	@FXML
	public void contientLettreK() throws RemoteException {
		boutonK.setDisable(true);
		contientLettre('k');
	}

	@FXML
	public void contientLettreL() throws RemoteException {
		boutonL.setDisable(true);
		contientLettre('l');
	}

	@FXML
	public void contientLettreM() throws RemoteException {
		boutonM.setDisable(true);
		contientLettre('m');
	}

	@FXML
	public void contientLettreN() throws RemoteException {
		boutonN.setDisable(true);
		contientLettre('n');
	}

	@FXML
	public void contientLettreO() throws RemoteException {
		boutonO.setDisable(true);
		contientLettre('o');
	}

	@FXML
	public void contientLettreP() throws RemoteException {
		boutonP.setDisable(true);
		contientLettre('p');
	}

	@FXML
	public void contientLettreQ() throws RemoteException {
		boutonQ.setDisable(true);
		contientLettre('q');
	}

	@FXML
	public void contientLettreR() throws RemoteException {
		boutonR.setDisable(true);
		contientLettre('r');
	}

	@FXML
	public void contientLettreS() throws RemoteException {
		boutonS.setDisable(true);
		contientLettre('s');
	}

	@FXML
	public void contientLettreT() throws RemoteException {
		boutonT.setDisable(true);
		contientLettre('t');
	}

	@FXML
	public void contientLettreU() throws RemoteException {
		boutonU.setDisable(true);
		contientLettre('u');
	}

	@FXML
	public void contientLettreV() throws RemoteException {
		boutonV.setDisable(true);
		contientLettre('v');
	}

	@FXML
	public void contientLettreW() throws RemoteException {
		boutonW.setDisable(true);
		contientLettre('w');
	}

	@FXML
	public void contientLettreX() throws RemoteException {
		boutonX.setDisable(true);
		contientLettre('x');
	}

	@FXML
	public void contientLettreY() throws RemoteException {
		boutonY.setDisable(true);
		contientLettre('y');
	}

	@FXML
	public void contientLettreZ() throws RemoteException {
		boutonZ.setDisable(true);
		contientLettre('z');
	}

	public void contientLettre(char lettre) throws RemoteException {
		pendu.ecritLettres(idPartie, lettre);
		labelMot.setText(pendu.affichage(idPartie));
		nbErreurs = pendu.dessinerPendu(idPartie);
		ajoutTraitPendu(nbErreurs);
		if (pendu.partieTerminee(idPartie)) affichageFin();
	}
	
	public void affichageFin() throws RemoteException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		ButtonType rejouer = new ButtonType("Rejouer");
        ButtonType quitter = new ButtonType("Quitter");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(rejouer, quitter);
        
		if (pendu.dessinerPendu(idPartie)>=11) {
			alert.setTitle("Dommage");
	        alert.setHeaderText("Dommage, vous avez perdu !");
	        alert.setContentText("Le mot était : "+ mot);
		}
		else {
	        alert.setTitle("Félicitations");
	        alert.setHeaderText("Félicitations, vous avez gagné !");
	        alert.setContentText("");
		}
		
		Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == rejouer) {
            initialize(null, null); 
        } else if (option.get() == quitter) {
        	Stage stage = (Stage) boutonA.getScene().getWindow();
    		stage.close();	
        } 
	}

	public void ajoutTraitPendu(int nbErreurs) throws RemoteException {
		switch (nbErreurs) {
		case 1: {
			pendu1.setVisible(true);
			break;
		}
		case 2: {
			pendu2.setVisible(true);
			break;
		}
		case 3: {
			pendu3.setVisible(true);
			break;
		}
		case 4: {
			pendu4.setVisible(true);
			break;
		}
		case 5: {
			pendu5.setVisible(true);
			break;
		}
		case 6: {
			pendu6.setVisible(true);
			break;
		}
		case 7: {
			pendu7.setVisible(true);
			break;
		}
		case 8: {
			pendu8.setVisible(true);
			break;
		}
		case 9: {
			pendu9.setVisible(true);
			break;
		}
		case 10: {
			pendu10.setVisible(true);
			break;
		}
		case 11: {
			pendu11.setVisible(true);
			break;
		}
		}
	}

}
