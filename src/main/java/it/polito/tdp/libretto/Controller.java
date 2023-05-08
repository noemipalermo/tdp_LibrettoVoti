package it.polito.tdp.libretto;

import it.polito.tdp.libretto.model.Libretto;
import it.polito.tdp.libretto.model.Voto;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
	// Controller non deve MAI fare la new sul libretto, si fa in App.java
	private Libretto model;

//	public void setModel(Libretto model) {
//		this.model = model;
//	}
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnInserisci;

    @FXML
    private ComboBox<Integer> comboPunti;

    @FXML
    private DatePicker selData;

    @FXML
    private TextField txtNomeCorso;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleInserisci(ActionEvent event) {
    	String corso = txtNomeCorso.getText();
    	Integer punti = comboPunti.getValue();
    	LocalDate data = selData.getValue();
    	
    	Voto v = new Voto(corso,punti,data);
    	this.model.add(v);
    	
    	txtResult.setText(this.model.toString());
    }

    @FXML
    void initialize() {
        assert btnInserisci != null : "fx:id=\"btnInserisci\" was not injected: check your FXML file 'main.fxml'.";
        assert comboPunti != null : "fx:id=\"comboPunti\" was not injected: check your FXML file 'main.fxml'.";
        assert selData != null : "fx:id=\"selData\" was not injected: check your FXML file 'main.fxml'.";
        assert txtNomeCorso != null : "fx:id=\"txtNomeCorso\" was not injected: check your FXML file 'main.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'main.fxml'.";
        
        for(int p=18; p<=30; p++)
        	comboPunti.getItems().add(p);
    }
    
    public void setModel(Libretto model) {
    	this.model = model;
    	txtResult.setText(this.model.toString());
    }

}

	
