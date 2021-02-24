package mvc.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControleurJeuPendu implements Initializable {

	@FXML
	private Button boutonA, boutonB, boutonC, boutonD, boutonE, boutonF, boutonG, boutonH, boutonI, boutonJ, boutonK,
			boutonL, boutonM, boutonN, boutonO, boutonP, boutonQ, boutonR, boutonS, boutonT, boutonU, boutonV, boutonW,
			boutonX, boutonY, boutonZ;

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

	}

	public void ecritLettre() {
		System.out.println("Bonjour");
	}

}
