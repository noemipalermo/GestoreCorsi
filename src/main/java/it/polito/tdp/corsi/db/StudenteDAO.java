package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {

public List<Studente> getIscrittiCorso(String codins){
		
		String query = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i " 
				+ "WHERE s.matricola=i.matricola AND i.codins=?;"; 

		
		List<Studente> resultStudentiCorso = new ArrayList<>();
		
		
		try {
			
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")) ;
				resultStudentiCorso.add(s);
			}
			
			st.close();
			rs.close();
			conn.close();
			return resultStudentiCorso;
			
		} catch (SQLException e) {
			System.out.println("Errore in Studente DAO: getStudentiPerCorso");
			e.printStackTrace();
			return null;
		}
		
	}

public List<Divisione> getDivisioneStudentiCorso(String codins){ //utile creare una nuova classe piuttosto che ritornare una mappa nel momento in cui può essere utile ordinare gli elementi
	
	String query = "SELECT s.CDS, COUNT(*) AS nStudenti "
			+"FROM studente s, iscrizione i "
			+"WHERE s.matricola=i.matricola AND i.codins=? AND s.CDS<>\"\""  // s.CDS <> "" -> s.CDS=!null
			+"GROUP BY s.CDS ;"; 
	
	List<Divisione> resultDivisioneStudenti= new ArrayList<>();
	
	
	try {
		
		Connection conn = DBConnect.getConnection();
		
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, codins);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			Divisione d = new Divisione(rs.getString("CDS"), rs.getInt("nStudenti")) ;
			resultDivisioneStudenti.add(d);
		}
		
		st.close();
		rs.close();
		conn.close();
		return resultDivisioneStudenti;
		
	} catch (SQLException e) {
		System.out.println("Errore in Studente DAO: getDivisioneStudenti");
		e.printStackTrace();
		return null;
	}
	
	
}
	
}
