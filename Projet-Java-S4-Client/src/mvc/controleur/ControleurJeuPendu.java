package mvc.controleur;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import mvc.modele.pendu.InterfacePendu;


public class ControleurJeuPendu implements Initializable {

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
	
	InterfacePendu pendu; 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			pendu = (InterfacePendu) Naming.lookup("rmi://localhost:8000/pendu");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.out.println(e);;
		}
		initialisationInterface(); 
		remplirLabel(); 
		
	}
	
	public void initialisationInterface () {
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
	}
	
	public void remplirLabel () {
		try {
			labelMot.setText(pendu.generationMotAleatoire());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void contientLettre() {
		System.out.println("Bonjour");
	}
	
	@FXML
	public void ecritLettre() {
		System.out.println("Bonjour");
	}
	
	

}
