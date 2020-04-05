package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FMXLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPeriodo;

    @FXML
    private TextField txtCorso;

    @FXML
    private Button btnCorsiPerPeriodo;

    @FXML
    private Button btnNumeroStudenti;

    @FXML
    private Button btnStudenti;

    @FXML
    private Button btnDivisionestudenti;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void NumeroStudenti(ActionEvent event) {
    	String pdString = txtPeriodo.getText();
        Integer pd;
        try {
        pd=Integer.parseInt(pdString);
        }catch(NumberFormatException e) {
        	txtRisultato.setText("Devi inserire un numero (1 o 2)!");
        	return;
        	
        }
        
        if(!pd.equals(1) && !pd.equals(2)) {
        	txtRisultato.setText("Devi inserire un numero (1 o 2)!");
        	return;
        }
        
    	Map<Corso, Integer> statistiche =this.model.getIscrittiByPeriodo(pd);
    	
    	for(Corso c : statistiche.keySet()) {
    		txtRisultato.appendText(c.getNome()+" "+ statistiche.get(c)+ "\n");
    	}

    }

    @FXML
    void corsoPerPeriodo(ActionEvent event) {
    	txtRisultato.clear();
        String pdString = txtPeriodo.getText();
        Integer pd;
        try {
        pd=Integer.parseInt(pdString);
        }catch(NumberFormatException e) {
        	txtRisultato.setText("Devi inserire un numero (1 o 2)!");
        	return;
        	
        }
        
        if(!pd.equals(1) && !pd.equals(2)) {
        	txtRisultato.setText("Devi inserire un numero (1 o 2)!");
        	return;
        }
        // l'input è corretto
       Map<Corso, Integer> statistiche = this.model.getIscrittiByPeriodo(pd);
       for(Corso c: statistiche.keySet()) {
    	   txtRisultato.appendText(c.getNome()+" "+ statistiche.get(c)+"\n");
       }
    }

    @FXML
    void stampaDivisione(ActionEvent event) {
  txtRisultato.clear();
    	
    	String codins = txtCorso.getText();
    	
    	// controllare se il codice corrisponde ad un corso esistente
    	if(!this.model.esisteCorso(codins)) {
    		txtRisultato.appendText("il corse non esiste!\n");
    		return;
    	}
    	// dato un corso ci aspettiamo una divisione del genere
    	
    	// Informatica 12 (numero di studenti studenti)
    	// Gestionali 20 (numero di studenti)
    	
    	Map<String, Integer> statistiche= this.model.getDivisoneCDS(new Corso(codins, null, null, null));
       
    	for(String cds : statistiche.keySet()) {
    		txtRisultato.appendText(cds+" "+ statistiche.get(cds)+ "\n");
    	}
    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	String codins = txtCorso.getText();
    	
    	// controllare se il codice corrisponde ad un corso esistente
    	if(!this.model.esisteCorso(codins)) {
    		txtRisultato.appendText("il corse non esiste\n");
    		return;
    	}
    	
    	
    	List<Studente> studenti = this.model.getStudentiByCorso(new Corso(codins,null,null,null));
    	if(studenti.size()==0) {
    		txtRisultato.appendText("il corso non ha stdudenti iscritti");
    		return;
    	}
    	for(Studente s: studenti) {
    		txtRisultato.appendText(s.toString() + "\n");
    	}

    }

    @FXML
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisionestudenti != null : "fx:id=\"btnDivisionestudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    

	

	public void setmodel(Model model) {
		this.model=model;
		
	}
}
