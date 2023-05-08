package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;


public class CorsoDAO {
	
	public List<Corso> getCorsiPerPeriodoDidattico(int pd){
		
		String query = "SELECT * "
				+ "FROM corso "
				+ "WHERE pd = ?";
		
		List<Corso> resultCorso = new ArrayList<>();
		
		
		try {
			
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), 
						rs.getInt("crediti"),rs.getString("nome"), 
						rs.getInt("pd"));
				resultCorso.add(c);
			}
			
			st.close();
			rs.close();
			conn.close();
			return resultCorso;
			
		} catch (SQLException e) {
			System.out.println("Errore in Corso DAO: getCorsiPerPeriodo");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Map<Corso,Integer> getIscrittiCorsi(int pd) {
		
		String query2 = "SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS num "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins=i.codins and c.pd=? "
				+ "GROUP BY c.codins, c.crediti, c.nome, c.pd;";
		
		Map<Corso, Integer> resultIscritti = new HashMap<>();
		
		
		try {
					
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(query2);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), 
						rs.getInt("crediti"),rs.getString("nome"), 
						rs.getInt("pd"));
				int numIscritti = rs.getInt("num");
				
				resultIscritti.put(c, numIscritti);
			}
			
			st.close();
			rs.close();
			conn.close();
			return resultIscritti;
			
		} catch (SQLException e) {
			System.out.println("Errore in Corso DAO: getIscrittiCorso");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	}

