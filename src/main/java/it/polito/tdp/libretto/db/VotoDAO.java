package it.polito.tdp.libretto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.libretto.model.Voto;

public class VotoDAO {
	
	//Operazioni di tipo CRUD - Create Read Update Delete - List(CRUL)
	
	public List<Voto> listaVoti(){

		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			
			String query = "SELECT corso,punti,data " //importante lo spazio, altrimenti nella concatenazione le parole data e FROM si unirebbero
					+ "FROM voto";
			
			ResultSet res = st.executeQuery(query);
			List<Voto> voti = new ArrayList<>();
			
			while(res.next()) {
				String corso  = res.getString("corso");
				int punti = res.getInt("punti");
				LocalDate data = res.getDate("data").toLocalDate();
				Voto v = new Voto(corso,punti,data);
				voti.add(v);
			}
			
			conn.close();
	
			return voti;
				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public void createVoto(Voto v) {
		
		String query = "INSERT INTO voto (`corso`, `punti`, `data`) "
				+"VALUES (?,?,?); " ;
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setString(0, v.getNomeCorso());
			st.setInt(1, v.getPunti());
			st.setDate(3, null);
			st.executeUpdate();
			
						
			conn.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public Voto readVoto(String corso) {
		return null;
	}
	
	public List<Voto> searchVotoConPuntiMaggiori(int punti){
		return null;
	}
}
