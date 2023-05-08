package it.polito.tdp.libretto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.libretto.model.Voto;

public class Prova {

	public static void main(String[] args) {

		String jdbcURL = "jdbc:mariadb://localhost/librettovoti?user=root&password=root";
		
		try {
			//Passo 1
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			//Passo 2			
			Statement st = conn.createStatement();
			
			//Passo 3
			String query = "SELECT corso,punti,data " //importante lo spazio, altrimenti nella concatenazione le parole data e FROM si unirebbero
					+ "FROM voto";
			
			//Passo 4
			ResultSet res = st.executeQuery(query);
			List<Voto> voti = new ArrayList<>();
			
			//Passo 5
			while(res.next()) {
				String corso  = res.getString("corso");
				int punti = res.getInt("punti");
				
//				System.out.println(corso+" = "+punti);
				
				Voto v = new Voto(corso,punti,null);
				voti.add(v);
				
			}
			
			//Passo 6
			conn.close();
			
			System.out.println(voti);
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}

}
