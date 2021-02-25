package mvc.controleur;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class ControleurJeuPendu implements Initializable {
	
	String dictionnaire[] = {"Claire", "Valentin", "Waian"}; 
	String mot; 
	String[] motPendu;
	String motATrou = ""; 

	@FXML
	private Button boutonA, boutonB, boutonC, boutonD, boutonE, boutonF, boutonG, boutonH, boutonI, boutonJ, boutonK,
			boutonL, boutonM, boutonN, boutonO, boutonP, boutonQ, boutonR, boutonS, boutonT, boutonU, boutonV, boutonW,
			boutonX, boutonY, boutonZ;
	
	@FXML 
	private Label labelMot; 
	
	@FXML
	private Line pendu1, pendu2, pendu3, pendu4, pendu5, pendu7, pendu8, pendu9, pendu10, pendu11;
	
	@FXML
	private Circle pendu6; 

	@FXML
	private GridPane gridPaneLettre;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		boutonA.setPrefSize(30, 30);
		boutonB.setPrefSize(30, 30);
		boutonC.setPrefSize(30, 30);
		boutonD.setPrefSize(30, 30);
		boutonE.setPrefSize(30, 30);
		boutonF.setPrefSize(30, 30);
		boutonG.setPrefSize(30, 30);
		boutonH.setPrefSize(30, 30);
		boutonI.setPrefSize(30, 30);
		boutonJ.setPrefSize(30, 30);
		boutonK.setPrefSize(30, 30);
		boutonL.setPrefSize(30, 30);
		boutonM.setPrefSize(30, 30);
		boutonN.setPrefSize(30, 30);
		boutonO.setPrefSize(30, 30);
		boutonP.setPrefSize(30, 30);
		boutonQ.setPrefSize(30, 30);
		boutonR.setPrefSize(30, 30);
		boutonS.setPrefSize(30, 30);
		boutonT.setPrefSize(30, 30);
		boutonU.setPrefSize(30, 30);
		boutonV.setPrefSize(30, 30);
		boutonW.setPrefSize(30, 30);
		boutonX.setPrefSize(30, 30);
		boutonY.setPrefSize(30, 30);
		boutonZ.setPrefSize(30, 30);
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
		initialiserMot();
		
	}
	
	@FXML
	/*
	public void ecritLettre(Button bouton) {
		System.out.println("Bonjour");
		char lettre = bouton.getText().toLowerCase().charAt(0);
		for (int i =0; i<mot.length(); i++) {
			if (mot.charAt(i)==lettre) motPendu[i]=lettre+"";
		}
	}*/
	
	public void ecritLettre(ActionEvent event) {
		System.out.println("Bonjour");
		Button btn = (Button) event.getSource();
		String id = btn.getId();
	    char lettre =  id.charAt(0);
	    System.out.println(lettre);
	}
	
	public void initialiserMot() {
		int nb;
		
		Random nbAleatoire = new Random();
		nb = nbAleatoire.nextInt(3);
		
		mot = dictionnaire[nb] ;
		motPendu = new String[mot.length()];
		
		for (int i=0; i<motPendu.length; i++) {
			motPendu[i]= "_ ";
		}
		
		for (int i=0; i<motPendu.length; i++) {
			motATrou+=motPendu[i];
			labelMot.setText(motATrou);
		}
		
	}
	
	public void ajoutTrait(int nbErreurs) {
		if (nbErreurs == 0);
		else if (nbErreurs == 1) pendu1.setVisible(true);
		else if (nbErreurs == 2) pendu2.setVisible(true);
		else if (nbErreurs == 3) pendu3.setVisible(true);
		else if (nbErreurs == 4) pendu4.setVisible(true);
		else if (nbErreurs == 5) pendu5.setVisible(true);
		else if (nbErreurs == 6) pendu6.setVisible(true);
		else if (nbErreurs == 7) pendu7.setVisible(true);
		else if (nbErreurs == 8) pendu8.setVisible(true);
		else if (nbErreurs == 9) pendu9.setVisible(true);
		else if (nbErreurs == 10) pendu10.setVisible(true);
		else pendu11.setVisible(true);
	}

}
