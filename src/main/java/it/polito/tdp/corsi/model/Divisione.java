package it.polito.tdp.corsi.model;

public class Divisione {
	private String CDS;
	private Integer nStudenti;
	
	public Divisione(String CDS, Integer nStudenti) {
		super();
		this.CDS = CDS;
		this.nStudenti = nStudenti;
	}

	public String getCDS() {
		return CDS;
	}

	public Integer getnStudenti() {
		return nStudenti;
	}

	@Override
	public String toString() {
		return  CDS + ": " + nStudenti;
	}
	
	
	

}
