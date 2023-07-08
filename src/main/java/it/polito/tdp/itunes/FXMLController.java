
package it.polito.tdp.itunes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.itunes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPlaylist"
    private Button btnPlaylist; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGenere"
    private ComboBox<String> cmbGenere; // Value injected by FXMLLoader

    @FXML // fx:id="txtDTOT"
    private TextField txtDTOT; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="txtMin"
    private TextField txtMin; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaPlaylist(ActionEvent event) {
    	

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	String genere = cmbGenere.getSelectionModel().getSelectedItem();
    	 if (genere == null) {
    	 txtResult.appendText("Perfavore seleziona un genere!\n");
    	 return;
    	 }
    	 
    	 String n = txtMin.getText();
    	 double min = 0;
    	 if (n == "") {
    	  txtResult.setText("Perfavore inserisci una durata minima!\n");
    	  return;
    	  }
    	 try {
    	  min = Double.parseDouble(n);
    	  } catch (NumberFormatException e) {
    	  e.printStackTrace();
    	  return;
    	  }
    	 if (min < 0) {
    	  txtResult.appendText("Perfavore inserisci una durata minima positiva!\n");
    	  return;
    	  }
    	 
    	 String m = txtMax.getText();
    	 double max = 0;
    	 if (m == "") {
    	  txtResult.setText("Perfavore inserisci una durata massima!\n");
    	  return;
    	  }
    	 try {
    	  max = Double.parseDouble(m);
    	  } catch (NumberFormatException e) {
    	  e.printStackTrace();
    	  return;
    	  }
    	 if (max < 0) {
    	  txtResult.appendText("Perfavore inserisci una durata massima positiva!\n");
    	  return;
    	  }
    	 
    	 if (this.model.valutaMinimo(genere, min) == false) {
    	  txtResult.appendText("Perfavore inserisci una durata minima maggiore!\n");
       	  return;
    	 }
    	 
    	 this.model.creaGrafo(min, max, genere);
    	 
    	 txtResult.appendText("Grafo creato!\n");
    	 txtResult.appendText("#VERTICI: " + this.model.numeroVertici() + "\n");
    	 txtResult.appendText("#ARCHI: " + this.model.numeroArchi() + "\n");
    	 txtResult.appendText("\n" + this.model.calcolaComponentiConnesse());



    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPlaylist != null : "fx:id=\"btnPlaylist\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGenere != null : "fx:id=\"cmbGenere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDTOT != null : "fx:id=\"txtDTOT\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMin != null : "fx:id=\"txtMin\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbGenere.getItems().addAll(this.model.listaGeneri());
    }

}