package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}


	public List<Corso> getCorsiPerPeriodoDidattico(int periodo){
		
		return this.corsoDAO.getCorsiPerPeriodoDidattico(periodo);
	}
	
	public Map<Corso, Integer> getIscrittiCorsi(int periodo){
		
		return this.corsoDAO.getIscrittiCorsi(periodo);
	}
	
	public List<Studente> getStudentiCorso(String codins){
		return this.studenteDAO.getIscrittiCorso(codins);
	}
	
	public List<Divisione> getDivisioneStudentiCorso(String codins){
		return this.studenteDAO.getDivisioneStudentiCorso(codins);
	}
}
